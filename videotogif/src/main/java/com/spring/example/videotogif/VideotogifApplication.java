package com.spring.example.videotogif;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class, JmxAutoConfiguration.class,
		WebSocketServletAutoConfiguration.class })
public class VideotogifApplication {

	@Value("${multipart.location}/gif/")
	private String videoGifLocation;

	public static void main(String[] args) {
		SpringApplication.run(VideotogifApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> deRegisterHiddenHttpMethodFilter(
			HiddenHttpMethodFilter hiddenHttpMethodFilter) {
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				hiddenHttpMethodFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<HttpPutFormContentFilter> deRegisterHttpPutFormContentFilter(
			HttpPutFormContentFilter httpPutFormContentFilter) {
		FilterRegistrationBean<HttpPutFormContentFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				httpPutFormContentFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<RequestContextFilter> deRegisterRequestContextFilter(
			RequestContextFilter requestContextFilter) {
		FilterRegistrationBean<RequestContextFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				requestContextFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
		};
	}

}