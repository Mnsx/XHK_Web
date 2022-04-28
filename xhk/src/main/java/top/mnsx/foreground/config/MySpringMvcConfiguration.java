package top.mnsx.foreground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mnsx.foreground.utils.PrivilegeInterceptor;

/**
 * Spring-mvc配置类
 */
@Configuration
public class MySpringMvcConfiguration implements WebMvcConfigurer {
    /**
     * 将拦截器添加到Bean中
     */
    @Bean
    public PrivilegeInterceptor getLoginInterceptor(){
        return new PrivilegeInterceptor();
    }

    /**
     * 将需要拦截的或者需要放行的数组添加到拦截器中
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/*", "/user/register", "/user/login", "/",
                        "/register.html", "/login.html", "/register", "/css/**", "/js/**",
                        "/img/**", "/success.html/**");
    }
}
