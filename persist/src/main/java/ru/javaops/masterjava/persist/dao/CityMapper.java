package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import ru.javaops.masterjava.persist.model.City;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Restrictor on 18.11.2016.
 */
public class CityMapper implements ResultSetMapper<City> {
    @Override
    public City map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new City(rs.getInt("id"), rs.getString("city_name"));
    }
}
