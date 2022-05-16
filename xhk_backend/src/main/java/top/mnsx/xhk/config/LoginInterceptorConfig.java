package top.mnsx.xhk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mnsx.xhk.interceptor.LoginInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器配置
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor(stringRedisTemplate);
        List<String> patterns = new ArrayList<>();
        patterns.add("/users/login");
        patterns.add("/css/**");
        patterns.add("/img/**");
        patterns.add("/js/**");
        patterns.add("/login.html");
        patterns.add("/home.html");
        patterns.add("/swagger-ui.html");
        patterns.add("/qrcode/**");
        patterns.add("/vcode/**");
        patterns.add("/users/save_user");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
