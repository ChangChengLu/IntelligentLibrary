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
     * @param rechargeMoney 充值金额
     * @return 充值是否成功
     */
    boolean doRecharge(BigDecimal rechargeMoney);

}
