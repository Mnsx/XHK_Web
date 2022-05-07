package top.mnsx.xhk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Ticket;

import java.util.Date;

@SpringBootTest
public class TicketServiceTest {
    @Autowired
    private ITicketService ticketService;

    @Test
    public void listAll() {
        ticketService.listAll(0);
    }

    @Test
    public void getCount() {
        ticketService.getCount();
    }

    @Test
    public void addTicket() {
        ticketService.addTicket(2L, 100D, 100D);
    }

    @Test
    public void findTicketByTid() {
        ticketService.getTicketByTid(1L);
    }

    @Test
    public void modifiedTicket() {
        Ticket ticket = new Ticket();
        ticket.setTid(1L);
        ticket.setDemand(10000D);
        ticketService.modifiedTicket(ticket, "mnsx_x");
    }
}
