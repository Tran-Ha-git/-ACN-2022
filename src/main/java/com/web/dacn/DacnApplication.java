package com.web.dacn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.web.dacn.config.StorageProperties;
import com.web.dacn.service.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DacnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DacnApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
}
