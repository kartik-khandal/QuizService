package com.quiz;

import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuizServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext contextFactory = SpringApplication.run(QuizServiceApplication.class, args);

	}

}
