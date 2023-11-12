package com.cclu.intelligentlibrary.service.vip;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.impl.GoldVipServiceImpl;
import com.cclu.intelligentlibrary.service.vip.impl.OrdinaryVipServiceImpl;
import com.cclu.intelligentlibrary.service.vip.impl.PlatinumVipServiceImpl;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ChangCheng Lu
 * @date 2023/11/7 11:12
 * @description
 * @copyright ChangChengLu
 */
@Component
public class VipConfig {

    @Resource
    private OrdinaryVipServiceImpl ordinaryVipService;

    @Resource
    private GoldVipServiceImpl goldVipService;

    @Resource
    private PlatinumVipServiceImpl platinumVipService;

    private static Map<VipRoleEnum, VipService> vipGroup = new ConcurrentHashMap<>();

    @PostConstruct
    protected void init() {
        vipGroup.put(VipRoleEnum.ORDINARY, ordinaryVipService);
        vipGroup.put(VipRoleEnum.GOLD_NUMBER, goldVipService);
        vipGroup.put(VipRoleEnum.PLATINUM_NUMBER, platinumVipService);
    }

    public VipService getVipServiceByVipRole(VipRoleEnum vipRoleEnum) {
        ThrowUtils.throwIf(
                vipRoleEnum == null,
                BaseResponseCode.PARAMS_ERROR
        );
        return vipGroup.get(vipRoleEnum);
    }
}
