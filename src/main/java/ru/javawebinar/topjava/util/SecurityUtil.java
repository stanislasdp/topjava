package ru.javawebinar.topjava.util;

public class SecurityUtil {

    private static final int DEFAULT_USER_ID = 1;

    private static int userId = DEFAULT_USER_ID;

    public static int authUserId() {
        return userId;
    }

    public static void setAuthUserId(int id) {
        userId = id;
    }

    public static int authUserCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
