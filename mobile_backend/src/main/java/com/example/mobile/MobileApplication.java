package com.example.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileApplication.class, args);
	}

}
