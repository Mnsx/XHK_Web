package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.User;

/**
 * 用户逻辑类接口
 */
public interface IUserService {
    /**
     * 用户注册功能
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 返回登录的用户的信息
     */
    User login(String username, String password);
}
