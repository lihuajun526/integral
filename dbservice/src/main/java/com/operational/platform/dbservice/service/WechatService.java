package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.WechatMsg;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface WechatService {

    String handle(WechatMsg wechatMsg);

}
