package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class InMemoryMealRepositoryImpl2 implements MealRepository {

    private AtomicInteger counter = new AtomicInteger();
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();


    @Override
    public void create(Meal meal) {
        meals.put(meal.getId() == null ? counter.incrementAndGet() : meal.getId(), meal);
    }

    @Override
    public List<Meal> read() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal readById(Integer id) {
        return meals.get(id);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(Integer id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> query(Predicate<Meal> predicate) {
        return meals
                .values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public Meal queryForSingle(Predicate<Meal> predicate) {
        return meals
                .values()
                .stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }


    @PostConstruct
    private void init() { {
            Arrays.asList(
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                    new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510))
                    .forEach(this::create);
        }
    }

}
