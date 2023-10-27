package com.cclu.intelligentlibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.mapper.BankAccountMapper;
import com.cclu.intelligentlibrary.model.entity.BankAccount;
import com.cclu.intelligentlibrary.service.BankAccountService;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【bank_account】的数据库操作Service实现
* @createDate 2023-10-27 16:47:14
*/
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount>
    implements BankAccountService {

}




