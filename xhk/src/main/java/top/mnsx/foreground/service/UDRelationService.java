package top.mnsx.foreground.service;

import top.mnsx.foreground.controller.UDRelationController;
import top.mnsx.foreground.entity.Discount;
import top.mnsx.foreground.entity.UDRelation;
import top.mnsx.foreground.entity.User;

import java.util.List;
import java.util.Set;

public interface UDRelationService {
    List<Long> getDiscountIdByUserId(Long userId);

    List<UDRelation> list();

    Boolean deleteOneInfo(Long userId, Long discountId);

    Boolean addRelationByDiscountId(Long userId, List<Long>discountIds);

    List<Discount> search(String searchText, User user);

    Set<String> searchDown(String searchText, User user);

    List<UDRelation> listDeletedById(User user);

    List<UDRelation> selectTimeOut(User user);

    Boolean updateInfo(Long userId, Long discountId);
}
