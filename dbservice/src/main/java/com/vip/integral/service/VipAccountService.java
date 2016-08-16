package com.vip.integral.service;

import com.vip.integral.model.VipAccount;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface VipAccountService {

    /**
     * 选举一个存量最多的账号
     *
     * @param type
     * @return
     */
    VipAccount vote(Integer type);

    int update(VipAccount vipAccount);

}
