package com.cclu.intelligentlibrary.service.vip;

import com.cclu.intelligentlibrary.model.po.Vip;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【vip】的数据库操作Service
* @createDate 2023-10-27 16:47:15
*/
public interface VipService extends IService<Vip> {

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
