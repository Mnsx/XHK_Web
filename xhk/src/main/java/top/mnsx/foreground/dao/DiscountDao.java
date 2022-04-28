package top.mnsx.foreground.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.mnsx.foreground.entity.Discount;

@Mapper
public interface DiscountDao extends BaseMapper<Discount> {

}
