package top.mnsx.xhk.service;

import top.mnsx.xhk.entity.Ticket;
import top.mnsx.xhk.vo.TicketVO;

import java.util.Date;
import java.util.List;

public interface ITicketService{
    /**
     * 返回所有的优惠券数据
     * @param curPage 对应的页数
     * @return 返回对应页数的优惠券数据
     */
    List<TicketVO> listAll(Integer curPage);

    /**
     * 返回数据库中所有的数据的数量
     * @return 返回数据库中所有数据的数量
     */
    Integer getCount();

    /**
     * 插入新的优惠券
     * @param sid 商家编号
     * @param demand 限定条件
     * @param Discount 优惠金额
     */
    void addTicket(Long sid, Double demand, Double Discount);

    /**
     * 根据优惠券id删除优惠券
     * @param tid 优惠券id
     */
    void removeTicket(Long tid);

    /**
     * 根据优惠券id查找优惠券信息
     * @param tid 优惠券id
     */
    Ticket getTicketByTid(Long tid);

    /**
     * 更新优惠券数据
     * @param ticket 优惠券数据
     * @param username 修改者信息
     */
    void modifiedTicket(Ticket ticket, String username);
}
