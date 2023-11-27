package com.cclu.intelligentlibrary.facade.impl;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.facade.IOrderProcess;
import com.cclu.intelligentlibrary.model.aggregates.OrderRich;
import com.cclu.intelligentlibrary.service.bank.BankAccountService;
import com.cclu.intelligentlibrary.service.order.OrderRichService;
import com.cclu.intelligentlibrary.service.vip.discount.VipService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 20:19
 * @description
 * @copyright ChangChengLu
 */
@Component
@Slf4j
public class OrderProcess implements IOrderProcess {

    @Resource
    private OrderRichService orderRichService;

    @Resource
    private VipService vipService;

    @Resource
    private BankAccountService bankAccountService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean doOrderActionProcess(OrderRich orderRich) {
        ThrowUtils.throwIf(
                orderRich == null || orderRich.getOrder() == null || orderRich.getOrderInfoList().isEmpty(),
                BaseResponseCode.PARAMS_ERROR
        );
        BigDecimal discountMoney = orderRichService.doOrderPay(orderRich.getOrder(), orderRich.getOrderInfoList());

        Long userId = orderRich.getOrder().getUserId();
        boolean bankAccountResult = bankAccountService.doOrderBankAccountPay(userId, discountMoney);
        ThrowUtils.throwIf(!bankAccountResult, BaseResponseCode.SYSTEM_ERROR, "银行账户更新错误");

        boolean updateVipRoleResult = vipService.updateVipRole(userId);
        if (!updateVipRoleResult) {
            log.info("用户{}身份不需要更新!", userId);
        }

        return true;
    }

}
