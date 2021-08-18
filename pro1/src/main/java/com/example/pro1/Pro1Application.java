package com.example.pro1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pro1Application {

	public static void main(String[] args) {
		SpringApplication.run(Pro1Application.class, args);
	}

}
