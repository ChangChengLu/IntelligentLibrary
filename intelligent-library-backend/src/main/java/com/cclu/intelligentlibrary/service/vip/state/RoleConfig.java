package com.cclu.intelligentlibrary.service.vip.state;

import com.cclu.intelligentlibrary.model.enums.VipRoleEnum;
import com.cclu.intelligentlibrary.service.vip.state.event.GoldNumberRole;
import com.cclu.intelligentlibrary.service.vip.state.event.OrdinaryRole;
import com.cclu.intelligentlibrary.service.vip.state.event.PlatinumNumberRole;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 21:33
 * @description
 * @copyright ChangChengLu
 */
public class RoleConfig {

    @Resource
    private OrdinaryRole ordinaryRole;

    @Resource
    private GoldNumberRole goldNumberRole;

    @Resource
    private PlatinumNumberRole platinumNumberRole;

    protected Map<VipRoleEnum, AbstractVipRoleBase> roleGroup = new ConcurrentHashMap<>(4);

    @PostConstruct
    public void init() {
        roleGroup.put(VipRoleEnum.ORDINARY, ordinaryRole);
        roleGroup.put(VipRoleEnum.GOLD_NUMBER, goldNumberRole);
        roleGroup.put(VipRoleEnum.PLATINUM_NUMBER, platinumNumberRole);
    }

}
