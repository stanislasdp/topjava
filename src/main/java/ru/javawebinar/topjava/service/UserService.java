package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    void update(User user);

    List<User> getAll();
}
