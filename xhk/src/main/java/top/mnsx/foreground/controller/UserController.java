package top.mnsx.foreground.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * 用户的Controller
 */
@Slf4j
@RestController("userController")
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    /**
     * 自动装配用户Service
     */
    @Autowired
    private UserService userService;

    /**
     * 展示所拥有的所有用户
     * @return
     */
    @GetMapping()
    public String list(){
        List<User> userList = userService.list();
        return JSON.toJSONString(userList.toString());
    }

    @PutMapping()
    public String updateUser(@RequestBody String json){
        User user = JSON.parseObject(json, User.class);
        if(userService.updateUser(user)){
            return "true";
        }else{
            return "false";
        }
    }

    @PutMapping("/psd")
    public String modifyPsd(@RequestBody String json){
        HashMap<String, String> hashMap = JSON.parseObject(json, HashMap.class);
        String s = DigestUtils.md5DigestAsHex(hashMap.get("password").getBytes(StandardCharsets.UTF_8));
        User user = userService.getUserById(Long.valueOf(hashMap.get("curId")));
        return "" + user.getPassword().equals(s);
    }

    @GetMapping("/name")
    public String getName(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user.getUsername();
    }

    @PutMapping("/change_psd")
    public String changePsd(@RequestBody String json){
        HashMap<String, String> hashMap = JSON.parseObject(json, HashMap.class);
        if(hashMap.get("password").equals(hashMap.get("rePassword"))){
            User user = userService.getUserById(Long.valueOf(hashMap.get("curId")));
            user.setPassword(DigestUtils.md5DigestAsHex(hashMap.get("password").getBytes(StandardCharsets.UTF_8)));
            Boolean aBoolean = userService.updatePasswordUser(user);
            if(aBoolean){
                return true + "";
            }
            return "密码修改失败！";
        }
        return false + "两次输入密码错误！";
    }

    /**
     * 通过Id查找用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getOne(@PathVariable("id")Long id){
        User user = userService.getUserById(id);
        return JSON.toJSONString(user);
    }

    /**
     * 登录功能
     * @param json
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/login")
    public String login(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        JSONArray jo = (JSONArray) JSON.parse(json);
        JSONObject o = (JSONObject) jo.get(0);
        String remember = (String) jo.get(1);
        String password = (String) o.get("password");
        User user1 = (User) session.getAttribute("user");
        if(user1 != null && user1.getUsername().equals(o.get("username"))){
            return "true";
        }
        User user = new User(null, (String)o.get("username"), DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)), null, null);
        user = userService.login(user);
        if(user != null){
            session.setAttribute("user", user);
            if(remember.equals("1")){
                Cookie usernameCookie = new Cookie("id", user.getId().toString());
                usernameCookie.setPath("/");
                usernameCookie.setMaxAge(60 * 60 * 24 * 7);
                usernameCookie.setHttpOnly(false);
                response.addCookie(usernameCookie);
            }
            return "true";
        }
        return "false";
    }

    /**
     * 注册功能
     * @param json
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody String json, HttpSession session){
        JSONArray jo = (JSONArray) JSON.parse(json);
        JSONObject o = (JSONObject) jo.get(0);
        String vCode = (String) jo.get(1);
        String password = (String) o.get("password");
        User user = new User(null, (String)o.get("username"), DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)), (String)o.get("phoneNum"), 0);
        Object string = session.getAttribute("vCode");
        if(vCode != null && vCode.toLowerCase().equals(string)){
            if(userService.getUserByName(user.getUsername())){
                Boolean b = userService.register(user);
                return b ? "true" : "false";
            }else{
                return "用户名重复，请重新输入!";
            }
        }
        return "验证码错误，请重新输入!";
    }

    /**
     * 当用户登录后，将id传存入session中方便调用
     * @param session
     * @return
     */
    @GetMapping("/id")
    public String getId(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user.getId().toString();
    }
}
