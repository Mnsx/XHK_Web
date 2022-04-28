package top.mnsx.foreground.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.foreground.dao.DiscountDao;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.service.DiscountService;

import java.util.List;

/**
 * 优惠券Service
 */
@Service("discountService")
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountDao discountDao;

    /**
     * 展示所有优惠券
     * @return
     */
    @Override
    public List<Discount> list() {
         return discountDao.selectList(null);
    }

    /**
     * 通过id查询优惠券
     * @param id
     * @return
     */
    @Override
    public Discount listById(Long id) {
        Discount discount = discountDao.selectById(id);
        return discount;
    }
}
