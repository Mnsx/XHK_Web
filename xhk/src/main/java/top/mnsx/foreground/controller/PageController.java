package top.mnsx.foreground.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.UserService;
import top.mnsx.foreground.utils.VCodeUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 页面跳转或者加载页面数据的Controller
 */
@Controller("pageController")
@CrossOrigin
public class PageController {
    /**
     * 自动装配用户的Service层
     */
    @Autowired
    private UserService userService;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/first.html")
    public String getFirst(){
        return "first";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String getLogin(){
        return "login";
    }

    /**
     * 记住密码后，传cookie中的用户id，查询账号密码，展示在前端
     * @param id
     * @return
     */
    @RequestMapping("/login/{id}")
    @ResponseBody
    public String loginUi(@PathVariable("id") Long id, HttpSession session){
        User user = userService.getUserById(id);
        session.setAttribute("user", user);
        return JSON.toJSONString(user);
    }

    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping("/register.html")
    public String getRegister()  {
        return "register";
    }

    /**
     * 返回注册是的验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/register")
    public void getVCode(HttpServletResponse response, HttpSession session) throws IOException {
        String vCode = VCodeUtil.getVCode(response);
        session.setAttribute("vCode", vCode.toLowerCase());
    }

    /**
     * 跳转到我的页面
     * @return
     */
    @RequestMapping("/my.html")
    public String getMy(){
        return "my";
    }

    /**
     * 跳转到消息界面
     * @return
     */
    @RequestMapping("/message.html")
    public String getMessage(){
        return "message";
    }

    /**
     * 验证成功界面
     * @return
     */
    @RequestMapping("/success.html/{money}/{storeName}")
    public String getSuccess(HttpServletResponse res, @PathVariable("money")String money, @PathVariable("storeName")String storeName) throws IOException {
        res.setCharacterEncoding("utf-8");
        res.getWriter().write("<script>alert('这张优惠券价值" + money + "，这是来自于" + storeName + "')</script>");
        return "success";
    }

    @RequestMapping("/personalInfo.html")
    public String getPersonalInfo(){
        return "personal_info";
    }

    @RequestMapping("/changepassword.html")
    public String getChangePassword(){
        return "change_psw";
    }

    @RequestMapping("/confirmpassword.html")
    public String getConfirmPassword(){
        return "confirm_psw";
    }

    @RequestMapping("/add_money.html")
    public String getAddMoney(){
        return "add_money";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        return "first";
    }
}
