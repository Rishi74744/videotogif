package com.spring.example.videotogif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class, JmxAutoConfiguration.class,
		WebSocketServletAutoConfiguration.class })
public class VideotogifApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideotogifApplication.class, args);
	}

}