package top.mnsx.xhk.service.impl;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.xhk.dao.StoreDao;
import top.mnsx.xhk.dao.TicketDao;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.Ticket;
import top.mnsx.xhk.service.ITicketService;
import top.mnsx.xhk.service.ex.*;
import top.mnsx.xhk.vo.TicketVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private StoreDao storeDao;

    @Override
    public List<TicketVO> listAll(Integer curPage) {
        int startPage = curPage * 7;
        List<Ticket> ticketList = ticketDao.findAll(startPage);
        List<TicketVO> res = new ArrayList<>();
        for (Ticket t : ticketList) {
            Store result = storeDao.findStoreBySid(t.getSid());
            if (result == null) {
                throw new StoreNotFoundException("店家信息不存在");
            }
            res.add(new TicketVO(t, result.getStoreName()));
        }
        return res;
    }

    @Override
    public Integer getCount() {
        return ticketDao.getCount();
    }

    @Override
    public void addTicket(Long sid, Double demand, Double discount) {
        Store result = storeDao.findStoreBySid(sid);
        if (result == null) {
            throw new StoreNotFoundException("店家信息不存在");
        }
        Integer rows = ticketDao.insertTicket(sid, demand, discount, result.getCreatedUser(), new Date());
        if (rows != 1) {
            throw new InsertException("插入优惠券数据时产生未知异常");
        }
    }

    @Override
    public void removeTicket(Long tid) {
        Ticket ticket = ticketDao.findTicketByTid(tid);
        if (ticket == null) {
            throw new TicketNotFoundException("优惠券不存在");
        }
        Integer rows = ticketDao.deleteByTid(tid);
        if (rows != 1) {
            throw new DeleteException("删除优惠券时产生未知异常");
        }
    }

    @Override
    public Ticket getTicketByTid(Long tid) {
        return ticketDao.findTicketByTid(tid);
    }

    @Override
    public void modifiedTicket(Ticket ticket, String username) {
        Integer rows = ticketDao.updateTicket(ticket, username, new Date());
        if (rows != 1) {
            throw new UpdateException("跟新优惠券数据时产生未知错误");
        }
    }
}
