package com.kpmg.demo.repo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Datasource microservice. It hosts two REST based repository (price & entity)
 * 
 * @author aantipov
 */
@SpringBootApplication
public class RepoApplication {

	/**
	 * Application entry point
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(RepoApplication.class, args);
	}
}