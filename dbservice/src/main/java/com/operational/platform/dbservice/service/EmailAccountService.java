package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.EmailAccount;

import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
public interface EmailAccountService {

    List<EmailAccount> listByPage(Integer page,Integer rows);

    Integer countByPage();

    void save(EmailAccount emailAccount);

    EmailAccount get(Integer id);

}
