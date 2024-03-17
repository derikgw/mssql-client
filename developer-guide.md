# MSSQL Client Application Developer's Guide

Welcome to the MSSQL Client Application, a Spring Boot-based project designed for interacting with Microsoft SQL Server databases. This guide aims to assist developers in understanding, setting up, and contributing to this project.

## Please Read First

This application serves as an example project to demonstrate the use of Spring Framework, Spring Data JPA, and HikariCP for building a robust Java application that interacts with Microsoft SQL Server. It is intended to be used as a reference or starting point for developers familiarizing themselves with these technologies.

While this application is fully functional, it is important to note that it is primarily educational and should be used as a guide for best practices and integration patterns.

## Important Notice

During the initial project setup, it was found that the intended package name 'com.derikwilson.mssql-client' is not a valid identifier in Java. As such, the project uses 'com.derikwilson.mssqlclient' as its package name.

## Documentation
For detailed API information, check out the [Project Javadocs](https://derikgw.github.io/mssql-client/apidocs/index.html).

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

### Database Configuration

Before running the application, you need to set up the database schema and tables. Execute the following SQL script against your SQL Server instance to create the `TEST_APP` schema along with `APP_USERS` and `USER_INFO` tables:

```sql
USE TESTDB;
GO

IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'TEST_APP')
BEGIN
    EXEC ('CREATE SCHEMA TEST_APP');
END;
GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'TEST_APP' AND TABLE_NAME = 'APP_USERS')
BEGIN
    DROP TABLE TEST_APP.APP_USERS;
END;
GO

CREATE TABLE TEST_APP.APP_USERS (
    USER_GUID uniqueidentifier NOT NULL,
    USER_ID bigint NOT NULL,
    LAST_UPDATE datetime NULL,
    ACTIVE int NULL,
    CONSTRAINT PK_APP_USERS PRIMARY KEY (USER_GUID)
);
GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'TEST_APP' AND TABLE_NAME = 'USER_INFO')
BEGIN
    DROP TABLE TEST_APP.USER_INFO;
END;
GO

CREATE TABLE TEST_APP.USER_INFO (
    INFO_ID int NOT NULL IDENTITY(1,1),
    USER_GUID uniqueidentifier NOT NULL,
    EMAIL nchar(255) NOT NULL,
    CONSTRAINT PK_USER_INFO PRIMARY KEY (INFO_ID),
    CONSTRAINT FK_USER_INFO_APP_USERS FOREIGN KEY (USER_GUID) REFERENCES TEST_APP.APP_USERS (USER_GUID)
);
GO
```

Adjust the database connection properties in <b>'src/main/resources/application.properties'</b> based on your local setup or set the environment variables accordingly.

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
