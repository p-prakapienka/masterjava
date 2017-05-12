package ru.javaops.masterjava.persist.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.GroupFlag;
import ru.javaops.masterjava.persist.model.Projects;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Restrictor on 17.11.2016.
 */
@Slf4j
public class GroupDaoTest extends AbstractDaoTest<GroupDao> {

    static Group TOPJAVA06 = new Group("topjava06", Projects.TOPJAVA, GroupFlag.FINISHED);
    static Group TOPJAVA07 = new Group("topjava07", Projects.TOPJAVA, GroupFlag.FINISHED);
    static Group TOPJAVA08 = new Group("topjava08", Projects.TOPJAVA, GroupFlag.CURRENT);
    static Group MASTERJAVA01 = new Group("masterjava01", Projects.MASTERJAVA, GroupFlag.CURRENT);
    static List<Group> GROUPS = Arrays.asList(MASTERJAVA01, TOPJAVA06, TOPJAVA07, TOPJAVA08);

    static Integer TOPJAVA06_ID = 5;
    static Integer TOPJAVA07_ID = 6;
    static Integer TOPJAVA08_ID = 7;
    static Integer MASTERJAVA01_ID = 8;

    public GroupDaoTest() {
        super(GroupDao.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        GROUPS.forEach(g -> {
            g.setId(null);
            dao.save(g);
        });

        log.info("-----------   End setUp ---------------\n");
    }

    @Test
    public void getAll() {
        List<Group> groups = dao.getAll();
        Assert.assertEquals(4, groups.size());
        Assert.assertArrayEquals(groups.toArray(), GROUPS.toArray());
    }

    @Test
    public void get() {
        Group group = dao.get(TOPJAVA06.getId());
        Assert.assertEquals(TOPJAVA06, group);
    }
}
