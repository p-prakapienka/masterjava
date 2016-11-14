package ru.javaops.masterjava.persist;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.masterjava.model.User;
import ru.javaops.masterjava.model.UserFlag;

import java.util.List;

import static ru.javaops.masterjava.persist.UserTestData.*;

/**
 * Created by Restrictor on 14.11.2016.
 */
public class UserDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoTest.class);

    private static UserDao userDao;

    @BeforeClass
    public static void setup() {
        userDao = new UserDao();
    }

    @AfterClass
    public static void teardown() {
        userDao = null;
    }

    @Before
    public void setUp() {
        DBPopulator.populate();
    }

    @Test
    public void testGetById() {
        User user = userDao.getUserById(100000);
        Assert.assertNotNull(user);
        LOG.info(user.toString());
    }

    @Test
    public void testGetAll() {
        List<User> users = userDao.getAllUsers();
        Assert.assertNotNull(users);
        for (User user : users) {
            LOG.info(user.toString());
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setFullName("Test Name");
        user.setEmail("test@email.com");
        user.setFlag(UserFlag.active);

        userDao.insertUser(user);
        Assert.assertTrue(user.getId() != 0);
        User createdUser = userDao.getUserById(user.getId());
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(user.getFullName(), createdUser.getFullName());

    }

    @Test
    public void testUpdate() {
        User user = userDao.getUserById(100001);
        user.setFullName("New Test Name");
        userDao.updateUser(user);
        User updatedUser = userDao.getUserById(100001);
        Assert.assertEquals(user.getFullName(), updatedUser.getFullName());
    }

    @Test
    public void testDelete() {
        User user = userDao.getUserById(100002);
        userDao.deleteUser(user.getId());
        User deletedUser = userDao.getUserById(100002);
        Assert.assertNull(deletedUser);
    }

}
