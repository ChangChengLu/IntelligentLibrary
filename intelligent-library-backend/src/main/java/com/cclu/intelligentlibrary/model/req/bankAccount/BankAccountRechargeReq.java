package com.cclu.intelligentlibrary.model.req.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/26 19:00
 * @description
 * @copyright ChangChengLu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRechargeReq implements Serializable {

    private Long userId;

    private BigDecimal rechargeMoney;

}
