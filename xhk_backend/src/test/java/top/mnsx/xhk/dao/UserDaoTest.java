package top.mnsx.xhk.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.User;

import java.util.Date;
import java.util.List;

/**
 * 用户持久层测试类
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void addUser() {
        User user = new User("mnsx", "123123", "123123", "110", "Mnsx_x", new Date(), "Mnsx_x", new Date());
        userDao.addUser(user);
    }

    @Test
    public void findUserByUsername() {
        System.out.println(userDao.findUserByUsername("test1"));
    }

    @Test
    public void findAll() {
        List<User> userList = userDao.findAll(1, "test");
        System.out.println(userList);
    }

    @Test
    public void findUserByUid() {
        User user = userDao.findUserByUid(5L);
        System.out.println(user.getModifiedUser());
        System.out.println(user);
    }

    @Test
    public void updateUserByUid() {
        User user = new User();
        user.setUid(5L);
        user.setEmail("1527030652@qq.com");
        user.setModifiedUser("Mnsx_x");
        user.setModifiedTime(new Date());
        Integer row = userDao.updateUserByUid(user, 5L);
        System.out.println(row);
    }

    @Test
    public void deleteByUid() {
        Integer rows = userDao.deleteByUid(6L);
        System.out.println(rows);
    }

    @Test
    public void getCount() {
        System.out.println(userDao.getCount("test"));
    }

    @Test
    public void findUserBySearch() {
        List<User> user = userDao.findUserBySearch("%test%");
        user.forEach(System.out::println);
    }
}
