package com.derikwilson.mssqlclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the MSSQL Client application.
 * This Spring Boot application is designed to interface with a Microsoft SQL Server database,
 * providing an example of how to configure and utilize Spring Data JPA for database operations
 * within a Spring Boot context.
 * <p>
 * The {@link SpringBootApplication} annotation enables auto-configuration, component scan,
 * and configuration properties support, setting up the application context with sensible defaults
 * and custom configurations defined in application.properties or application.yml.
 * </p>
 */
@SpringBootApplication
public class MssqlClientApplication {

	/**
	 * Main method to launch the application.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MssqlClientApplication.class, args);
	}
}
