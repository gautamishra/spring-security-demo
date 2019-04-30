package com.spring.saphire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = { @PropertySource("classpath:persistence.properties"),
		@PropertySource("classpath:messages_en_US.properties") })

public class SaphireApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaphireApplication.class, args);
	}

}
