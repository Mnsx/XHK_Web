package top.mnsx.xhk.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import top.mnsx.xhk.config.LoginInterceptorConfig;
import top.mnsx.xhk.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录拦截器
 */
@CrossOrigin
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 进入程序前得拦截
     * @param request 请求类
     * @param response 响应类
     * @param handler 处理器类
     * @return 返回是否拦截
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        String token = request.getHeader("Authorization");
        Map<String, Object> map = new HashMap<>();
        String code = (String) stringRedisTemplate.opsForHash().get("5", "code");
        try {
            JWTUtils.verify(token, code);
            return true;
        } catch (TokenExpiredException e) {
            map.put("state", false);
            map.put("message", "Token已过期");
        } catch (SignatureGenerationException e) {
            map.put("state", false);
            map.put("message", "签名错误");
        } catch (AlgorithmMismatchException e) {
            map.put("state", false);
            map.put("message", "加密算法不匹配");
        } catch (Exception e) {
            map.put("state", false);
            map.put("message", "无效token");
        }
        response.sendRedirect("/login.html");
        return false;
    }
}
