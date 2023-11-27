package com.cclu.intelligentlibrary.service.vip.strategy;

import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 20:24
 * @description
 * @copyright ChangChengLu
 */
public interface IVipDiscountStrategy {

    /**
     * VIP折扣：购物折扣
     * @param currentMoney 未折扣付费
     * @return 折扣付费
     */
    BigDecimal doPayDisCount(BigDecimal currentMoney);

    /**
     * VIP折扣：借阅折扣
     * @param currentMoney 未折扣付费
     * @return 折扣付费
     */
    BigDecimal doBorrowDiscount(BigDecimal currentMoney);

}
