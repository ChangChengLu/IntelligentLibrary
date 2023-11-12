package com.cclu.intelligentlibrary.service.bank.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.mapper.BankAccountMapper;
import com.cclu.intelligentlibrary.model.po.BankAccount;
import com.cclu.intelligentlibrary.service.bank.BankAccountService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
* @author ChangChengLu
* @description 针对表【bank_account】的数据库操作Service实现
* @createDate 2023-10-27 16:47:14
*/
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount>
    implements BankAccountService {

    @Resource
    private BankAccountMapper bankAccountMapper;

    @Override
    public boolean doRecharge(BigDecimal rechargeMoney) {
        ThrowUtils.throwIf(
                rechargeMoney != null || rechargeMoney.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        QueryWrapper<BankAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_balance", rechargeMoney);
        return update(queryWrapper);
    }

}




