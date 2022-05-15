package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.User;

import java.util.List;

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

    /**
     * 展示用户数据
     * @return 返回用户数据的集合
     */
    List<User> listUser(Integer curPage, String username);

    /**
     * 通过用户编号展示用户信息
     * @param uid 用户编号
     * @return 用户信息
     */
    User findUserByUid(Long uid);

    /**
     * 根据用户id修改用户信息
     * @param user 用户信息
     * @param uid 用户编号
     */
    void modifiedUserByUid(User user, Long uid);

    /**
     * 根据用户id删除用户信息
     * @param uid
     */
    void deleteByUid(Long uid);

    /**
     * 获取用户数据总数
     * @return 返回用户数据总数
     */
    Integer getCount(String like);

    /**
     * 模糊查询用户信息
     * @param username 模糊查询用户名
     * @return 返回用户信息
     */
    List<User> findUserBySearch(String username);

    /**
     * 更新头像
     * @param avatar 头像url
     * @param uid 用户编号
     */
    void modifiedAvatar(String avatar, Long uid);

    /**
     * 修改密码
     * @param oldPass 旧密码
     * @param newPass 新密码
     */
    void modifiedPass(String oldPass, String newPass, Long uid);
}
