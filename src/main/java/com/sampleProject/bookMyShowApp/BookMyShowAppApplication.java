package com.sampleProject.bookMyShowApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookMyShowAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowAppApplication.class, args);
	}

}
