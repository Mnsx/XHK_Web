package top.mnsx.xhk.config;

import org.springframework.context.annotation.Configuration;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        List<String> patterns = new ArrayList<>();
        patterns.add("/users/login");
        patterns.add("/css/**");
        patterns.add("/img/**");
        patterns.add("/js/**");
        patterns.add("/login.html");
        patterns.add("/home.html");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
