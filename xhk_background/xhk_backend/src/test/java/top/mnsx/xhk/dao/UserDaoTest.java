package top.mnsx.xhk.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.User;

import java.util.Date;

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
        System.out.println(userDao.findUserByUsername("mnsx"));
    }
}
