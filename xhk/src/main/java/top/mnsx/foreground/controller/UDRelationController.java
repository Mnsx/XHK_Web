package top.mnsx.foreground.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.entity.DiscountWithTime;
import top.mnsx.foreground.entity.UDRelation;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.DiscountService;
import top.mnsx.foreground.service.UDRelationService;
import top.mnsx.foreground.service.UserService;
import top.mnsx.foreground.utils.UnCheck;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 关于多表，用户和优惠券关联的controller
 */
@RestController
@RequestMapping("/user_discount")
@CrossOrigin
public class UDRelationController {
    /**
     * 自动装配，关联的Service层
     */
    @Autowired
    private UDRelationService udRelationService;
    /**
     * 自动装配，优惠券Service
     */
    @Autowired
    private DiscountService discountService;
    /**
     * 自动装配，用户Service
     */
    @Autowired
    private UserService userService;

    /**
     * 通过用户id来查找，当前用户所拥有的所有优惠券
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public String listDiscountOfUser(@PathVariable("id") Long userId){
        List<Long> longList = udRelationService.getDiscountIdByUserId(userId);
        List<Discount> discountList = new ArrayList<>();
        for(Long l : longList){
            Discount discount = discountService.listById(l);
            discountList.add(discount);
        }
        return JSON.toJSONString(discountList);
    }

    /**
     * 当用户使用优惠券后，删除用户的优惠券，并且扣除用户所使用的对应jine
     * @param response
     * @param userId
     * @param discountId
     * @throws IOException
     */
    @UnCheck
    @GetMapping("/d/{userId}/{discountId}")
    public void list(HttpServletResponse response, @PathVariable("userId") Long userId, @PathVariable("discountId") Long discountId) throws IOException, IndexOutOfBoundsException {
        udRelationService.updateInfo(userId, discountId);
        udRelationService.deleteOneInfo(userId, discountId);
        Discount discount = discountService.listById(discountId);
        User user = userService.getUserById(userId);
        user.setBalance(user.getBalance() - discount.getMoney());
        userService.updateBalance(user);
//        response.sendRedirect("http://localhost/xhk/success.html/" + discount.getMoney() + "/" + discount.getStoreName());
        response.sendRedirect("http://mnsx.top/xhk/success.html/" + discount.getMoney() + "/" + discount.getStoreName());
//        response.sendRedirect("http://mnsx.top/xhk/success.html");
    }

    /**
     * 搜索功能
     */
    @PostMapping("/search")
    public String doSearch(@RequestBody String searchText, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Discount> search = udRelationService.search(searchText, user);
        return JSON.toJSONString(search);
    }
    @PostMapping("/search_down")
    public String doSearchDown(@RequestBody String searchText, HttpSession session){
        User user = (User) session.getAttribute("user");
        Set<String> search = udRelationService.searchDown(searchText, user);
        return JSON.toJSONString(search);
    }

    //消息功能
    @GetMapping("/message")
    public String getMessage(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<UDRelation> timeOut = udRelationService.selectTimeOut(user);
        List<UDRelation> udRelations = udRelationService.listDeletedById(user);
        List<DiscountWithTime> list = new ArrayList<>();
        for(UDRelation ud : udRelations){
            if(timeOut.contains(ud)){
                continue;
            }
            Discount discount = discountService.listById(ud.getDiscountId());
            String simpleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ud.getDeleteTime());
            list.add(new DiscountWithTime(discount.getMoney(), discount.getStoreName(), simpleTime));
        }
        return JSON.toJSONString(list);
    }
}
