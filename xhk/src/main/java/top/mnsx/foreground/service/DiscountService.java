package top.mnsx.foreground.service;

import top.mnsx.foreground.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> list();

    Discount listById(Long id);
}
