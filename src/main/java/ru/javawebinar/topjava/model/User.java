package ru.javawebinar.topjava.model;

import lombok.Data;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;

@Data
public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private Boolean enabled = true;

    private LocalDateTime registered = LocalDateTime.now();

    private int caloriesPerDay = MealsUtil.DEFAULT_CALORIES_PER_DAY;

    private Set<Role> roles;

    public User(Integer id, String name, String email, String password, int caloriesPerDay, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(Integer id, String name, String email, String password, Role role, Role...roles) {
        this(id, name, email, password, MealsUtil.DEFAULT_CALORIES_PER_DAY, true, EnumSet.of(role, roles));
    }
}
