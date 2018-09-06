package com.spring.example.videotogif;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Value("${multipart.location}/gif/")
	private String videoGifLocation;

	@PostConstruct
	private void init() {
		File gifFolder = new File(videoGifLocation);
		if (!gifFolder.exists()) {
			gifFolder.mkdirs();
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/gif/**").addResourceLocations("file:" + videoGifLocation);

	}

}
