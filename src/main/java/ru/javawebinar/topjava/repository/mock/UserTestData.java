package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.EnumSet;

public class UserTestData {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "user", "user@email.com", "password", 2000, true, EnumSet.of(Role.ROLE_ADMIN));
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@email.com", "password", 2000, true, EnumSet.of(Role.ROLE_USER));

}
