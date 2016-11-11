package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.VipAccount;

import java.util.List;

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

    int save(VipAccount vipAccount);

    VipAccount get(VipAccount vipAccount);

    List<VipAccount> listAll();

    int delete(VipAccount vipAccount);

    void reset();

    List<VipAccount> listVip(User user);

}
