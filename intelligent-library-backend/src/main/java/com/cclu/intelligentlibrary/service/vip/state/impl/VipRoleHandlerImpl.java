package com.cclu.intelligentlibrary.service.vip.state.impl;

import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.state.IVipRoleHandler;
import com.cclu.intelligentlibrary.service.vip.state.RoleConfig;
import org.springframework.stereotype.Service;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 21:36
 * @description
 * @copyright ChangChengLu
 */
@Service
public class VipRoleHandlerImpl extends RoleConfig implements IVipRoleHandler {
    @Override
    public boolean toOrdinary(Long userId, VipRoleEnum currentVipRole) {
        return roleGroup.get(currentVipRole).toOrdinary(userId, currentVipRole);
    }

    @Override
    public boolean toGoldNumber(Long userId, VipRoleEnum currentVipRole) {
        return roleGroup.get(currentVipRole).toGoldNumber(userId, currentVipRole);
    }

    @Override
    public boolean toPlatinumNumber(Long userId, VipRoleEnum currentVipRole) {
        return roleGroup.get(currentVipRole).toPlatinumNumber(userId, currentVipRole);
    }
}
