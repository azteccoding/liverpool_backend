package com.liverpool.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(RestapiApplication.class, args);
		org.springframework.core.env.Environment env = context.getEnvironment();
		System.out.println(">>> DATABASE ACTUAL: test");
	}
}
