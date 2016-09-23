package com.vip.dbservice.service;

import com.vip.dbservice.model.WechatMsg;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface WechatService {

    String handle(WechatMsg wechatMsg);

}
