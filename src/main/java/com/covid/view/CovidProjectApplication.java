package com.covid.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.covid.controller"})
public class CovidProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidProjectApplication.class, args);
	}

}
