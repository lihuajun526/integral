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

        List<AttackPage> list = attackPageService.listByPointsAndLimit(Arrays.asList(33), 5);
        StringBuffer sb = new StringBuffer("515182557@qq.com;");
        for (int i = 0; i < 5 && i < list.size(); i++) {
            sb.append(list.get(i).getLink().trim()).append("@qq.com;");
        }

        try {
            HttpPost httpPost = new HttpPost("https://mail.qq.com/cgi-bin/compose_send?sid=r56ar1RJtMTEpAhS");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            httpPost.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; o_cookie=515182557; pgv_pvid=5910488650; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; skey=@ygZLFw51f; pt2gguin=o0515182557; p_uin=o0515182557; pt4_token=BOq6XhZRql*uWb3H*XqtBzzgQF2UgrCoC3EfassUKbs_; p_skey=i4ZFeDqHoQo6*2QfaZmsGvT5r15M5guAjI2d9ExWEso_; wimrefreshrun=0&; qm_antisky=-1401881807&4591721d9f1774ddec7bc356e32ae9006a071dd71c9fa12ccb4b69e899d77c0e|515182557&4768ea63acd27a542215b2bdb0c91776db489f6a1c4ca599952ded90506ef7e1; qm_flag=0; qqmail_alias=515182557@qq.com; sid=-1401881807&2c7dcbb0f8a278ea817292de33d1b83b,qYld1dE51TTlDdk5jVlVNUzYxZ3Zobzd5Uio3VEY2Nk1KZ1lYS2xGMThGa18.|515182557&0329ca29c98224508d7300e9f087b052,qaTRaRmVEcUhvUW82KjJRZmFabXNHdlQ1cjE1TTVndUFqSTJkOUV4V0Vzb18.; qm_domain=https://mail.qq.com; qm_ptsk=-1401881807&@PgNY9qH59|515182557&@ygZLFw51f; foxacc=-1401881807&0|515182557&0; ssl_edition=sail.qq.com; edition=mail.qq.com; qm_loginfrom=-1401881807&qqtips2|515182557&clientread; username=-1401881807&2893085489|515182557&515182557; CCSHOW=000001; webp=1; new_mail_num=-1401881807&0|515182557&205; qm_username=2893085489; uin=o2893085489");
            httpPost.setHeader("Origin", "https://mail.qq.com");
            httpPost.setHeader("Referer", "https://mail.qq.com/zh_CN/htmledition/ajax_proxy.html?mail.qq.com&v=140521");

            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("06ec97f7df86a60b0f61b608e8dcef9b", "2c7dcbb0f8a278ea817292de33d1b83b"));
                add(new BasicNameValuePair("sid", "r56ar1RJtMTEpAhS"));
                add(new BasicNameValuePair("from_s", "draft"));
                add(new BasicNameValuePair("to", sb.toString()));
                add(new BasicNameValuePair("subject", "《爱奇艺30天会员免费送》"));
                add(new BasicNameValuePair("content__html", "微信关注- 影咖365 -即送爱奇艺、芒果TV、乐视TV 30天黄金会员"));
                add(new BasicNameValuePair("sendmailname", "2893085489@qq.com"));
                add(new BasicNameValuePair("savesendbox", "1"));
                add(new BasicNameValuePair("actiontype", "send"));
                add(new BasicNameValuePair("sendname", "Zoe"));
                add(new BasicNameValuePair("acctid", "0"));
                add(new BasicNameValuePair("separatedcopy", "false"));
                add(new BasicNameValuePair("fmailid", "ZC0307-nK07rFdVUCAhgi50HbamZ7b"));
                add(new BasicNameValuePair("s", "comm"));
                add(new BasicNameValuePair("hitaddrbook", "0"));
                add(new BasicNameValuePair("selfdefinestation", "-1"));
                add(new BasicNameValuePair("backurl", "#"));
                add(new BasicNameValuePair("domaincheck", "0"));
                add(new BasicNameValuePair("cgitm", "1510015071601"));
                add(new BasicNameValuePair("clitm", "1510015071435"));
                add(new BasicNameValuePair("comtm", "1510017845518"));
                add(new BasicNameValuePair("logattcnt", "0"));
                add(new BasicNameValuePair("logattsize", "0"));
                add(new BasicNameValuePair("cginame", "compose_send"));
                add(new BasicNameValuePair("ef", "js"));
                add(new BasicNameValuePair("t", "compose_send.json"));
                add(new BasicNameValuePair("resp_charset", "UTF8"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            String response = XHttpClient.doRequest(httpPost);
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }


    }
}
