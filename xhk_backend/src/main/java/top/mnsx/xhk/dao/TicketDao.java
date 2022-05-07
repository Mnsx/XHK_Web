package top.mnsx.xhk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mnsx.xhk.entity.Ticket;

import java.util.Date;
import java.util.List;

@Mapper
public interface TicketDao {
    /**
     * 分页查询所有的优惠券数据
     * @param startPage 开始的页数
     * @return 返回对应页数的7条数据
     */
    List<Ticket> findAll(@Param("startPage")Integer startPage);

    /**
     * 返回数据库中所有数据的数量
     * @return 返回所有数据的数量
     */
    Integer getCount();

    /**
     * 插入新的优惠券类型
     * @param demand 使用优惠券的要求需求
     * @param discount 优惠券的优惠额度
     * @return 返回受影响的行数
     */
    Integer insertTicket(@Param("sid")Long sid, @Param("demand")Double demand, @Param("discount")Double discount, @Param("username")String username, @Param("now")Date now);

    /**
     * 根据优惠券编号删除优惠券
     * @param tid 优惠券id
     * @return 返回受影响的行数
     */
    Integer deleteByTid(@Param("tid")Long tid);

    /**
     * 根据优惠券id查找优惠券
     * @param tid 优惠券id
     * @return 返回被查找的优惠券id
     */
    Ticket findTicketByTid(@Param("tid")Long tid);

    /**
     * 修改优惠券信息
     * @param ticket 优惠券信息
     * @param username 用户名
     * @param now 修改时间
     * @return 返回受影响的行数
     */
    Integer updateTicket(@Param("ticket")Ticket ticket, @Param("username")String username, @Param("now")Date now);
}
