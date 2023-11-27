package com.cclu.intelligentlibrary.controller;

import com.cclu.intelligentlibrary.assembly.BankAccountAssembly;
import com.cclu.intelligentlibrary.common.request.IdReq;
import com.cclu.intelligentlibrary.common.response.BaseResponse;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.po.BankAccount;
import com.cclu.intelligentlibrary.model.req.bankAccount.BankAccountRechargeReq;
import com.cclu.intelligentlibrary.model.vo.bankAccount.BankAccountVO;
import com.cclu.intelligentlibrary.service.bank.BankAccountService;
import com.cclu.intelligentlibrary.utils.ResultUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/26 18:56
 * @description
 * @copyright ChangChengLu
 */
@RestController
@RequestMapping("/bank")
@Slf4j
public class BankAccountController {

    @Resource
    private BankAccountService bankAccountService;

    @PostMapping("/recharge")
    public BaseResponse<Boolean> bankRecharge(@RequestBody BankAccountRechargeReq bankAccountRechargeReq) {
        ThrowUtils.throwIf(bankAccountRechargeReq == null, BaseResponseCode.PARAMS_ERROR);
        Long userId = bankAccountRechargeReq.getUserId();
        BigDecimal rechargeMoney = bankAccountRechargeReq.getRechargeMoney();
        boolean res = bankAccountService.doRecharge(userId, rechargeMoney);
        ThrowUtils.throwIf(!res, BaseResponseCode.SYSTEM_ERROR);
        return ResultUtils.success(true);
    }

    @PostMapping("/profile")
    public BaseResponse<BankAccountVO> getProfileByUserId(@RequestBody IdReq req) {
        ThrowUtils.throwIf(req == null, BaseResponseCode.PARAMS_ERROR);
        Long userId = req.getId();
        BankAccount bankAccount = bankAccountService.getBankAccountByUserId(userId);
        BankAccountVO bankAccountVO = BankAccountAssembly.bankAccount2BankAccountVO(bankAccount);
        return ResultUtils.success(bankAccountVO);
    }

}
