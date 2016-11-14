package ru.javaops.masterjava.persist;

import ru.javaops.masterjava.model.User;
import ru.javaops.masterjava.model.UserFlag;

/**
 * Created by Restrictor on 14.11.2016.
 */
public abstract class UserTestData {
    public final static User USER1 = new User("Test User1", "user1@gmail.com", UserFlag.superuser);
    public final static User USER2 = new User("Test User2", "user2@gmail.com", UserFlag.active);

    public final static int USER1_ID = 100000;
    public final static int USER2_ID = 100001;
}
