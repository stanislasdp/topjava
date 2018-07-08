package ru.javawebinar.topjava.web.meal.action;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ActionsResolver {

    private List<Action> actions;

    private MealRestController controller;

    public ActionsResolver(MealRestController controller) {
        this.controller = controller;
        resolve();
    }

    @SneakyThrows
    private void resolve() {
        Reflections reflections = new Reflections(this.getClass().getPackage().getName());
        Set<Class<? extends Action>> classes = reflections.getSubTypesOf(Action.class);
        actions = classes.stream().map(clazz -> {
            try {
                return clazz.getConstructor(MealRestController.class).newInstance(controller);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                    IllegalArgumentException | InvocationTargetException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }).collect(toImmutableList());
    }

    public List<Action> getActions() {
        return actions;
    }
}
