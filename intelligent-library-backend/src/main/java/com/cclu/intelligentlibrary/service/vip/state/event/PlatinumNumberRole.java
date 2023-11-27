package com.cclu.intelligentlibrary.service.vip.state.event;

import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.state.AbstractVipRoleBase;
import org.springframework.stereotype.Component;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 21:31
 * @description
 * @copyright ChangChengLu
 */
@Component
public class PlatinumNumberRole extends AbstractVipRoleBase {
    @Override
    public boolean toOrdinary(Long userId, VipRoleEnum currentVipRole) {
        return false;
    }

    @Override
    public boolean toGoldNumber(Long userId, VipRoleEnum currentVipRole) {
        return false;
    }

    @Override
    public boolean toPlatinumNumber(Long userId, VipRoleEnum currentVipRole) {
        return false;
    }
}
