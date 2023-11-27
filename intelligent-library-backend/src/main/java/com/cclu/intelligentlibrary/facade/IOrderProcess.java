package com.cclu.intelligentlibrary.facade;

import com.cclu.intelligentlibrary.model.aggregates.OrderRich;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 20:20
 * @description
 * @copyright ChangChengLu
 */
public interface IOrderProcess {

    /**
     * 流程编排：
     * 1. 订单服务
     *    1. 保存订单
     *    2. 计算折扣价格
     * 2. 扣减银行账户
     * 3. 计算并保存用户总消费金额
     * 4. 根据用户消费计算用户状态(VIP等级变化)
     * 5. 返回结果
     *
     * @param orderRich 订单聚合根对象
     * @return 是否成功
     */
    boolean doOrderActionProcess(OrderRich orderRich);

}
