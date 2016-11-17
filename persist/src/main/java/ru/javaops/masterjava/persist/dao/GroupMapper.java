package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import ru.javaops.masterjava.persist.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Restrictor on 18.11.2016.
 */
public class GroupMapper implements ResultSetMapper<Group> {
    @Override
    public Group map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Group(rs.getInt("id"), rs.getString("group_name"), rs.getString("project_name"));
    }
}
