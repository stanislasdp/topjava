package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class MockUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return Collections.emptyList();
    }

    @Override
    public User query(Predicate<User> predicate) {
        return getAll().stream().filter(predicate)
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }
}
