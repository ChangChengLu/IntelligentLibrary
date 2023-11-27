package com.cclu.intelligentlibrary.service.order;

import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.model.po.OrderInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/2 20:02
 * @description
 * @copyright ChangChengLu
 */
public interface OrderRichService {

    /**
     * 账单支付
     * @param order order
     * @param orderInfoList orderInfoList
     * @return 账单支付是否成功
     */
    BigDecimal doOrderPay(Order order, List<OrderInfo> orderInfoList);

    /**
     * 根据ID查询OrderRich
     * @param orderId orderId
     * @return OrderRich
     */
    OrderRich queryOrderRichById(Integer orderId);

    /**
     * 插入 OrderRich
     * @param order order
     * @param orderInfoList orderInfoList
     * @return 是否成功
     */
    boolean saveOrderRich(Order order, List<OrderInfo> orderInfoList);

    /**
     * vipRole
     * @param orderRich orderRich
     * @return vipRole
     */
    Integer getUserVipRole(OrderRich orderRich);

    /**
     * 获取 VIP role
     * @param userId 用户ID
     * @return vipRole
     */
    Integer getUserVipRole(Long userId);

}
