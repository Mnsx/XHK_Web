package top.mnsx.xhk.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.User;

import java.util.Date;
import java.util.List;

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

    /**
     * 展示所有的用户信息
     * @return 返回所有用户信息的集合
     */
    List<User> findAll(@Param("startPage")Integer startPage, @Param("username")String username);

    /**
     * 通过用户id返回用户信息
     * @param uid 用户id
     * @return 返回对应的用户信息
     */
    User findUserByUid(@Param("uid") Long uid);

    /**
     * 修改用户信息通过uid
     * @param user 用户信息
     * @param uid 用户编号
     * @return 返回被修改的行数
     */
    Integer updateUserByUid(@Param("user") User user, @Param("uid") Long uid);

    /**
     * 根据用户id删除用户信息
     * @param uid 用户id
     * @return 返回受影响的行数
     */
    Integer deleteByUid(@Param("uid") Long uid);

    /**
     * 获取用户数据总数
     * @return 返回用户数据的总数
     */
    Integer getCount(@Param("like") String like);

    /**
     * 模糊查询用户数据
     * @param username 用户名
     * @return 返回用户信息
     */
    List<User> findUserBySearch(@Param("username") String username);
}
