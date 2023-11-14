package com.cclu.intelligentlibrary.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.mapper.OrderMapper;
import com.cclu.intelligentlibrary.model.po.Order;
import com.cclu.intelligentlibrary.service.order.OrderService;
import org.springframework.stereotype.Service;

/**
* @author ChangChengLu
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




