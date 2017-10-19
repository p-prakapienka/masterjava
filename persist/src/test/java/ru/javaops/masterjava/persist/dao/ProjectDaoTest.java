package ru.javaops.masterjava.persist.dao;

import org.junit.Before;
import org.junit.Test;
import ru.javaops.masterjava.persist.ProjectTestData;
import ru.javaops.masterjava.persist.model.Project;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static ru.javaops.masterjava.persist.ProjectTestData.PROJECTS;

/**
 * Created by Restrictor on 12.05.2017.
 */
public class ProjectDaoTest extends AbstractDaoTest<ProjectDao> {

    public ProjectDaoTest() {
        super(ProjectDao.class);
    }

    @Before
    public void setUp() throws Exception {
        ProjectTestData.setUp();
    }

    @Test
    public void getAll() throws Exception {
        final Map<String, Project> projects = dao.getAsMap();
        assertEquals(PROJECTS, projects);
        System.out.println(projects.values());
    }
}
