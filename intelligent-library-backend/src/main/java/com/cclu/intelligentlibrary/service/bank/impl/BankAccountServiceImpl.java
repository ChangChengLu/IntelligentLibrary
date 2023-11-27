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
    public boolean doRecharge(Long userId, BigDecimal rechargeMoney) {
        ThrowUtils.throwIf(
                rechargeMoney == null || rechargeMoney.doubleValue() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        QueryWrapper<BankAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        BankAccount bankAccount = bankAccountMapper.selectOne(queryWrapper);
        BigDecimal oldUserBalance = bankAccount.getUserBalance();
        bankAccount.setUserBalance(oldUserBalance.add(rechargeMoney));
        return updateById(bankAccount);
    }

    @Override
    public boolean doOrderBankAccountPay(Long userId, BigDecimal discountMoney) {
        QueryWrapper<BankAccount> bankAccountQueryWrapper = new QueryWrapper<>();
        bankAccountQueryWrapper.eq("user_id", userId);
        BankAccount bankAccount = getOne(bankAccountQueryWrapper);

        BigDecimal oldUserBalance = bankAccount.getUserBalance();
        BigDecimal oldUserTotalCost = bankAccount.getUserTotalCost();

        ThrowUtils.throwIf(
                oldUserBalance.subtract(discountMoney).doubleValue() < 0,
                BaseResponseCode.SYSTEM_ERROR, "账号余额不足"
        );
        bankAccount.setUserBalance(oldUserBalance.subtract(discountMoney));
        bankAccount.setUserTotalCost(oldUserTotalCost.add(discountMoney));
        return updateById(bankAccount);
    }

    @Override
    public BankAccount getBankAccountByUserId(Long userId) {
        ThrowUtils.throwIf(
                userId == null || userId < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        QueryWrapper<BankAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return getOne(queryWrapper);
    }
}




