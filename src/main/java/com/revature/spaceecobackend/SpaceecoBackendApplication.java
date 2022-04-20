package com.revature.spaceecobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class SpaceecoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceecoBackendApplication.class, args);
	}

}
