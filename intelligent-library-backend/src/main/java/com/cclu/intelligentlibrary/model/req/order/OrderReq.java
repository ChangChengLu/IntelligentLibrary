package com.cclu.intelligentlibrary.model.req.order;

import com.cclu.intelligentlibrary.model.po.OrderInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/27 10:12
 * @description
 * @copyright ChangChengLu
 */
@Data
public class OrderReq implements Serializable {

    private Long userId;

    private List<OrderInfo> orderInfoList;

    private static final long serialVersionUID = 1L;

}
