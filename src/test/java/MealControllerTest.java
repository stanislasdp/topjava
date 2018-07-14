import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.javawebinar.topjava.converter.MealDtoToMealWithExceedConverter;
import ru.javawebinar.topjava.dto.MealDto;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class MealControllerTest {

    @Mock
    private MealService service;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MealDtoToMealWithExceedConverter converter;

    @InjectMocks
    private MealRestController controller = new MealRestController();

    @Test
    public void getExceededPositiveTest() {
        List<MealDto> dtos = new ArrayList<>();
        dtos.add(new MealDto(1, LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(13), "description", 1000));
        dtos.add(new MealDto(2, LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(12), "description2", 1001));
        given(service.getWithinTime(any(), any(), anyInt())).willReturn(dtos);
        List<MealWithExceed> expected = new ArrayList<>();
        expected.add(new MealWithExceed(1, LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(13), "description", 1000, true));
        expected.add(new MealWithExceed(2, LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(12), "description2", 1001, true));

        List<MealWithExceed> exceeded = controller.getExceeded(LocalDate.now().minusDays(1), LocalDate.now().minusDays(2),
                LocalTime.now(), LocalTime.now());

        assertEquals(expected, exceeded);
    }
}
