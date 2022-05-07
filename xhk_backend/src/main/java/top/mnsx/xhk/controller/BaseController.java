package top.mnsx.xhk.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.mnsx.xhk.controller.ex.FileSizeMoreMaxSizeException;
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
           result.put("message", "用户名已存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.put("state", "4001");
            result.put("message", "输入密码错误");
        } else if (e instanceof AdvertisingNotFoundException) {
            result.put("state", "4002");
            result.put("message", "广告不存在异常");
        } else if (e instanceof TicketNotFoundException) {
            result.put("state", "4007");
            result.put("message", "优惠券不存在异常");
        }  else if (e instanceof UsernameNotFoundException) {
            result.put("state", "4003");
            result.put("message", "用户数据不存在");
        } else if (e instanceof StoreNameDuplicatedException) {
            result.put("state", "4004");
            result.put("message", "店名重复");
        } else if (e instanceof UserInfoWrongException) {
            result.put("state", "4005");
            result.put("message", "用户信息错误");
        } else if (e instanceof StoreNotFoundException) {
            result.put("state", "4006");
            result.put("message", "店名不存在");
        } else if (e instanceof InsertException) {
            result.put("state", "5000");
            result.put("message", "插入数据时产生未知异常");
        } else if (e instanceof UpdateException) {
            result.put("state", "5001");
            result.put("message", "修改数据时产生未知异常");
        } else if (e instanceof DeleteException) {
            result.put("state", "5002");
            result.put("message", "删除数据时产生未知异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.put("state", "6000");
            result.put("message", "图片文件上传未能接收异常");
        } else if (e instanceof FileSizeMoreMaxSizeException) {
            result.put("state", "6001");
            result.put("message", "文件大小超过最大限制异常");
        }

        return result;
    }
}
