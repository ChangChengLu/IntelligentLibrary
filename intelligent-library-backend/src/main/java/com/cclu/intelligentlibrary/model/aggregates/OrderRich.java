package com.cclu.intelligentlibrary.model.aggregates;

import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/2 20:00
 * @description
 * @copyright ChangChengLu
 */
@Data
public class OrderRich implements Serializable {

    private int orderId;

    private Order order;

    private List<OrderInfo> orderInfoList;

    private static final long serialVersionUID = 1L;

}
