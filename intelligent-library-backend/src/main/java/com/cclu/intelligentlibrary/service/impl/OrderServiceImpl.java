package com.cclu.intelligentlibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.model.entity.Order;
import com.cclu.intelligentlibrary.service.OrderService;
import com.cclu.intelligentlibrary.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




