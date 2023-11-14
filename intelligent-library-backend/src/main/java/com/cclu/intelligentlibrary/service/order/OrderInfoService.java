package com.cclu.intelligentlibrary.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intelligentlibrary.model.po.OrderInfo;

import java.util.List;

/**
* @author ChangChengLu
* @description 针对表【order_info】的数据库操作Service
* @createDate 2023-10-27 16:47:15
*/
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * List<OrderInfo>
     * @param orderId orderId
     * @return List<OrderInfo>
     */
    List<OrderInfo> queryOrderInfoByOrderId(Integer orderId);

}
