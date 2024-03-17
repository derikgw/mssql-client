Certainly! Based on the details provided, here's a restructured `README.md` that serves as a more comprehensive developer's guide for your application:

# MSSQL Client Application Developer's Guide

Welcome to the MSSQL Client Application, a Spring Boot-based project designed for interacting with Microsoft SQL Server databases. This guide aims to assist developers in understanding, setting up, and contributing to this project.

## Important Notice

During the initial project setup, it was found that the intended package name 'com.derikwilson.mssql-client' is not a valid identifier in Java. As such, the project uses 'com.derikwilson.mssqlclient' as its package name.

## Getting Started

This section provides a brief overview of how to get the project up and running on your local development machine.

### Prerequisites

- Java JDK 11 or later
- Maven 3.6 or later
- Access to a Microsoft SQL Server instance

### Setting Up the Project

1. Clone the project repository:
   ```bash
   git clone https://github.com/derikgw/mssql-client.git
   ```
2. Navigate to the project directory:
   ```bash
   cd mssql-client
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration

The application requires configuration of database connection properties in `src/main/resources/application.properties` or through environment variables. Please refer to the included `application-local.properties.example` file for guidance on setting up your database connection.

## Reference Documentation

For further understanding and reference, the following documents and guides are recommended:

- [Official Apache Maven Documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/html/)
- [Guide to Creating OCI Images with Spring Boot](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/html/#build-image)

### Working with Spring Data JPA

This project leverages Spring Data JPA for data access:

- [Spring Data JPA Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
- [Guide to Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## Development Guides

- **Code Style**: Follow the [Oracle Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) and Spring Framework's coding standards.
- **Testing**: Write unit and integration tests for your code. The project includes JUnit 5 for testing purposes.
- **Logging**: Utilize the configured Logback for logging. Refer to the `logback-spring.xml` for logging configurations.

## Contribution

We welcome contributions! Please read the CONTRIBUTING.md file for guidelines on how to submit contributions to this project.

## Support

If you encounter any issues or require assistance, please file an issue on the project's GitHub issue tracker.

Thank you for contributing to the MSSQL Client Application!
