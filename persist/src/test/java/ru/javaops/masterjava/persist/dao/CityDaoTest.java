package ru.javaops.masterjava.persist.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

/**
 * Created by Restrictor on 17.11.2016.
 */
@Slf4j
public class CityDaoTest extends AbstractDaoTest<CityDao> {

    static City SPB = new City("Санкт-Петербург");
    static City MOW = new City("Москва");
    static City KIV = new City("Киев");
    static City MNSK = new City("Минск");

    public CityDaoTest() {
        super(CityDao.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        SPB.setId(null);
        MOW.setId(null);
        KIV.setId(null);
        MNSK.setId(null);

        dao.save(SPB);
        dao.save(MOW);
        dao.save(KIV);
        dao.save(MNSK);

        log.info("-----------   End setUp ---------------\n");
    }

    @Test
    public void getAll() {
        List<City> cities = dao.getAll();
        Assert.assertEquals(4, cities.size());
    }

    @Test
    public void get() {
        City city = dao.get(SPB.getId());
        Assert.assertEquals(SPB, city);
    }
}
