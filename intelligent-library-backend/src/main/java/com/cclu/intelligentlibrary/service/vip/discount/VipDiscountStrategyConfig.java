package com.cclu.intelligentlibrary.service.vip.discount;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.strategy.IVipDiscountStrategy;
import com.cclu.intelligentlibrary.service.vip.strategy.impl.GoldVipDiscountStrategy;
import com.cclu.intelligentlibrary.service.vip.strategy.impl.OrdinaryVipDiscountStrategy;
import com.cclu.intelligentlibrary.service.vip.strategy.impl.PlatinumVipDiscountStrategy;
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
public class VipDiscountStrategyConfig {

    @Resource
    private OrdinaryVipDiscountStrategy ordinaryVipDiscountStrategy;

    @Resource
    private GoldVipDiscountStrategy goldVipDiscountStrategy;

    @Resource
    private PlatinumVipDiscountStrategy platinumVipDiscountStrategy;

    private static Map<VipRoleEnum, IVipDiscountStrategy> vipStrategyGroup = new ConcurrentHashMap<>();

    @PostConstruct
    protected void init() {
        vipStrategyGroup.put(VipRoleEnum.ORDINARY, ordinaryVipDiscountStrategy);
        vipStrategyGroup.put(VipRoleEnum.GOLD_NUMBER, goldVipDiscountStrategy);
        vipStrategyGroup.put(VipRoleEnum.PLATINUM_NUMBER, platinumVipDiscountStrategy);
    }

    public IVipDiscountStrategy getVipServiceByVipRole(VipRoleEnum vipRoleEnum) {
        ThrowUtils.throwIf(
                vipRoleEnum == null,
                BaseResponseCode.PARAMS_ERROR
        );
        return vipStrategyGroup.get(vipRoleEnum);
    }
}
