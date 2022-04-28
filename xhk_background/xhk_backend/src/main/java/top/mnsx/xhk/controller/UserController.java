package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.utils.JWTUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @PostMapping("/save_user")
    public Map<String, String> saveUser(User user) {
        userService.saveUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("state", String.valueOf(HttpServletResponse.SC_OK));
        return map;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody String json, HttpSession session) {
        Map<String, String> reqMap = JSON.parseObject(json, Map.class);
        // 登录逻辑
        User result = userService.login(reqMap.get("username"), reqMap.get("password"));
        // 生成token
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(result.getUid()));
        map.put("username", result.getUsername());
        String token = JWTUtils.createToken(map, result.getCode());
        // 将密钥保存在session中
        session.setAttribute("code", result.getCode());
        // 配置返回数据
        Map<String, String> response = new HashMap<>();
        response.put("state", String.valueOf(HttpServletResponse.SC_OK));
        response.put("token", token);
        response.put("uid", String.valueOf(result.getUid()));
        response.put("password", String.valueOf(result.getUsername()));
        return response;
    }
}
