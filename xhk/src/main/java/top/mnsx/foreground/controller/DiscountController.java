package top.mnsx.foreground.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.service.DiscountService;

import java.util.List;

/**
 * 优惠券Controller层
 */
@RestController("discountController")
@RequestMapping("/discount")
@CrossOrigin
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    /**
     * 展示所有券的种类
     * @return
     */
    @GetMapping()
    public String list(){
        List<Discount> discountList = discountService.list();
        return JSON.toJSONString(discountList);
    }
}
