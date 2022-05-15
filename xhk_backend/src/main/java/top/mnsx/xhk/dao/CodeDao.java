package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.Code;

import java.util.List;

/**
 * 注册码持久额曾接口
 */
@Mapper
public interface CodeDao {
    /**
     * 返回所有的注册码数据
     * @param startPage 当前页数
     * @return 返回注册码数据
     */
    List<Code> findAll(@Param("startPage")Integer startPage);

    /**
     * 查看注册码数量
     * @return 返回注册码页数
     */
    Integer getCount();

    /**
     * 添加注册码
     * @param code 注册码
     * @return 返回受影响行数
     */
    Integer insertCode(@Param("code") String code);

    /**
     * 删除指定的注册码
     * @param cid 注册码编号
     * @return 返回受影响行数
     */
    Integer deleteByCid(@Param("cid")Long cid);

    /**
     * 删除指定注册码
     * @param code 注册码
     * @return
     */
    Integer deleteByCode(@Param("code") String code);

}
