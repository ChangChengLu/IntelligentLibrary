package com.cclu.intelligentlibrary.service.order;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import com.cclu.intelligentlibrary.service.vip.discount.VipService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/7 17:05
 * @description
 * @copyright ChangChengLu
 */
public abstract class AbstractOrderRichBase implements OrderRichService {

    @Resource
    protected VipService vipService;

    @Resource
    protected OrderService orderService;

    @Resource
    protected OrderInfoService orderInfoService;

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
        BigDecimal discountMoney = vipService.doVipDiscount(actualMoney);

        order.setOrderAmount(actualMoney);
        order.setOrderPayAmount(discountMoney);

        if (discountMoney == null || discountMoney.doubleValue() < 0) {
            saveOrderRich(order, orderInfoList);
            clear();
            return actualMoney;
        }
        saveOrderRich(order, orderInfoList);

        clear();

        return discountMoney;
    }

    /**
     * actualMoney
     * @param order order
     * @param orderInfoList orderInfoList
     * @return actualMoney
     */
    protected abstract BigDecimal computerActualMoney(Order order, List<OrderInfo> orderInfoList);

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
        Long userId = order.getUserId();
        boolean saveResult;
        synchronized (userId.toString().intern()) {
            boolean orderSave = orderService.save(order);
            boolean orderInfoListSave = orderInfoService.saveBatch(orderInfoList);
            saveResult = orderSave && orderInfoListSave;
        }
        return saveResult;
    }

    /**
     * clear ThreadLocal
     */
    protected abstract void clear();

}
