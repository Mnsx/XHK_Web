package top.mnsx.xhk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageUploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/focus/**").addResourceLocations("file:D:\\WorkSpace\\XHK\\xhk_backend\\src\\main\\resources\\static\\img\\focus\\");
        registry.addResourceHandler("/img/avatar/**").addResourceLocations("file:D:\\WorkSpace\\XHK\\xhk_backend\\src\\main\\resources\\static\\img\\avatar\\");
        registry.addResourceHandler("/img/store/**").addResourceLocations("file:D:\\WorkSpace\\XHK\\xhk_backend\\src\\main\\resources\\static\\img\\store\\");
    }
}
