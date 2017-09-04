package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.EmailAccountMapper;
import com.operational.platform.dbservice.model.EmailAccount;
import com.operational.platform.dbservice.service.EmailAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/9/4.
 */
@Service("emailAccountService")
public class EmailAccountServiceImpl implements EmailAccountService {

    @Autowired
    private EmailAccountMapper emailAccountMapper;


    @Override
    public List<EmailAccount> listByPage(Integer pageindex, Integer pagesize) {

        Map<String, Integer> condition = new HashMap<>();
        condition.put("pagesize", pagesize);
        condition.put("start", (pageindex - 1) * pagesize);

        return emailAccountMapper.listByPage(condition);
    }

    @Override
    public Integer countByPage() {
        return emailAccountMapper.countByPage();
    }

    @Override
    public void save(EmailAccount emailAccount) {
        if (emailAccount.getId() == null || emailAccount.getId().intValue() == 0)
            emailAccountMapper.insert(emailAccount);
        else
            emailAccountMapper.updateByPrimaryKeySelective(emailAccount);
    }

    @Override
    public EmailAccount get(Integer id) {
        return emailAccountMapper.selectByPrimaryKey(id);
    }
}
