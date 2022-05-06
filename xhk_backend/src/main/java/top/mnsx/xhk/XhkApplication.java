package top.mnsx.xhk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class XhkApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhkApplication.class, args);
    }

}
