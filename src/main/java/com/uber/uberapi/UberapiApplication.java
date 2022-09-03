package com.uber.uberapi;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberapiApplication.class, args);

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);

		list.forEach(value -> {
			System.out.println(value);
		});

	}
}
