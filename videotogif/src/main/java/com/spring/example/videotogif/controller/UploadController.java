package com.spring.example.videotogif.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.spring.example.videotogif.autoconfigure.service.GifEncoderService;
import com.spring.example.videotogif.autoconfigure.service.VideoConvertorService;
import com.spring.example.videotogif.autoconfigure.service.VideoDecoderService;

@RestController
public class UploadController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${multipart.location}")
	private String location;

	@Autowired
	private VideoConvertorService videoConvertorService;

	@Autowired
	private GifEncoderService gifEncoderService;

	@Autowired
	private VideoDecoderService videoDecoderService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
	public String uploadFile(@RequestPart("file") MultipartFile multipartFile, @RequestParam("startTime") int startTime,
			@RequestParam("endTime") int endTime, @RequestParam("gifSpeed") int gifSpeed,
			@RequestParam("repeatAtEnd") boolean repeatAtEnd) throws IOException, Exception {
		File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
		multipartFile.transferTo(videoFile);
		log.info("Saved File To {Files Location} : ", videoFile.getAbsolutePath());
		Path output = Paths.get(location + "/gif/" + System.currentTimeMillis() + ".gif");

		FFmpegFrameGrabber frameGrabber = videoDecoderService.read(videoFile);
		AnimatedGifEncoder gifEncoder = gifEncoderService.getGifEncoder(repeatAtEnd,
				(float) frameGrabber.getFrameRate(), output);
		videoConvertorService.toAnimatedGif(frameGrabber, gifEncoder, startTime, endTime, gifSpeed);

		log.info("Saved generated gif to {}", output.toString());

		return output.getFileName().toString();
	}

}
