package com.cclu.intelligentlibrary.service.order;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/7 17:05
 * @description
 * @copyright ChangChengLu
 */
public abstract class AbstractOrderRichBase implements OrderRichService {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderInfoService orderInfoService;

    @Override
    public BigDecimal doOrderPay(Order order, List<OrderInfo> orderInfoList) {
        ThrowUtils.throwIf(
                order == null || orderInfoList == null || orderInfoList.isEmpty(),
                BaseResponseCode.PARAMS_ERROR
        );
        OrderRich orderRich = new OrderRich();
        orderRich.setOrder(order);
        orderRich.setOrderInfoList(orderInfoList);

        BigDecimal actualMoney = computerActualMoney(order, orderInfoList);
        BigDecimal discountMoney = doVipDiscount(actualMoney);

        if (discountMoney == null || discountMoney.doubleValue() < 0) {
            saveOrderRich(order, orderInfoList);
            return actualMoney;
        }
        saveOrderRich(order, orderInfoList);

        clear();

        return discountMoney;
    }

    public BigDecimal computerActualMoney(Order order, List<OrderInfo> orderInfoList) {

        return null;
    }

    private BigDecimal doVipDiscount(BigDecimal actualMoney) {
        ThrowUtils.throwIf(
                actualMoney == null || actualMoney.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        int userRole = getUserRole();
        int actionMode = getActionMode();
        return vipDiscount(userRole, actionMode, actualMoney);
    }

    protected abstract BigDecimal vipDiscount(int userRoel, int actionMode, BigDecimal actualMoney);

    protected abstract int getUserRole();

    protected abstract int getActionMode();

    protected abstract void clear();

    @Override
    public OrderRich queryOrderRichById(Integer orderId) {
        ThrowUtils.throwIf(
                orderId == null || orderId <= 0,
                BaseResponseCode.PARAMS_ERROR
        );
        Order order = orderService.getById(orderId);
        List<OrderInfo> orderInfoList = orderInfoService.queryOrderInfoByOrderId(orderId);
        OrderRich orderRich = new OrderRich();
        orderRich.setOrderId(orderId);
        orderRich.setOrder(order);
        orderRich.setOrderInfoList(orderInfoList);
        return orderRich;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrderRich(Order order, List<OrderInfo> orderInfoList) {
        Long orderId = order.getId();
        boolean saveResult;
        synchronized (orderId.toString().intern()) {
            boolean orderSave = orderService.save(order);
            boolean orderInfoListSave = orderInfoService.saveBatch(orderInfoList);
            saveResult = orderSave && orderInfoListSave;
        }
        return saveResult;
    }

}
