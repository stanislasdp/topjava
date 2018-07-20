package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.javawebinar.topjava.configuration.DatabaseConfiguration;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@Import({TestConfiguration.class, DatabaseConfiguration.class})
public class TestDatabaseConfiguration {


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .setName("testDB;MODE=MySQL")
            .addScripts("/sql/init.sql", "/sql/populate.sql")
            .build();
    }

    @Bean
    public MealRepository mealRepository() {
        return new InMemoryMealRepositoryImpl();
    }

}
