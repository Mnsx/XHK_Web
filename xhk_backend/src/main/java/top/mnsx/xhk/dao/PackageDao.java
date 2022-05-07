package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.Package;

import java.util.Date;
import java.util.List;

@Mapper
public interface PackageDao {
    /**
     * 获取所有的券包数据
     * @param startPage 当前页数
     * @return 返回所有的全包数据
     */
    List<Package> findAll(@Param("startPage") Integer startPage);

    /**
     * 获取数据库中所有数据的数量
     * @return 返回数据库中所有数据的数量
     */
    Integer getCount();

    /**
     * 添加新的券包数据
     * @param uid 用户id
     * @param tid 优惠券id
     * @param username 用户名
     * @param now 时间
     * @return 返回受影响行数
     */
    Integer insertPackage(@Param("uid")Long uid, @Param("tid")Long tid, @Param("username")String username, @Param("now") Date now);

    /**
     * 删除券包数据通过券包id
     * @param pid 券包编号
     * @return 返回受影响的行数
     */
    Integer deletePackage(@Param("pid")Long pid);

    /**
     * 删除券包数据通过用户id
     * @param uid 用户id
     * @return 返回受影响的行数
     */
    Integer deletePackageByUid(@Param("uid")Long uid);
}
