package top.mnsx.foreground.utils;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * mvc拦截器, 判断session中是否有对象信息，以保证用户已经登录
 */
public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            UnCheck methodAnnotation = handlerMethod.getMethodAnnotation(UnCheck.class);
            if(methodAnnotation != null){
                return true;
            }
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user == null){
            response.sendRedirect("/xhk/login.html");
            return false;
        }
        return true;
    }
}
