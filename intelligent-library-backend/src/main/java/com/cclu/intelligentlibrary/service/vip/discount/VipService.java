package com.cclu.intelligentlibrary.service.vip.discount;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intelligentlibrary.model.po.Vip;

import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【vip】的数据库操作Service
* @createDate 2023-10-27 16:47:15
*/
public interface VipService extends IService<Vip> {

    /**
     * 折扣
     * @param actualMoney 当前金额
     * @return 折扣后价格
     */
    BigDecimal doVipDiscount(BigDecimal actualMoney);

    /**
     * 更新用户VIP当前状态
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean updateVipRole(Long userId);

}
