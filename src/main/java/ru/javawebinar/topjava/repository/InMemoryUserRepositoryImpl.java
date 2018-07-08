package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

@Component
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Set<User> users;

    public InMemoryUserRepositoryImpl() {
        users = new TreeSet<>(comparing(AbstractNamedEntity::getName)
                .thenComparing(AbstractBaseEntity::getId));
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        return users.removeIf(user -> Objects.equals(user.getId(), id));
    }

    @Override
    public User get(int id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NotFoundException("no users are found"));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User query(Predicate<User> predicate) {
        return getAll().stream().filter(predicate)
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }
}
