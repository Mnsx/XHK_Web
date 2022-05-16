package top.mnsx.xhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("get_message/{from}/{to}")
    public Map<String, Object> getMessage(@PathVariable("from") String from, @PathVariable("to") String to) {
        List<String> data = new ArrayList<>();
        while (redisTemplate.opsForList().size(to + ":" + from) != 0) {
            String s = redisTemplate.opsForList().rightPop(to + ":" + from);
            data.add(s);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpServletResponse.SC_OK);
        response.put("data", data);
        return response;
    }
}
