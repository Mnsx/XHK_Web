package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.utils.JWTUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

/*    @GetMapping("/find_user_by_search")
    public Map<String, Object> findUserBySearch(String username) {
        List<User> userList = userService.findUserBySearch(username);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", userList);
        return response;
    }*/

    @DeleteMapping("/delete_by_uid/{uid}")
    public Map<String, Object> deleteByUid(@PathVariable("uid") Long uid) {
        userService.deleteByUid(uid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @PutMapping("/modified_user_by_uid")
    public Map<String, Object> modifiedUserByUid(@RequestBody String data) {
        User user = JSON.parseObject(data, User.class);
        userService.modifiedUserByUid(user, user.getUid());
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @GetMapping("/find_user_by_uid/{uid}")
    public Map<String, Object> findUserByUid(@PathVariable("uid") Long uid) {
        User data = userService.findUserByUid(uid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        return response;
    }

    @PostMapping("/save_user")
    public Map<String, String> saveUser(@RequestBody String data) {
        User user = JSON.parseObject(data, User.class);
        userService.saveUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("state", String.valueOf(HttpServletResponse.SC_OK));
        return map;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody String data) {
        Map<String, String> reqData = JSON.parseObject(data, Map.class);
        // 登录逻辑
        User result = userService.login(reqData.get("username"), reqData.get("password"));
        // 生成token
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(result.getUid()));
        map.put("username", result.getUsername());
        String token = JWTUtils.createToken(map, result.getCode());
        // 将密钥保存在session中
        redisTemplate.opsForHash().put(result.getUid().toString(), "code", result.getCode());
        redisTemplate.opsForHash().put(result.getUid().toString(), "username", result.getUsername());
        // 配置返回数据
        Map<String, String> response = new HashMap<>();
        response.put("state", String.valueOf(HttpServletResponse.SC_OK));
        response.put("token", token);
        response.put("uid", String.valueOf(result.getUid()));
        response.put("username", String.valueOf(result.getUsername()));
        return response;
    }

    @GetMapping("/list_by_search/{curPage}")
    public Map<String, Object> list(@PathVariable("curPage") Integer curPage, String username) {
        // 获取数据
        List<User> data = userService.listUser(curPage, username);
        Integer count = userService.getCount(username);
        // 配置返回数据
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        response.put("count", count);
        return response;
    }
}
