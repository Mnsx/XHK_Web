package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.Ticket;
import top.mnsx.xhk.service.ITicketService;
import top.mnsx.xhk.vo.TicketVO;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController extends BaseController{
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/list_all/{curPage}")
    public Map<String, Object> listAll(@PathVariable("curPage") Integer curPage) {
        List<TicketVO> data = ticketService.listAll(curPage);
        Map<String, Object> response = new HashMap<>();
        Integer count = ticketService.getCount();
        response.put("count", count);
        response.put("data", data);
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @PostMapping("/add_ticket")
    public Map<String, Object> addTicket(@RequestBody String json) {
        Map<String, String> map = JSON.parseObject(json, Map.class);
        ticketService.addTicket(Long.valueOf(map.get("sid")), Double.valueOf(map.get("demand")), Double.valueOf(map.get("discount")));
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @DeleteMapping("/remove_ticket_by_tid/{tid}")
    public Map<String, Object> removeTicketByTid(@PathVariable("tid")Long tid) {
        ticketService.removeTicket(tid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @GetMapping("/get_ticket_by_tid/{tid}")
    public Map<String, Object> getTicketByTid(@PathVariable("tid")Long tid) {
        Ticket data = ticketService.getTicketByTid(tid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        return response;
    }

    @PutMapping("/modified_ticket")
    public Map<String, Object> modifiedStoreBySid(@RequestBody String data) {
        Ticket ticket = JSON.parseObject(data, Ticket.class);
        ticketService.modifiedTicket(ticket, ticket.getModifiedUser());
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
