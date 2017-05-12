package ru.javaops.masterjava.persist.dao;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.skife.jdbi.v2.Handle;
import ru.javaops.masterjava.persist.AbstractDao;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.DBITestProvider;
import ru.javaops.masterjava.persist.DBPopulator;

import java.io.*;

/**
 * gkislin
 * 27.10.2016
 */
public abstract class AbstractDaoTest<DAO extends AbstractDao> {
    static {
        DBITestProvider.initDBI();
    }

    protected DAO dao;

    protected AbstractDaoTest(Class<DAO> daoClass) {
        this.dao = DBIProvider.getDao(daoClass);
    }

    @Before
    public void setUp() throws Exception {
        DBPopulator.executeSqlScript("initDB.sql");
    }

}
