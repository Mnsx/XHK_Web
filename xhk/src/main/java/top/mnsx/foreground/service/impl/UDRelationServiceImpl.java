package top.mnsx.foreground.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.foreground.dao.DiscountDao;
import top.mnsx.foreground.dao.UDRelationDao;
import top.mnsx.foreground.dao.UserDao;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.entity.UDRelation;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.DiscountService;
import top.mnsx.foreground.service.UDRelationService;

import java.util.*;

/**
 * 用户和优惠券关联的Service
 */
@Service
public class UDRelationServiceImpl implements UDRelationService {
    @Autowired
    private UDRelationDao udRelationDao;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    private DiscountService discountService;

    /**
     * 通过用户id查询优惠券,其所拥有的优惠券
     * @param userId
     * @return
     */
    @Override
    public List<Long> getDiscountIdByUserId(Long userId) {
        List<Long> longList = udRelationDao.selectDIdByUId(userId);
        return longList;
    }

    /**
     * 展示所有的关联关系
     * @return
     */
    @Override
    public List<UDRelation> list() {
        List<UDRelation> udRelations = udRelationDao.selectList(null);
        return udRelations;
    }

    /**
     * 通过用户id和优惠券id删除对应的关联信息
     * @param userId
     * @param discountId
     * @return
     */
    @Override
    public Boolean deleteOneInfo(Long userId, Long discountId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("discount_id", discountId);
        List<UDRelation> udRelations = udRelationDao.selectByMap(map);
        int b = 0;
        if(!udRelations.isEmpty()){
            Long id = udRelations.get(0).getId();
            b = udRelationDao.deleteById(id);
        }
        return (b > 0);
    }

    /**
     * 当用户输入注册码后添加优惠券
     */
    public Boolean addRelationByDiscountId(Long userId, List<Long> discountIds){
        boolean b = true;
        for(Long id : discountIds){
            int insert = udRelationDao.insert(new UDRelation(null, id, userId));
            if(insert <= 0){
               b = false;
            }
        }
        return b;
    }

    /**
     * 搜索功能
     * @param searchText
     * @return
     */
    @Override
    public Set<String> searchDown(String searchText, User user) {
        String str = (String) JSON.parse(searchText);
        QueryWrapper<Discount> qw = new QueryWrapper<>();
        qw.like("store_name", str);
        List<Discount> discounts = discountDao.selectList(qw);
        ArrayList<Long> ids = new ArrayList<>();
        for(Discount discount : discounts){
            ids.add(discount.getId());
        }
        List<Long> listOfUser = getDiscountIdByUserId(user.getId());
        HashSet<String> result = new HashSet<>();
        for(long l : listOfUser){
            if(ids.contains(l)){
                result.add(discountService.listById(l).getStoreName());
            }
        }
        return result;
    }
    @Override
    public List<Discount> search(String searchText, User user) {
        String str = (String) JSON.parse(searchText);
        QueryWrapper<Discount> qw = new QueryWrapper<>();
        qw.like("store_name", str);
        List<Discount> discounts = discountDao.selectList(qw);
        ArrayList<Long> ids = new ArrayList<>();
        for(Discount discount : discounts){
            ids.add(discount.getId());
        }
        List<Long> listOfUser = getDiscountIdByUserId(user.getId());
        ArrayList<Discount> result = new ArrayList<>();
        for(long l : listOfUser){
            if(ids.contains(l)){
                result.add(discountService.listById(l));
            }
        }
        return result;
    }

    @Override
    public List<UDRelation> listDeletedById(User user) {
        return udRelationDao.selectDeleted(user.getId());
    }

    @Override
    public List<UDRelation> selectTimeOut(User user) {
        List<UDRelation> udRelations = udRelationDao.selectDeleted(user.getId());
        List<UDRelation> result = new ArrayList<>();
        for(UDRelation ud : udRelations){
            Date deleteTime = ud.getDeleteTime();
            Date curDate = new Date();
            if(curDate.getTime() - deleteTime.getTime() > (1000L * 60 * 60 * 24 * 30)){
                result.add(ud);
            }
        }
        return result;
    }

    @Override
    public Boolean updateInfo(Long userId, Long discountId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("discount_id", discountId);
        List<UDRelation> udRelations = udRelationDao.selectByMap(map);
        udRelations.get(0).setDeleteTime(new Date());
        return udRelationDao.updateById(udRelations.get(0)) > 0;
    }
}
