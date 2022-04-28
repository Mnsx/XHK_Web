package top.mnsx.foreground.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.foreground.dao.UserDao;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.UserService;

import java.util.HashMap;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 查询所有的用户
     * @return
     */
    @Override
    public List<User> list() {
        return userDao.selectList(null);
    }

    /**
     * 通过Id查询用户
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        User user = userDao.selectById(id);
        return user;
    }

    /**
     * 通过账号密码查询用户 判断用户是否存在
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        List<User> users = userDao.selectByMap(map);
        return !users.isEmpty() ? users.get(0) : null;
    }

    /**
     * 注册功能，添加用户
     * @param user
     * @return
     */
    @Override
    public Boolean register(User user) {
        return (userDao.insert(user) > 0);
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Override
    public Boolean getUserByName(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> users = userDao.selectByMap(map);
        return users.isEmpty();
    }

    /**
     * 更新用户的金额
     * @param user
     * @return
     */
    @Override
    public Boolean updateBalance(User user) {
        return (userDao.updateById(user) > 0);
    }

    @Override
    public Boolean updateUser(User user) {
        user.setId(user.getId() - 777);
        return userDao.updateById(user) > 0;
    }

    @Override
    public Boolean updatePasswordUser(User user){
        return userDao.updateById(user) > 0;
    }
}
