package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.javawebinar.topjava.configuration.WebConfiguration;


@Configuration
@Import({WebConfiguration.class})
public class TestConfiguration {

//    @Bean
//    public MealToMealDtoMapper mealToMealDtoMapper() {
//        return new MealToMealDtoMapperImpl();
//    }
}
