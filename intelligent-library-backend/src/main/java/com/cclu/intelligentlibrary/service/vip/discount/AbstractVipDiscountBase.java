package com.cclu.intelligentlibrary.service.vip.discount;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.context.UserActionContext;
import com.cclu.intelligentlibrary.mapper.VipMapper;
import com.cclu.intelligentlibrary.model.po.Vip;
import com.cclu.intelligentlibrary.service.bank.BankAccountService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 20:36
 * @description
 * @copyright ChangChengLu
 */
public abstract class AbstractVipDiscountBase extends ServiceImpl<VipMapper, Vip> implements VipService {

    @Resource
    protected VipDiscountStrategyConfig vipDiscountStrategyConfig;

    @Resource
    protected BankAccountService bankAccountService;

    @Override
    public BigDecimal doVipDiscount(BigDecimal actualMoney) {
        ThrowUtils.throwIf(
                actualMoney == null || actualMoney.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        int userRole = getUserRole();
        int actionMode = getActionMode();
        return vipDiscount(userRole, actionMode, actualMoney);
    }

    protected abstract BigDecimal vipDiscount(int userRoel, int actionMode, BigDecimal actualMoney);

    protected int getUserRole() {
        return UserActionContext.getUserRole();
    }

    protected int getActionMode() {
        return UserActionContext.getActionMode();
    }

}
