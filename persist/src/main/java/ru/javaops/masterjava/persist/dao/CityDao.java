package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.AbstractDao;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

/**
 * Created by Restrictor on 17.11.2016.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class CityDao implements AbstractDao {

    public City save(City city) {
        if (city.isNew()) {
            int id = insert(city);
            city.setId(id);
        } else {
            update(city);
        }
        return city;
    }

    @SqlUpdate("INSERT INTO cities (city_name) VALUES (:cityName)")
    @GetGeneratedKeys
    abstract int insert(@BindBean City city);

    @SqlUpdate("UPDATE cities SET city_name=:cityName WHERE id=:id")
    abstract void update(@BindBean City city);

    @SqlQuery("SELECT * FROM cities WHERE id=:it")
    @Mapper(CityMapper.class)
    public abstract City get(@Bind int id);

    @SqlQuery("SELECT * FROM cities ORDER BY city_name")
    public abstract List<City> getAll();

    @SqlUpdate("TRUNCATE cities CASCADE")
    @Override
    public abstract void clean();

}
