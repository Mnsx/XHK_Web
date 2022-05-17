package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.User;

import java.util.List;

/**
 * 商店持久层接口
 */
@Mapper
public interface StoreDao {
    /**
     * 查看所有的商店信息
     * @param StoreName 模糊商店信息
     * @return 返回所有匹配的商家集合
     */
    List<Store> findAll(@Param("startPage")Integer startPage, @Param("storeName")String StoreName);

    /**
     * 返回模糊查询结果数量
     * @param like 模糊查询内容
     * @return 返回匹配结果的个数
     */
    Integer getCount(@Param("like")String like);

    /**
     * 加入商家
     * @param store 商家类
     * @return 返回受影响行数
     */
    Integer insertStore(@Param("store") Store store);

    /**
     * 获取商家信息通过商家名
     * @param storeName 商家名称
     * @return 返回匹配的商家信息
     */
    Store getStoreByStoreName(@Param("storeName")String storeName);

    /**
     * 根据店家id删除店家数据
     * @param sid 店家id
     * @return 返回受影响行数
     */
    Integer deleteBySid(@Param("sid")Long sid);

    /**
     * 根据商家id查询商家信息
     * @param sid 商家编号
     * @return 返回商家信息
     */
    Store findStoreBySid(@Param("sid")Long sid);

    /**
     * 更新商家信息
     * @param store 商家信息
     * @param uid 用户编号
     * @return 返回受影响行数
     */
    Integer modifiedStore(@Param("store")Store store);

    Integer deleteStoreByUid(@Param("uid")Long uid);
}
