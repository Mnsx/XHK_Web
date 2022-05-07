package top.mnsx.xhk.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Ticket;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TicketDaoTest {
    @Autowired
    private TicketDao ticketDao;

    @Test
    public void findAll() {
        List<Ticket> all = ticketDao.findAll(0);
        all.forEach(System.out::println);
    }

    @Test
    public void getCount() {
        Integer count = ticketDao.getCount();
        System.out.println(count);
    }

    @Test
    public void InsertTicket() {
        Integer rows = ticketDao.insertTicket(2L, 100D, 100D, "Mnsx_x", new Date());
        System.out.println(rows);
    }

    @Test
    public void deleteByTid() {
        System.out.println(ticketDao.deleteByTid(15L));
    }

    @Test
    public void findTicketByTid() {
        Ticket ticket = ticketDao.findTicketByTid(1L);
        System.out.println(ticket);
    }

    @Test
    public void updateTicket() {
        Ticket ticket = new Ticket();
        ticket.setTid(1L);
        ticket.setDemand(10000D);
        System.out.println(ticketDao.updateTicket(ticket, "mnsx", new Date()));
    }
}
