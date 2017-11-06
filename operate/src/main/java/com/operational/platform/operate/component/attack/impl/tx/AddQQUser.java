package com.operational.platform.operate.component.attack.impl.tx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.AttackerAttr;
import com.operational.platform.dbservice.service.AttackerAttrService;
import com.operational.platform.operate.bean.IMessage;
import com.operational.platform.operate.component.attack.Attacker;
import com.operational.platform.operate.component.attack.bean.QZoneParam;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/11/3.
 */
@Component
public class AddQQUser extends Attacker {

    @Override
    public void exe(IMessage iMessage) throws CommonException {
        try {
            AttackerAttr attackerAttr = iMessage.getAttackerAttr();
            QZoneParam qZoneParam = JSON.parseObject(JSON.parseObject(attackerAttr.getData()).getString("qzone"), QZoneParam.class);
            httpPost.setURI(new URI("https://user.qzone.qq.com/proxy/domain/w.qzone.qq.com/cgi-bin/tfriend/friend_addfriend.cgi?qzonetoken=" + qZoneParam.getQzonetoken() + "&g_tk=" + qZoneParam.getgTk()));
            httpPost.setHeader("Cookie", qZoneParam.getCookie());
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            httpPost.setHeader("Referer", "https://user.qzone.qq.com/" + iMessage.getTarget() + "/infocenter");
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("sid", "0"));
                add(new BasicNameValuePair("ouin", iMessage.getTarget()));
                add(new BasicNameValuePair("uin", attackerAttr.getName()));
                add(new BasicNameValuePair("fuin", iMessage.getTarget()));
                add(new BasicNameValuePair("fupdate", "1"));
                add(new BasicNameValuePair("qzreferrer", "https%3A%2F%2Fuser.qzone.qq.com%2F" + iMessage.getTarget() + "%2Finfocenter"));
                add(new BasicNameValuePair("rd", "0.08724932846711231"));
                add(new BasicNameValuePair("strmsg", ""));
                add(new BasicNameValuePair("groupId", "0"));
                add(new BasicNameValuePair("realname", ""));
                add(new BasicNameValuePair("flag", "0"));
                add(new BasicNameValuePair("key", ""));
                add(new BasicNameValuePair("verify", ""));
                add(new BasicNameValuePair("im", "0"));
                add(new BasicNameValuePair("format", "fs"));
                add(new BasicNameValuePair("from", "9"));
                add(new BasicNameValuePair("from_source", "3"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            response = response.substring(response.indexOf("frameElement.callback(") + 23, response.indexOf(");\n</script>"));
            JSONObject jsonObject = JSON.parseObject(response);
            if (jsonObject.getIntValue("code") != 0) {
                LOGGER.error("攻击者[{}]添加好友[{}]失败[{}]", attackerAttr.getName(), iMessage.getTarget(), jsonObject.getString("message"));
                throw new CommonException(ExceptionTypeEnum.Attack_ERROR);
            }

        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new CommonException(ExceptionTypeEnum.Attack_ERROR);
        }
    }

    @Override
    public void init() {

    }


}
