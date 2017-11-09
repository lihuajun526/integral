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
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getLink().trim()).append("@qq.com;");
        }

        try {
            HttpPost httpPost = new HttpPost("https://mail.qq.com/cgi-bin/compose_send?sid=HC3NLtWJ8ICC_1cN");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            httpPost.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; pgv_pvid=5910488650; o_cookie=3135588298; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; uin=o2893085489; skey=@kBJteDFEk; pt2gguin=o2893085489; p_uin=o2893085489; pt4_token=g2zSXx1U4*sWiJ0Sdgt8V8NAvazpHNk9WpB9sB5dsA4_; p_skey=Bm7pZvvYHwPCQf2hKwiPfcQNFzoc3zY9OjHVsB6F*0c_; wimrefreshrun=0&; qm_antisky=-1401881807&b91e605292dc11e7dfb107d71c81850b694d599f31fd5ff3a7032eb08f60cc1f; qm_flag=0; qqmail_alias=2893085489@qq.com; sid=-1401881807&c27825088e46ba4810f1af7527602c25,qQm03cFp2dllId1BDUWYyaEt3aVBmY1FORnpvYzN6WTlPakhWc0I2RiowY18.; qm_username=2893085489; qm_domain=https://mail.qq.com; qm_ptsk=-1401881807&@kBJteDFEk; foxacc=-1401881807&0; ssl_edition=sail.qq.com; edition=mail.qq.com; qm_loginfrom=-1401881807&clientread; username=-1401881807&2893085489; webp=1; new_mail_num=-1401881807&0; CCSHOW=100001");
            httpPost.setHeader("Origin", "https://mail.qq.com");
            httpPost.setHeader("Referer", "https://mail.qq.com/zh_CN/htmledition/ajax_proxy.html?mail.qq.com&v=140521");

            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("c6a5f281e6c6c13259551158aca2efa6", "c27825088e46ba4810f1af7527602c25"));
                add(new BasicNameValuePair("sid", "HC3NLtWJ8ICC_1cN"));
                add(new BasicNameValuePair("from_s", "cnew"));
                add(new BasicNameValuePair("to", sb.toString()));
                add(new BasicNameValuePair("subject", "《爱奇艺30天会员免费送》"));
                add(new BasicNameValuePair("content__html", "微信关注- 影咖365 -即送爱奇艺、芒果TV、乐视TV 30天黄金会员"));
                add(new BasicNameValuePair("sendmailname", "2893085489@qq.com"));
                add(new BasicNameValuePair("savesendbox", "1"));
                add(new BasicNameValuePair("actiontype", "send"));
                add(new BasicNameValuePair("sendname", "影咖365"));
                add(new BasicNameValuePair("acctid", "0"));
                add(new BasicNameValuePair("separatedcopy", "false"));
                //add(new BasicNameValuePair("fmailid", "ZC0307-nK07rFdVUCAhgi50HbamZ7b"));
                add(new BasicNameValuePair("s", "comm"));
                add(new BasicNameValuePair("hitaddrbook", "0"));
                add(new BasicNameValuePair("selfdefinestation", "-1"));
                //add(new BasicNameValuePair("backurl", "#"));
                add(new BasicNameValuePair("domaincheck", "0"));
                add(new BasicNameValuePair("cgitm", "1510104289101"));
                add(new BasicNameValuePair("clitm", "1510104287939"));
                add(new BasicNameValuePair("comtm", "1510104757937"));
                add(new BasicNameValuePair("logattcnt", "0"));
                add(new BasicNameValuePair("logattsize", "0"));
                add(new BasicNameValuePair("cginame", "compose_send"));
                add(new BasicNameValuePair("ef", "js"));
                add(new BasicNameValuePair("t", "compose_send.json"));
                add(new BasicNameValuePair("resp_charset", "UTF8"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            String response = XHttpClient.doRequest(httpPost);
            LOGGER.info(response);
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }


    }
}
