package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import top.mnsx.xhk.entity.Advertising;

import java.util.Date;
import java.util.List;

/**
 * 广告持久层接口
 */
@Mapper
public interface AdvertisingDao {
    /**
     * 添加用户信息
     * @param url 图片路径
     * @param username 创建着
     * @param now 创建时间
     * @return 返回受影响行数
     */
    Integer insertAD(@Param("url")String url, @Param("username")String username, @Param("now")Date now, @Param("file")String file);

    /**
     * 返回所有的图片路径
     * @return 返回所有图片路径
     */
    List<Advertising> findAll();

    /**
     * 根据广告id删除对应广告
     * @param aid 广告id
     * @return 返回受影响的行数
     */
    Integer removeADByAid(@Param("aid") Long aid);

    /**
     * 根据aid查询广告
     * @param aid 广告id
     * @return 返回查询到的广告信息
     */
    Advertising findByAid(@Param("aid") Long aid);

    /**
     * 获取所有的广告
     * @return 返回所有广告的集合
     */
    List<Advertising> getAll();
}
