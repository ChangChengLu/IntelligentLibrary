package com.cclu.intelligentlibrary.assembly;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.po.BankAccount;
import com.cclu.intelligentlibrary.model.vo.bankAccount.BankAccountVO;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author ChangCheng Lu
 * @date 2023/11/26 19:23
 * @description
 * @copyright ChangChengLu
 */
public class BankAccountAssembly {

    public static BankAccountVO bankAccount2BankAccountVO(BankAccount bankAccount) {
        ThrowUtils.throwIf(
                bankAccount == null,
                BaseResponseCode.PARAMS_ERROR
        );
        BankAccountVO bankAccountVO = new BankAccountVO();
        BeanUtils.copyProperties(bankAccount, bankAccountVO);
        return bankAccountVO;
    }

}
