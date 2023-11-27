package com.cclu.intelligentlibrary.service.vip.strategy.impl;

import com.cclu.intelligentlibrary.constant.VipConstant;
import com.cclu.intelligentlibrary.service.vip.strategy.IVipDiscountStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【vip】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Component("goldVipDiscountStrategy")
public class GoldVipDiscountStrategy implements IVipDiscountStrategy {

    @Override
    public BigDecimal doPayDisCount(BigDecimal currentMoney) {
        return currentMoney.multiply(BigDecimal.valueOf(VipConstant.GOLD_DISCOUNT_RATE));
    }

    @Override
    public BigDecimal doBorrowDiscount(BigDecimal currentMoney) {
        return currentMoney.multiply(BigDecimal.valueOf(VipConstant.GOLD_DISCOUNT_RATE));
    }
}




