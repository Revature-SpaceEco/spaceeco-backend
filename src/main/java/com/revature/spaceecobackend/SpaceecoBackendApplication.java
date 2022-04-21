package com.revature.spaceecobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;
=======
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
>>>>>>> 42755edb160d9e1bd1738ae2caaeb966a81520cf

public class SpaceecoBackendApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpaceecoBackendApplication.class, args);
	}

}
