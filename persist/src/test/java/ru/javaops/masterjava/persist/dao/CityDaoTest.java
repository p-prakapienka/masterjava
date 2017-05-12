package ru.javaops.masterjava.persist.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.model.City;

import java.util.Arrays;
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
    static List<City> CITIES = Arrays.asList(KIV, MNSK, MOW, SPB);

    static Integer SPB_ID = 1;
    static Integer MOW_ID = 2;
    static Integer KIV_ID = 3;
    static Integer MNSK_ID = 4;

    public CityDaoTest() {
        super(CityDao.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        CITIES.forEach(c -> {
            c.setId(null);
            dao.save(c);
        });

        log.info("-----------   End setUp ---------------\n");
    }

    @Test
    public void getAll() {
        List<City> cities = dao.getAll();
        Assert.assertEquals(4, cities.size());
        Assert.assertArrayEquals(cities.toArray(), CITIES.toArray());
    }

    @Test
    public void get() {
        City city = dao.get(SPB.getId());
        Assert.assertEquals(SPB, city);
    }
}
