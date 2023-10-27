package com.cclu.intelligentlibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.model.entity.OrderInfo;
import com.cclu.intelligentlibrary.service.OrderInfoService;
import com.cclu.intelligentlibrary.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{

}




