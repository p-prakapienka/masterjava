package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;
import ru.javaops.masterjava.persist.AbstractDao;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

/**
 * Created by Restrictor on 17.11.2016.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class GroupDao implements AbstractDao {

    public Group save(Group group) {
        if (group.isNew()) {
            int id = insert(group);
            group.setId(id);
        } else {
            update(group);
        }
        return group;
    }

    @SqlUpdate("INSERT INTO groups (group_name, project_name) VALUES (:groupName, :projectName)")
    @GetGeneratedKeys
    abstract int insert(@BindBean Group group);

    @SqlUpdate("UPDATE groups SET group_name=:groupName, project_name=:projectName WHERE id=:id")
    abstract void update(@BindBean Group group);

    @SqlQuery("SELECT * FROM groups WHERE id=:it")
    @Mapper(GroupMapper.class)
    public abstract Group get(@Bind int id);

    @SqlQuery("SELECT * FROM groups ORDER BY project_name, group_name")
    public abstract List<Group> getAll();

    @SqlUpdate("TRUNCATE groups CASCADE")
    @Override
    public abstract void clean();

}
