package top.mnsx.foreground.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import top.mnsx.foreground.entity.Key;

@Mapper
public interface KeyDao extends BaseMapper<Key> {

}
