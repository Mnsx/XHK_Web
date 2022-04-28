package top.mnsx.foreground.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.foreground.dao.KeyDao;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.entity.Key;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.DiscountService;
import top.mnsx.foreground.service.KeyService;
import top.mnsx.foreground.service.UDRelationService;
import top.mnsx.foreground.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 注册码的Service层
 */
@Service
public class KeyServiceImpl implements KeyService {
    @Autowired
    private KeyDao keyDao;
    @Autowired
    private UDRelationService udRelationService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private UserService userService;

    /**
     * 用户使用注册码，进行校验，并且判断，所有注册码是否使用完毕,完毕后添加注册码
     * @return
     */
    @Override
    public Boolean useKey(String keyCode, Long curId) {
        User user = userService.getUserById(curId);
        boolean flag = false;
        HashMap<String, Object> map = new HashMap<>();
        map.put("key_code", keyCode);;
        List<Key> keys = keyDao.selectByMap(map);
        if(!keys.isEmpty()){
            int i = keyDao.deleteByMap(map);
            if(i > 0){
                List<Discount> discounts = discountService.list();
                ArrayList<Long> longs = new ArrayList<>();
                int balance = user.getBalance();
                for(Discount d : discounts){
                    longs.add(d.getId());
                    balance += d.getMoney();
                }
                flag = udRelationService.addRelationByDiscountId(curId, longs);
                user.setBalance(balance);
                userService.updateBalance(user);
            }
        }
        if(keyDao.selectList(null).isEmpty()){
            for(int i = 0; i < 100; ++i){
                StringBuffer sb = new StringBuffer();
                for(int j = 0; j < 5; ++j){
                    sb.append((char)(Math.random() * 80 + 40));
                }
                int insert = keyDao.insert(new Key(sb.toString()));
                flag = insert > 0;
            }
        }
        return flag;
    }
}
