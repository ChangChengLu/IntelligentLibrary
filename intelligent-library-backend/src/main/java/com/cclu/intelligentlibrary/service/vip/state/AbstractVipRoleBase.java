package com.cclu.intelligentlibrary.service.vip.state;

import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.discount.VipService;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 21:27
 * @description
 * @copyright ChangChengLu
 */
public abstract class AbstractVipRoleBase {

    @Resource
    private VipService vipService;

    /**
     * 切换为普通用户
     * @param userId 用户ID
     * @param currentVipRole 当前VIP用户角色
     * @return 角色是否变换成功
     */
    public abstract boolean toOrdinary(Long userId, VipRoleEnum currentVipRole);

    /**
     * 切换为黄金用户
     * @param userId 用户ID
     * @param currentVipRole 当前VIP用户角色
     * @return 角色是否变换成功
     */
    public abstract boolean toGoldNumber(Long userId, VipRoleEnum currentVipRole);

    /**
     * 切换为白金用户
     * @param userId 用户ID
     * @param currentVipRole 当前VIP用户角色
     * @return 角色是否变换成功
     */
    public abstract boolean toPlatinumNumber(Long userId, VipRoleEnum currentVipRole);

}
