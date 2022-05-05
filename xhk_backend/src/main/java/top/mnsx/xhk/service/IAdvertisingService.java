package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.Advertising;

import java.util.Date;
import java.util.List;

/**
 * 广告业务逻辑层
 */
public interface IAdvertisingService {
    /**
     * 添加前台轮播图图片
     * @param url 图片路径
     * @param username 创建者
     */
    void saveAD(String url, String username, String file);

    /**
     * 获取所有图片信息
     * @return 获取所有图片信息
     */
    List<Advertising> findAll();

    /**
     * 删除土拍你
     * @param aid 图片id
     */
    void removeAD(Long aid);
}
