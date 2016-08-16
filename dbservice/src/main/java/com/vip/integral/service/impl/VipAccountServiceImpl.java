package com.vip.integral.service.impl;

import com.vip.integral.model.VipAccount;
import com.vip.integral.service.VipAccountService;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/16.
 */
public class VipAccountServiceImpl implements VipAccountService {
    @Override
    public VipAccount vote(Integer type) {

        List<VipAccount> list = null;
        VipAccount vipAccount = list.get(0);
        if (vipAccount.getCount() == 0)
            return null;
        return vipAccount;
    }
}
