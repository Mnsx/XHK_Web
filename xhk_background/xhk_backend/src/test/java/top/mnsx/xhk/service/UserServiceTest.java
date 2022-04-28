package top.mnsx.xhk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.User;

import java.util.Date;

/**
 * 用户逻辑层测试类
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void saveUser() {
        User user = new User("test1", "123123", "123123", "110", "Mnsx_x", new Date(), "Mnsx_x", new Date());
        userService.saveUser(user);
    }

    @Test
    public void login() {
        System.out.println(userService.login("test1", "123123"));
    }
}
