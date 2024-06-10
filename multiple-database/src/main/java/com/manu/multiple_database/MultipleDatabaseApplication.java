package com.manu.multiple_database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MultipleDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseApplication.class, args);
	}

}
