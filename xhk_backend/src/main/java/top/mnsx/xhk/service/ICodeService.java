package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.Code;

import java.util.List;

/**
 * 注册码业务逻辑层接口
 */
public interface ICodeService {
    /**
     * 展示所有注册码数据
     * @param curPage 当前页数
     * @return 返回所有注册码数据
     */
    List<Code> listAll(Integer curPage);

    /**
     * 获取数据库中数据总数
     * @return 返回数据库中数据总数
     */
    Integer getCount();

    /**
     * 添加注册码
     * @param code 注册码
     */
    void addCode(String code);

    /**
     * 删除注册码
     * @param cid 注册码id
     */
    void removeCode(Long cid);
}
