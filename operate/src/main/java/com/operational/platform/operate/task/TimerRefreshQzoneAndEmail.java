package com.operational.platform.operate.task;

import com.operational.platform.common.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by lihuajun on 16-7-19.
 */
@Component
public class TimerRefreshQzoneAndEmail {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerRefreshQzoneAndEmail.class);

    public void execute() {

        LOGGER.info("刷新");
        try {
            //刷新qq空间
            HttpGet httpGet = new HttpGet("https://user.qzone.qq.com/2893085489/infocenter");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            httpGet.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; __Q_w_s__QZN_TodoMsgCnt=1; o_cookie=515182557; pgv_pvid=5910488650; qzspeedup=sdch; pgv_info=ssid=s9142869367; QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=1; verifysession=h015d4058121e498bb2717cac32bf519409662fd61fa344e47de43b80deabab219016c08a3441212220; welnewuser=1; Loading=Yes; qz_screen=1366x768; 2893085489_todaycount=0; 2893085489_totalcount=0; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; uin=o2893085489; skey=@PgNY9qH59; pt2gguin=o2893085489; p_uin=o2893085489; pt4_token=NcdsfXOLjcwqJwCAtar7v7xiwqOwWpiSnhiXuMoI9I8_; p_skey=4ETib48cezvW04OtwW5m8W4I7VJ-ca-gppK8d*sfP48_; qzone_check=2893085489_1509938485");
            XHttpClient.doRequest(httpGet);

            //刷新邮箱
            httpGet.setURI(new URI("https://mail.qq.com/cgi-bin/frame_html?sid=r56ar1RJtMTEpAhS&r=47d549b07b9bb7d4d61ad6963eae9f7e"));
            httpGet.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; o_cookie=515182557; pgv_pvid=5910488650; pgv_info=ssid=s9142869367; verifysession=h015d4058121e498bb2717cac32bf519409662fd61fa344e47de43b80deabab219016c08a3441212220; welnewuser=1; pgv_si=s2830554112; qqmail_activated=-1401881807&0; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; uin=o2893085489; skey=@PgNY9qH59; pt2gguin=o2893085489; p_uin=o2893085489; pt4_token=ZoApcznRHhu1-cueK5HZ96YSWpcRQEIBi6z2XG1fnxE_; p_skey=AEerfv90h1kEfFuud-3vm0mndzWhgsUDpcjEeK9IgxA_; wimrefreshrun=0&; qm_antisky=-1401881807&2559897c8e1ccd72eb2fe444a9e8473ebb5e8cb2cddf2d9b7ebad67d08148ec4|-1096474153&17deef79afab29dc2f95372f7f6e83c6d820419a76b53381a21a631496b2e73f|515182557&f45377d7993eda0ad04cfdd0dcb692f7b99c702d53425dddb7cbe00a9d6c9344; qm_flag=0; qqmail_alias=2893085489@qq.com; sid=-1401881807&2c7dcbb0f8a278ea817292de33d1b83b,qQUVlcmZ2OTBoMWtFZkZ1dWQtM3ZtMG1uZHpXaGdzVURwY2pFZUs5SWd4QV8.|-1096474153&8da5a1e53c14a00d9b73d2cca3e8ee8e,qVnFIVGwyVXl0bTNMRWZiRVEtZTdsNnVFU2Jta1dMS1hPV0drdTlWbUh3WV8.|515182557&a196f00cd395b09656900b9510a1dc09,qc3huRjhwNml3eDBpc3Y2alRIckh6U2lRcnRtdTVkT2dIMmg1VTU0Y0RIUV8.; qm_username=2893085489; qm_lg=qm_lg; qm_domain=https://mail.qq.com; qm_ptsk=-1096474153&@UmGvgShGM|515182557&@NzMQVQfqY|-1401881807&@PgNY9qH59; foxacc=-1096474153&0|515182557&0|-1401881807&0; ssl_edition=sail.qq.com; edition=mail.qq.com; qm_loginfrom=-1096474153&clientread|515182557&qqtips2|-1401881807&clientread; username=-1401881807&2893085489|-1096474153&3198493143|515182557&515182557; webp=1; CCSHOW=000001; new_mail_num=-1401881807&0|-1096474153&0|515182557&0");
            XHttpClient.doRequest(httpGet);

        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
    }
}
