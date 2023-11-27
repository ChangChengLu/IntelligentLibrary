package com.cclu.intelligentlibrary.controller;

import com.cclu.intelligentlibrary.common.response.BaseResponse;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.context.UserActionContext;
import com.cclu.intelligentlibrary.facade.IOrderProcess;
import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.model.po.OrderInfo;
import com.cclu.intelligentlibrary.model.req.order.OrderReq;
import com.cclu.intelligentlibrary.service.order.OrderRichService;
import com.cclu.intelligentlibrary.service.vip.discount.VipService;
import com.cclu.intelligentlibrary.utils.ResultUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/11/2 20:10
 * @description
 * @copyright ChangChengLu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderProcess orderProcess;

    @Resource
    private OrderRichService orderRichService;

    @Resource
    private VipService vipService;

    @PostMapping("/buy")
    public BaseResponse<Boolean> bookOrderBuy(@RequestBody OrderReq orderReq) {
        OrderRich orderRich = getOrderRich(orderReq);
        UserActionContext.setActionMode(1);
        UserActionContext.setUserRole(orderRichService.getUserVipRole(orderRich));
        boolean res = orderProcess.doOrderActionProcess(orderRich);
        ThrowUtils.throwIf(!res, BaseResponseCode.SYSTEM_ERROR, "购买书籍下单出错");
        return ResultUtils.success(true);
    }

    @PostMapping("/borrow")
    public BaseResponse<Boolean> bookOrderBorrow(@RequestBody OrderReq orderReq) {
        OrderRich orderRich = getOrderRich(orderReq);
        UserActionContext.setActionMode(2);
        UserActionContext.setUserRole(orderRichService.getUserVipRole(orderRich));
        boolean res = orderProcess.doOrderActionProcess(orderRich);
        ThrowUtils.throwIf(!res, BaseResponseCode.SYSTEM_ERROR, "借阅书籍下单出错");
        return ResultUtils.success(true);
    }

    @PostMapping("/price/total")
    public BaseResponse<BigDecimal> computerTotalPrice(@RequestBody OrderReq orderReq) {
        ThrowUtils.throwIf(
                orderReq == null || orderReq.getOrderInfoList() == null,
                BaseResponseCode.PARAMS_ERROR
        );
        UserActionContext.setActionMode(2);
        UserActionContext.setUserRole(orderRichService.getUserVipRole(orderReq.getUserId()));
        List<OrderInfo> orderInfoList = orderReq.getOrderInfoList();
        double res = orderInfoList.stream().mapToDouble(item -> item.getOrderInfoAmount().doubleValue()).sum();
        UserActionContext.clearUserRole();
        UserActionContext.clearActionMode();
        return ResultUtils.success(BigDecimal.valueOf(res));
    }

    @PostMapping("/price/discount")
    public BaseResponse<BigDecimal> computerDiscountPrice(@RequestBody OrderReq orderReq) {
        ThrowUtils.throwIf(
                orderReq == null || orderReq.getOrderInfoList() == null,
                BaseResponseCode.PARAMS_ERROR
        );
        UserActionContext.setActionMode(2);
        UserActionContext.setUserRole(orderRichService.getUserVipRole(orderReq.getUserId()));
        List<OrderInfo> orderInfoList = orderReq.getOrderInfoList();
        BigDecimal res = vipService.doVipDiscount(BigDecimal.valueOf(orderInfoList.stream().mapToDouble(
                item -> item.getOrderInfoAmount().doubleValue()
        ).sum()));
        UserActionContext.clearUserRole();
        UserActionContext.clearActionMode();
        return ResultUtils.success(res);
    }

    private OrderRich getOrderRich(OrderReq orderReq) {
        ThrowUtils.throwIf(
                orderReq == null || orderReq.getUserId() == null || orderReq.getOrderInfoList() == null,
                BaseResponseCode.PARAMS_ERROR
        );
        Long userId = orderReq.getUserId();
        List<OrderInfo> orderInfoList = orderReq.getOrderInfoList();

        OrderRich orderRich = new OrderRich();
        orderRich.getOrder().setUserId(userId);
        orderRich.setOrderInfoList(orderInfoList);

        return orderRich;
    }

}