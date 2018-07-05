package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryMealRepository implements MealRepository {

    private AtomicLong counter = new AtomicLong();
    private Map<Long, Meal> meals = new ConcurrentHashMap<>();

    {
        Arrays.asList(
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                new Meal(counter.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510))
                .forEach(this::create);
    }

    @Override
    public void create(Meal meal) {
        meals.put(meal.getId() == null ? counter.incrementAndGet() : meal.getId(), meal);
    }

    @Override
    public List<Meal> read() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal readById(Long id) {
        return meals.getOrDefault(id, new Meal());
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(Long id) {
        meals.remove(id);
    }
}
