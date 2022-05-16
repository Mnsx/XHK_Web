package top.mnsx.xhk.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mnsx.xhk.utils.VCodeUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
public class VCoder {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/vcode/{date}")
    public void getVCode(HttpServletResponse response, @PathVariable("date")String date) throws IOException {
        String vCode = VCodeUtil.getVCode(response);
        redisTemplate.opsForValue().set("date:" + date, vCode, 120, TimeUnit.SECONDS);
    }
}
