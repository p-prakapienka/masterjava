package ru.javaops.masterjava.persist.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Projects;

import java.util.List;

/**
 * Created by Restrictor on 17.11.2016.
 */
@Slf4j
public class GroupDaoTest extends AbstractDaoTest<GroupDao> {

    static Group TOPJAVA06 = new Group("topjava06", Projects.TOPJAVA);
    static Group TOPJAVA07 = new Group("topjava07", Projects.TOPJAVA);
    static Group TOPJAVA08 = new Group("topjava08", Projects.TOPJAVA);
    static Group MASTERJAVA01 = new Group("masterjava01", Projects.MASTERJAVA);

    public GroupDaoTest() {
        super(GroupDao.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        TOPJAVA06.setId(null);
        TOPJAVA07.setId(null);
        TOPJAVA08.setId(null);
        MASTERJAVA01.setId(null);

        dao.save(TOPJAVA06);
        dao.save(TOPJAVA07);
        dao.save(TOPJAVA08);
        dao.save(MASTERJAVA01);

        log.info("-----------   End setUp ---------------\n");
    }

    @Test
    public void getAll() {
        List<Group> groups = dao.getAll();
        Assert.assertEquals(4, groups.size());
    }

    @Test
    public void get() {
        Group group = dao.get(TOPJAVA06.getId());
        Assert.assertEquals(TOPJAVA06, group);
    }
}
