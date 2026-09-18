package com.liverpool.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class RestapiApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(RestapiApplication.class, args);
		MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
		System.out.println(">>> BASE DE DATOS REAL USADA POR MONGOTEMPLATE: " + mongoTemplate.getDb().getName());
	}
}