package kov.develop.mvc.controller.data;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final User ADMIN = new User(1, "Andrey", "12345", "ADMIN");
    public static final User USER_1 = new User(2, "Zlatan", "54321", "USER");
    public static final User USER_2 = new User(3, "Petya", "54321", "USER");
    public static final User USER_3 = new User(4, "Masha", "54321", "USER");
    public static final User USER_4 = new User(5, "Olya", "54321", "USER");
    public static final User USER_5 = new User(6, "Ivanov", "54321", "USER");
    public static final List<User> USERS = Arrays.asList(USER_1, USER_2, USER_3, USER_4, USER_5);

    public static final Good GOOD_1 = new Good(1, "Meet", "Beef", 3.34, 1000);
    public static final Good GOOD_2 = new Good(2, "Meet1", "Beef5", 2.13, 1000);
    public static final Good GOOD_3 = new Good(3, "Meet2", "Beef4", 6.87, 1000);
    public static final Good GOOD_4 = new Good(4, "Meet3", "Beef3", 9.56, 1000);
    public static final Good GOOD_5 = new Good(5, "Meet4", "Beef2", 15.68, 1000);
    public static final Good GOOD_6 = new Good(6, "Meet5", "Beef1", 300.54, 1000);

    public static final Purchasing PURCHASING = new Purchasing(1,1,1, LocalDate.parse("2017-12-12"),3.0,3);
}
