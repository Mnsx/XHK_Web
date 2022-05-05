package top.mnsx.xhk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.User;

import java.util.Date;
import java.util.List;

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

    @Test
    public void listUser() {
        System.out.println(userService.listUser(1, "test"));
    }

    @Test
    public void findUserByUid() {
        System.out.println(userService.findUserByUid(5L));
    }

    @Test
    public void updateUserByUid() {
        User user = new User();
        user.setPhone("12345");
        user.setModifiedUser("Mnsx_x");
        user.setModifiedTime(new Date());
        userService.modifiedUserByUid(user, 5L);
    }

    @Test
    public void deleteByUid() {
        userService.deleteByUid(7L);
    }

    @Test
    public void getCount() {
        System.out.println(userService.getCount("test"));
    }

    @Test
    public void findUserBySearch() {
        List<User> userBySearch = userService.findUserBySearch("%test%");
        userBySearch.forEach(System.out::println);
    }

}
