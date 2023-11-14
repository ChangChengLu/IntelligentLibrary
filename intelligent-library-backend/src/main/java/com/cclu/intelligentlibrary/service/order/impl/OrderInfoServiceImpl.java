package com.cclu.intelligentlibrary.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.mapper.OrderInfoMapper;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import com.cclu.intelligentlibrary.service.order.OrderInfoService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ChangChengLu
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> queryOrderInfoByOrderId(Integer orderId) {
        ThrowUtils.throwIf(
                orderId == null || orderId <= 0,
                BaseResponseCode.PARAMS_ERROR
        );
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return orderInfoMapper.selectList(queryWrapper);
    }
}




