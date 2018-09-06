package com.spring.example.videotogif.health.controller;

import javax.inject.Inject;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.spring.example.videotogif.autoconfigure.properties.VideoToGifProperties;

public class ApplicationHealthIndicator implements HealthIndicator {

	@Inject
	private VideoToGifProperties videoToGifProperties;

	@Override
	public Health health() {
		if (videoToGifProperties.getGifLocation().canWrite()) {
			return Health.down().build();
		}
		return Health.up().build();
	}

}
