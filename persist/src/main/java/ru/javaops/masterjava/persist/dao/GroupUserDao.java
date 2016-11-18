package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.AbstractDao;
import ru.javaops.masterjava.persist.model.User;

import java.util.List;

/**
 * Created by Restrictor on 18.11.2016.
 */
public abstract class GroupUserDao implements AbstractDao {

    @SqlUpdate("INSERT INTO groups_users (group_id, user_id) VALUES (:groupId, :userId)")
    public abstract void insert(@Bind Integer groupId, @Bind Integer userId);

    @SqlUpdate("TRUNCATE groups_users")
    @Override
    public abstract void clean();
}
