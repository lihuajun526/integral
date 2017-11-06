package com.operational.platform.operate.task;

import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lihuajun on 16-7-19.
 */
@Component
public class TimerSendEmail {

    @Autowired
    private AttackPageService attackPageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerSendEmail.class);

    public void execute() {

        List<AttackPage> list =  attackPageService.listByPoints(Arrays.asList(33));
        StringBuffer sb = new StringBuffer("515182557@qq.com;");
        for(int i=0;i<6&& i<list.size();i++){
            sb.append(list.get(i).getLink().trim()).append("@qq.com;");
        }

        try{
            HttpPost httpPost = new HttpPost("https://mail.qq.com/cgi-bin/compose_send?sid=r56ar1RJtMTEpAhS");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            httpPost.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; o_cookie=515182557; pgv_pvid=5910488650; pgv_info=ssid=s9142869367; verifysession=h015d4058121e498bb2717cac32bf519409662fd61fa344e47de43b80deabab219016c08a3441212220; welnewuser=1; pgv_si=s2830554112; qqmail_activated=-1401881807&0; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; skey=@UmGvgShGM; pt2gguin=o3198493143; p_uin=o3198493143; pt4_token=llxrsCYX10N*GezIRYNwjzylJpomJN7tESv2jX3rXH8_; p_skey=VqHTl2Uytm3LEfbEQ-e7l6uESbmkWLKXOWGku9VmHwY_; wimrefreshrun=0&; qm_antisky=-1401881807&45064446bf4a1a1a45d3e97bfb121c8d55da0bac133e199a7c779897e7fb7c74|-1096474153&17deef79afab29dc2f95372f7f6e83c6d820419a76b53381a21a631496b2e73f; qm_flag=0; qqmail_alias=3198493143@qq.com; sid=-1401881807&2c7dcbb0f8a278ea817292de33d1b83b,qRzRZeGRLZzJzOVkwMUR6WHR5NFZ6Yi1YMEVqMkVIaUpDZ1JZbktrMHdpVV8.|-1096474153&8da5a1e53c14a00d9b73d2cca3e8ee8e,qVnFIVGwyVXl0bTNMRWZiRVEtZTdsNnVFU2Jta1dMS1hPV0drdTlWbUh3WV8.; qm_domain=https://mail.qq.com; qm_ptsk=-1096474153&@UmGvgShGM; foxacc=-1096474153&0; ssl_edition=sail.qq.com; edition=mail.qq.com; qm_loginfrom=-1096474153&clientread; username=-1401881807&2893085489|-1096474153&3198493143; new_mail_num=-1401881807&0|-1096474153&0; qm_username=2893085489; uin=o2893085489; webp=1; CCSHOW=000001");

            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("06ec97f7df86a60b0f61b608e8dcef9b", "2c7dcbb0f8a278ea817292de33d1b83b"));
                add(new BasicNameValuePair("sid", "r56ar1RJtMTEpAhS"));
                add(new BasicNameValuePair("from_s", "cnew"));
                add(new BasicNameValuePair("to", sb.toString()));
                add(new BasicNameValuePair("subject", "《爱奇艺30天会员免费送》"+System.currentTimeMillis()));
                add(new BasicNameValuePair("content__html", "wx关注<影咖365>即送爱奇艺、芒果TV、乐视TV 30天黄金会员"+System.currentTimeMillis()));
                add(new BasicNameValuePair("sendmailname", "2893085489@qq.com"));
                add(new BasicNameValuePair("savesendbox", "1"));
                add(new BasicNameValuePair("actiontype", "send"));
                add(new BasicNameValuePair("sendname", "Zoe"));
                add(new BasicNameValuePair("acctid", "0"));
                add(new BasicNameValuePair("separatedcopy", "false"));
                add(new BasicNameValuePair("fmailid", "ZD5506-_s8MPcbm3q6unTFrZlSrC7b"));
                add(new BasicNameValuePair("s", "comm"));
                add(new BasicNameValuePair("hitaddrbook", "0"));
                add(new BasicNameValuePair("selfdefinestation", "-1"));
                add(new BasicNameValuePair("domaincheck", "0"));
                add(new BasicNameValuePair("cgitm", "1509957522536"));
                add(new BasicNameValuePair("cgitm", "1509957522536"));
                add(new BasicNameValuePair("clitm", "1509957522941"));
                add(new BasicNameValuePair("comtm", "1509957882118"));
                add(new BasicNameValuePair("logattcnt", "0"));
                add(new BasicNameValuePair("logattsize", "0"));
                add(new BasicNameValuePair("cginame", "compose_send"));
                add(new BasicNameValuePair("ef", "js"));
                add(new BasicNameValuePair("t", "compose_send.json"));
                add(new BasicNameValuePair("resp_charset", "UTF8"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            XHttpClient.doRequest(httpPost);
        }catch (Exception e){
            LOGGER.error("error:",e);
        }



    }
}
