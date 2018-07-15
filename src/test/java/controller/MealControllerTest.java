package controller;

import org.mockito.Answers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.converter.MealDtoToMealWithExceedConverter;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class MealControllerTest {

    @Autowired
    private MealService service;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MealDtoToMealWithExceedConverter converter;

    @Autowired
    private MealRestController controller;

    public void deleteTest() {
        controller.delete(1);

        List<MealWithExceed> meals = controller.getExceeded(null, null, null, null);

        assertThat(meals).extracting("id").isNotIn(1);
    }
}
