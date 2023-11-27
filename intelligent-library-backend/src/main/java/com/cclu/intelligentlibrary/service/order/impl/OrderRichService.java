package com.cclu.intelligentlibrary.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.context.UserActionContext;
import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import com.cclu.intelligentlibrary.model.po.Vip;
import com.cclu.intelligentlibrary.service.order.AbstractOrderRichBase;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/12 19:02
 * @description
 * @copyright ChangChengLu
 */
@Service
public class OrderRichService extends AbstractOrderRichBase {

    @Override
    protected BigDecimal computerActualMoney(Order order, List<OrderInfo> orderInfoList) {
        BigDecimal res = BigDecimal.ZERO;
        for (OrderInfo orderInfo : orderInfoList) {
            res = res.add(orderInfo.getOrderInfoAmount());
        }
        return res;
    }

    @Override
    protected void clear() {
        UserActionContext.clearUserRole();
        UserActionContext.clearActionMode();
    }

    @Override
    public Integer getUserVipRole(OrderRich orderRich) {
        ThrowUtils.throwIf(
                orderRich.getOrder() == null || orderRich.getOrderInfoList().isEmpty(),
                BaseResponseCode.PARAMS_ERROR
        );
        Long userId = orderRich.getOrder().getUserId();
        return getUserVipRole(userId);
    }

    @Override
    public Integer getUserVipRole(Long userId) {
        QueryWrapper<Vip> vipQueryWrapper = new QueryWrapper<>();
        vipQueryWrapper.eq("user_id", userId);
        Vip vip = vipService.getOne(vipQueryWrapper);
        ThrowUtils.throwIf(vip == null, BaseResponseCode.PARAMS_ERROR);
        return vip.getVipType();
    }
}
