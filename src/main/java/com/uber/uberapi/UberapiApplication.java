package com.uber.uberapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberapiApplication.class, args);

		Integer id = 1;

		System.out.println(id.equals(1));
	}
}
