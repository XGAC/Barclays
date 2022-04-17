package com.barclays.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.barclays.demo")
public class BarclaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarclaysApplication.class, args);
	}

}
