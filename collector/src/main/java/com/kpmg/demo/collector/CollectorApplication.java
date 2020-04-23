package com.kpmg.demo.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Collector microservice. Based on scheduler, service collect and records share price info
 * @author aantipov
 */
@SpringBootApplication
@EnableScheduling
public class CollectorApplication {

	/**
	 * Application entrypoint
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
	}

}
