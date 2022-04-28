package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.User;

/**
 * 用户持久层接口
 */
@Mapper
public interface UserDao {
    /**
     * 添加用户功能
     * @param user 用户类
     */
    Integer addUser(@Param("user") User user);

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 返回查找到的用户类
     */
    User findUserByUsername(@Param("username") String username);
}
