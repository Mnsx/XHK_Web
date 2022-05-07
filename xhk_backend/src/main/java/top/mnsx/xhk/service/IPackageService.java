package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.Package;

import java.util.List;

public interface IPackageService {
    /**
     * 展示所有的券包数据
     * @param curPage 当前页数
     * @return 返回券包数据集合
     */
    List<Package> listAll(Integer curPage);

    /**
     * 返回数据库中数据的总数
     * @return 返回数据库中所有数据的总数
     */
    Integer getCount();

    /**
     * 添加券包信息
     * @param p 全包信息
     * @param username 用户名
     */
    void addPackage(Package p, String username);

    /**
     * 删除全包数据通过券包编号
     * @param pid 券包编号
     */
    void removePackage(Long pid);

    /**
     * 通过用户id删除全包数据
     * @param uid 用户id
     */
    void removePackageByUid(Long uid);
}
