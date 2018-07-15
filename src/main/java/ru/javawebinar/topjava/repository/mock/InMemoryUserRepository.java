package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class InMemoryUserRepository implements UserRepository {

    private Map<Integer, User> users = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            users.put(user.getId(), user);
            return user;
        }
        return users.computeIfPresent(user.getId(), (id, newUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        return users.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return users.values()
                .stream()
                .sorted(comparing(User::getName).thenComparing(User::getEmail))
                .collect(toList());
    }

    @Override
    public User getByEmail(String email) {
        return users.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    @PostConstruct
    private void init() {
        save(UserTestData.USER);
        save(UserTestData.ADMIN);
    }
}
