package com.spring.example.videotogif.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${multipart.location}")
	private String location;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
	public String uploadFile(@RequestPart("file") MultipartFile multipartFile, @RequestParam("startTime") int startTime,
			@RequestParam("endTime") int endTime, @RequestParam("gifSpeed") int gifSpeed,
			@RequestParam("repeatAtEnd") boolean repeatAtEnd) throws IOException {
		File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
		multipartFile.transferTo(videoFile);
		log.info("Saved File To {Files Location} : ", videoFile.getAbsolutePath());
		return "";
	}

}
