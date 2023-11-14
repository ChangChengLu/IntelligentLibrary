package com.cclu.intelligentlibrary.service.order.impl;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.context.UserActionContext;
import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.order.AbstractOrderRichBase;
import com.cclu.intelligentlibrary.service.vip.VipConfig;
import com.cclu.intelligentlibrary.service.vip.VipService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/12 19:02
 * @description
 * @copyright ChangChengLu
 */
public class OrderRichService extends AbstractOrderRichBase {

    @Resource
    private VipConfig vipConfig;

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
        VipService vipService = vipConfig.getVipServiceByVipRole(vipRoleEnum);
        BigDecimal discountMoney = null;
        switch (actionMode) {
            case 1: {
                discountMoney = vipService.doPayDisCount(actualMoney);
                break;
            }
            case 2: {
                discountMoney = vipService.doBorrowDiscount(actualMoney);
                break;
            }
            default: break;
        }
        return discountMoney == null ? actualMoney : discountMoney;
    }

    @Override
    protected int getUserRole() {
        return UserActionContext.getUserRole();
    }

    @Override
    protected int getActionMode() {
        return UserActionContext.getActionMode();
    }

    @Override
    protected void clear() {
        UserActionContext.clearUserRole();
        UserActionContext.clearActionMode();
    }

}
