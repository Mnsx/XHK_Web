package top.mnsx.xhk.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import top.mnsx.xhk.service.ex.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制层的基类
 */
public class BaseController {

    @ExceptionHandler({ServiceException.class})
    public Map<String, String> handleException(Throwable e) {
        Map<String, String> result = new HashMap<>();

        if (e instanceof UsernameDuplicatedException) {
           result.put("state", "4000");
           result.put("massage", "用户名已存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.put("state", "4001");
            result.put("massage", "输入密码错误");
        } else if (e instanceof UsernameNotFoundException) {
            result.put("state", "4003");
            result.put("massage", "用户数据不存在");
        } else if (e instanceof InsertException) {
            result.put("state", "5000");
            result.put("massage", "插入数据时产生未知异常");
        }

        return result;
    }
}
