package com.cclu.intelligentlibrary.model.vo.bankAccount;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/26 19:16
 * @description
 * @copyright ChangChengLu
 */
@Data
public class BankAccountVO implements Serializable {

    /**
     * 银行账户编号
     */
    private Long id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 账户余额
     */
    private BigDecimal userBalance;

    /**
     * 用户消费总额
     */
    private BigDecimal userTotalCost;


}
