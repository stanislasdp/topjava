package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.javawebinar.topjava.configuration.DatabaseConfiguration;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@Import({TestConfiguration.class, DatabaseConfiguration.class})
public class TestDatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;MODE=MySQL;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }



    @Bean
    public MealRepository mealRepository() {
        return new InMemoryMealRepositoryImpl();
    }

}
