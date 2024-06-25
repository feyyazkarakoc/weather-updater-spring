package com.tpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TinyWeatherUpdaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyWeatherUpdaterApplication.class, args);
	}

}
