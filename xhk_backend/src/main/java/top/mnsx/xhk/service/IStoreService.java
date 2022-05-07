package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.vo.StoreVO;

import java.util.List;

/**
 * 商家业务逻辑层接口
 */
public interface IStoreService {
    /**
     * 获取所有商家的信息
     * @param searchText 模糊查询搜索框中的内容
     * @param curPage 当前页数
     * @return 返回所有用户的集合
     */
    List<Store> listStore(String searchText, Integer curPage);

    /**
     * 获取匹配的数量
     * @param searchText 搜索的内容
     * @return 返回符合条件的数量
     */
    Integer getCount(String searchText);

    /**
     * 添加商家
     * @param store 商店类
     */
    void addStore(Store store, String username);

    /**
     * 删除指定编号的商家
     * @param sid 商家id
     */
    void removeStoreBySid(Long sid);

    /**
     * 根据商家id查询商家数据
     * @param sid 商家id
     * @return 返回对应的商家信息
     */
    StoreVO findOneBySid(Long sid);

    /**
     * 根据sid修改商家信息
     * @param store 修改后商家信息
     * @param username 所属人信息
     */
    void modifiedStoreBySid(Store store, String username);

    void removeStoreByUid(Long uid);
}
