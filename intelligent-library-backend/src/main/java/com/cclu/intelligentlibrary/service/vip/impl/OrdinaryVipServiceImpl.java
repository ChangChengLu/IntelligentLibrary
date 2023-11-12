package com.cclu.intelligentlibrary.service.vip.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.constant.VipConstant;
import com.cclu.intelligentlibrary.model.po.Vip;
import com.cclu.intelligentlibrary.service.vip.VipService;
import com.cclu.intelligentlibrary.mapper.VipMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【vip】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class OrdinaryVipServiceImpl extends ServiceImpl<VipMapper, Vip>
    implements VipService{

    @Override
    public BigDecimal doPayDisCount(BigDecimal currentMoney) {
        return currentMoney.multiply(BigDecimal.valueOf(VipConstant.ORDINARY_DISCOUNT_RATE));
    }

    @Override
    public BigDecimal doBorrowDiscount(BigDecimal currentMoney) {
        return currentMoney.multiply(BigDecimal.valueOf(VipConstant.ORDINARY_DISCOUNT_RATE));
    }
}




