package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;

public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final int ADMIN_USER_ID = 1;

    private AtomicInteger counter = new AtomicInteger();
    private Map<Integer, Map<Integer, Meal>> meals = new ConcurrentHashMap<>();

    @Override
    public Meal create(Meal meal, int userId) {
        Map<Integer, Meal> meals = this.meals.computeIfAbsent(userId, integer -> new ConcurrentHashMap<>());

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        throw new RuntimeException("meal is not new");
    }

    @Override
    public List<Meal> read(int userId) {
        return new ArrayList<>(meals.getOrDefault(userId, emptyMap()).values());
    }

    @Override
    public Meal readById(int id, int userId) {
        return meals.getOrDefault(userId, emptyMap()).get(id);
    }

    @Override
    public Meal update(Meal meal, int userId) {
        if (meal.isNew()) {
            throw new RuntimeException("meal not found for User" + userId);
        }
        Map<Integer, Meal> userMeal = this.meals.getOrDefault(userId, emptyMap());
        return userMeal.computeIfPresent(meal.getId(), (integer, meal1) -> meal);
    }

    @Override
    public boolean delete(Integer id, int userId) {
        return meals.getOrDefault(userId, emptyMap()).remove(id) != null;
    }

    @Override
    public List<Meal> query(Predicate<Meal> predicate, int userId) {
        return meals.getOrDefault(userId, emptyMap())
                .values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public Meal queryForSingle(Predicate<Meal> predicate, int userId) {
        return meals.getOrDefault(userId, emptyMap())
                .values()
                .stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @PostConstruct
    private void init() {
        {
            Stream.of(
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                    new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510))
                    .forEach(meal -> create(meal, ADMIN_USER_ID));
        }
    }


}
