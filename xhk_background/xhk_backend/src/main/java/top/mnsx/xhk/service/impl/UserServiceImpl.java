package top.mnsx.xhk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.mnsx.xhk.dao.UserDao;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.service.ex.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户逻辑层接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        // 查找用户名是否被使用
        String username = user.getUsername();
        User result = userDao.findUserByUsername(username);
        if (result != null) {
            throw new UsernameDuplicatedException("用户名已经被使用");
        }

        // 生成随机密钥
        String code = UUID.randomUUID().toString().toUpperCase();
        // 用户密码加密
        String old = user.getPassword();
        String password = getMd5Password(old, code);
        user.setPassword(password);

        // 跟新用户数据
        user.setCode(code);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(new Date());
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());

        // 添加用户
        Integer rows = userDao.addUser(user);
        if (rows != 1) {
            throw new InsertException("新建用户时产生未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 判断用户名不存在的情况
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

        // 判断用户输入密码与用户信息是否匹配
        password = getMd5Password(password, user.getCode());
        if (!user.getPassword().equals(password)) {
            throw new PasswordNotMatchException("输入密码错误");
        }
        if (user.getDeleted() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

        User result = new User();
        result.setUid(user.getUid());
        result.setUsername(user.getUsername());
        result.setAvatar(user.getAvatar());

        return user;
    }

    @Override
    public List<User> listUser(Integer curPage, String username) {
        int startPage = curPage * 7;
        username = "%" + username + "%";
        return userDao.findAll(startPage, username);
    }

    @Override
    public User findUserByUid(Long uid) {
        User result = userDao.findUserByUid(uid);
        result.setPassword(null);
        result.setCode(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }

    @Override
    public void modifiedUserByUid(User user, Long uid) {
        User result = userDao.findUserByUid(uid);
        if (result == null) {
            throw new UsernameNotFoundException("用户信息不存在");
        }
        user.setModifiedUser(result.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userDao.updateUserByUid(user, uid);
        if (rows != 1) {
            throw new UpdateException("修改用户数据时产生了未知的异常");
        }
    }

    @Override
    public void deleteByUid(Long uid) {
        User result = userDao.findUserByUid(uid);
        if (result == null) {
            throw new UsernameNotFoundException("用户信息不存在");
        }
        Integer rows = userDao.deleteByUid(uid);
        if (rows != 1) {
            throw new DeleteException("删除用户数据时产生了未知的异常");
        }
    }

    @Override
    public Integer getCount(String like) {
        like = "%" + like + "%";
        return userDao.getCount(like);
    }

    @Override
    public List<User> findUserBySearch(String username) {
        username = "%" + username + "%";
        return userDao.findUserBySearch(username);
    }

    /**
     * 使用Md5进行加密
     * @param password 旧密码
     * @param code 密钥
     * @return 返回加密后的密码
     */
    private String getMd5Password(String password, String code) {
        for (int i = 0; i < 3; ++i) {
            password = DigestUtils.md5DigestAsHex((code + password + code).getBytes(StandardCharsets.UTF_8)).toUpperCase();
        }
        return password;
    }
}
