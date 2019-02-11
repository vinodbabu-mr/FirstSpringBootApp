package com.java.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.java.dao","com.java.model","com.java.rest"})
@EntityScan("com.java.model")
@SpringBootApplication(scanBasePackages={"com.java.dao","com.java.rest","com.java.service",
		"com.java.config","com.java.model"})
@EnableJpaRepositories(basePackages={"com.java.dao","com.java.rest","com.java.service",
		"com.java.config","com.java.model"})
public class RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}

}

