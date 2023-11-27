package com.cclu.intelligentlibrary.service.bank;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intelligentlibrary.model.po.BankAccount;

import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【bank_account】的数据库操作Service
* @createDate 2023-10-27 16:47:14
*/
public interface BankAccountService extends IService<BankAccount> {

    /**
     * 会员充值服务
     * @param userId 用户ID
     * @param rechargeMoney 充值金额
     * @return 充值是否成功
     */
    boolean doRecharge(Long userId, BigDecimal rechargeMoney);

    /**
     * 下订单用户信息更新操作
     * @param userId 用户ID
     * @param discountMoney 折扣价格
     * @return 是否扣款成功
     */
    boolean doOrderBankAccountPay(Long userId, BigDecimal discountMoney);

    /**
     * BankAccount
     * @param userId userId
     * @return BankAccount
     */
    BankAccount getBankAccountByUserId(Long userId);

}
