package com.cclu.intelligentlibrary.service.vip.discount.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.constant.VipConstant;
import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.model.po.BankAccount;
import com.cclu.intelligentlibrary.model.po.Vip;
import com.cclu.intelligentlibrary.service.vip.discount.AbstractVipDiscountBase;
import com.cclu.intelligentlibrary.service.vip.strategy.IVipDiscountStrategy;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 20:41
 * @description
 * @copyright ChangChengLu
 */
@Service
public class VipDiscountImpl extends AbstractVipDiscountBase {

    @Override
    protected BigDecimal vipDiscount(int userRole, int actionMode, BigDecimal actualMoney) {
        ThrowUtils.throwIf(
                userRole < 0 || userRole > 3,
                BaseResponseCode.PARAMS_ERROR
        );
        ThrowUtils.throwIf(
                actionMode != 1 && actionMode != 2,
                BaseResponseCode.PARAMS_ERROR
        );
        ThrowUtils.throwIf(
                actualMoney == null || actualMoney.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        VipRoleEnum vipRoleEnum = VipRoleEnum.getEnumByCode(userRole);
        IVipDiscountStrategy vipDiscountStrategy = vipDiscountStrategyConfig.getVipServiceByVipRole(vipRoleEnum);
        BigDecimal discountMoney = null;
        switch (actionMode) {
            case 1: {
                discountMoney = vipDiscountStrategy.doPayDisCount(actualMoney);
                break;
            }
            case 2: {
                discountMoney = vipDiscountStrategy.doBorrowDiscount(actualMoney);
                break;
            }
            default: break;
        }
        return discountMoney == null ? actualMoney : discountMoney;
    }

    @Override
    public boolean updateVipRole(Long userId) {
        QueryWrapper<BankAccount> bankAccountQueryWrapper = new QueryWrapper<>();
        bankAccountQueryWrapper.eq("user_id", userId);
        BankAccount bankAccount = bankAccountService.getOne(bankAccountQueryWrapper);
        BigDecimal userTotalCost = bankAccount.getUserTotalCost();
        ThrowUtils.throwIf(
                userTotalCost == null || userTotalCost.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        double userTotalCostValue = userTotalCost.doubleValue();

        QueryWrapper<Vip> vipQueryWrapper = new QueryWrapper<>();
        vipQueryWrapper.eq("user_id", userId);
        Vip vip = getOne(vipQueryWrapper);
        VipRoleEnum currentVipRole = VipRoleEnum.getEnumByCode(vip.getVipType());

        if (VipRoleEnum.ORDINARY.equals(currentVipRole)
                && userTotalCostValue >= VipConstant.GOLD_CRITICAL_POINT
                && userTotalCostValue < VipConstant.PLATINUM_CRITICAL_POINT) {
            UpdateWrapper<Vip> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("user_id", userId);
            updateWrapper.set("vip_type", VipRoleEnum.GOLD_NUMBER.getCode());
            return update(updateWrapper);
        }
        if (VipRoleEnum.GOLD_NUMBER.equals(currentVipRole)
                && userTotalCostValue >= VipConstant.PLATINUM_CRITICAL_POINT) {
            UpdateWrapper<Vip> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("user_id", userId);
            updateWrapper.set("vip_type", VipRoleEnum.PLATINUM_NUMBER.getCode());
            return update(updateWrapper);
        }
        return false;
    }
}
