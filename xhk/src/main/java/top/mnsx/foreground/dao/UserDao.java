package top.mnsx.foreground.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.mnsx.foreground.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
