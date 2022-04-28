package top.mnsx.foreground.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import top.mnsx.foreground.entity.UDRelation;

import java.util.List;

@Mapper
public interface UDRelationDao extends BaseMapper<UDRelation> {
    /**
     * 通过用户id查找其所拥有的所有优惠券
     * @param userId
     * @return
     */
    @ResultType(java.lang.Long.class)
    @Select("select discount_id from xhk_discount_user where user_id = #{id} and deleted != 1 order by create_time")
    List<Long> selectDIdByUId(Long userId);

    @ResultType(top.mnsx.foreground.entity.UDRelation.class)
    @Select("select * from xhk_discount_user where user_id = #{id} and deleted = 1 order by create_time desc")
    List<UDRelation> selectDeleted(Long userId);
}
