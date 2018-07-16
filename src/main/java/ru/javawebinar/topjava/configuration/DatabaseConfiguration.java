package ru.javawebinar.topjava.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Import(ApplicationConfiguration.class)
@PropertySource("classpath:database.properties")
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("dburl"));
        dataSource.setSchema(environment.getRequiredProperty("dbschema"));
        dataSource.setUsername(environment.getRequiredProperty("dbusername"));
        dataSource.setPassword(environment.getRequiredProperty("dbpassword"));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sql/init.sql"));
        databasePopulator.addScript(new ClassPathResource("/sql/populate.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(dataSource());
    }
}
