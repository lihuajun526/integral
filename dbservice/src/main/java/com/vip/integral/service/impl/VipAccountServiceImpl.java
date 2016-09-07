package com.vip.integral.service.impl;

import com.vip.integral.dao.VipAccountMapper;
import com.vip.integral.model.VipAccount;
import com.vip.integral.service.VipAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/16.
 */
@Service("vipAccountService")
public class VipAccountServiceImpl implements VipAccountService {

    @Autowired
    private VipAccountMapper vipAccountMapper;

    @Override
    public VipAccount vote(Integer type) {
        if (true)
            return null;
        List<VipAccount> list = vipAccountMapper.listByTypeOrderByCountDesc(type);
        if (list == null)
            return null;
        VipAccount vipAccount = list.get(0);
        if (vipAccount.getCount() == 0)
            return null;
        return vipAccount;
    }

    @Override
    public int update(VipAccount vipAccount) {
        return vipAccountMapper.updateByPrimaryKeySelective(vipAccount);
    }

    @Override
    public int save(VipAccount vipAccount) {
        return vipAccountMapper.insert(vipAccount);
    }

    @Override
    public VipAccount get(VipAccount vipAccount) {
        return vipAccountMapper.selectByPrimaryKey(vipAccount.getId());
    }

    @Override
    public List<VipAccount> listAll() {
        return vipAccountMapper.listAll();
    }

    @Override
    public int delete(VipAccount vipAccount) {
        return vipAccountMapper.deleteByPrimaryKey(vipAccount.getId());
    }
}
