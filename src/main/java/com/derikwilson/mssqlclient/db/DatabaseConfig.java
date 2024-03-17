package com.derikwilson.mssqlclient.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

/**
 * Configuration class for setting up the database connection within the Spring Boot application.
 * It defines beans and configuration settings needed to establish a connection to the database.
 * This class utilizes Spring Boot's {@link ConfigurationProperties} annotation to automatically bind and validate external configurations,
 * like those specified in application.properties or application.yml, to configure the DataSource.
 */
@Configuration
public class DatabaseConfig {

    /**
     * Configures and provides a {@link DataSource} bean that Spring Boot uses to manage database connections.
     * The {@link ConfigurationProperties} annotation with the prefix "spring.datasource" reads database configuration
     * properties from application.properties or application.yml and applies them to configure the DataSource.
     *
     * This method leverages Spring Boot's DataSourceBuilder to create and configure a data source instance
     * based on the application's properties, facilitating connection pooling and other DataSource optimizations.
     *
     * @return The configured DataSource instance that can be used by Spring Boot to manage database connections.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
