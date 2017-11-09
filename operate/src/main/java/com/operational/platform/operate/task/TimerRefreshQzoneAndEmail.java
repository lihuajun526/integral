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
            httpGet.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; __Q_w_s__QZN_TodoMsgCnt=1; cpu_performance_v8=1; QZ_FE_WEBP_SUPPORT=1; qz_screen=1366x768; pgv_pvid=5910488650; o_cookie=3135588298; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; uin=o2893085489; skey=@kBJteDFEk; pt2gguin=o2893085489; p_uin=o2893085489; pt4_token=-2CWMqHXBUxpAPE50jQ4cH04wRucb0WO20NasnTt534_; p_skey=vOSBQGJ91-8X841P03BGCSvv4ozjLMzEYvDflVJnFqs_; qzone_check=2893085489_1510105338");
            XHttpClient.doRequest(httpGet);

            //刷新邮箱
            httpGet.setURI(new URI("https://mail.qq.com/cgi-bin/frame_html?sid=vXfRldKNDOnr_0Eb&r=072dbac6ff34affcf123a1553343f316"));
            httpGet.setHeader("Cookie", "RK=gfHnTGl/GQ; pac_uid=1_515182557; pgv_pvi=7638767616; tvfe_boss_uuid=dce7e63a29125d71; mobileUV=1_15e737c0a19_66bf0; ptui_loginuin=3198493143; pgv_pvid=5910488650; o_cookie=3135588298; ptisp=ctc; ptcz=d99836524cc8fe4d0029935b94ac1c9287f51ce838859204cbc2c646b4f5d19f; uin=o3135588298; skey=@RqWSWPMCy; pt2gguin=o3135588298; p_uin=o3135588298; pt4_token=38PxoQc4rMm94sFYCHBXBUZlV3Uzaa*4sT6P1AlP-DQ_; p_skey=bQ5Ud3EIRcx84K-rV3keYtIg*uIakqmIrpQIg8vTUF8_; wimrefreshrun=0&; qm_antisky=-1401881807&b91e605292dc11e7dfb107d71c81850b694d599f31fd5ff3a7032eb08f60cc1f|-1159378998&3909e0f12232533affc2c5d5aeff21c05c0299438169b9a4250c867ab6c0a250; qm_flag=0; qqmail_alias=3135588298@qq.com; sid=-1401881807&c27825088e46ba4810f1af7527602c25,qQm03cFp2dllId1BDUWYyaEt3aVBmY1FORnpvYzN6WTlPakhWc0I2RiowY18.|-1159378998&efbf97cef86f4a467c5ed154843d154d,qYlE1VWQzRUlSY3g4NEstclYza2VZdElnKnVJYWtxbUlycFFJZzh2VFVGOF8.; qm_username=3135588298; qm_domain=https://mail.qq.com; qm_ptsk=-1401881807&@kBJteDFEk|-1159378998&@RqWSWPMCy; foxacc=-1401881807&0|-1159378998&0; ssl_edition=sail.qq.com; edition=mail.qq.com; qm_loginfrom=-1401881807&clientread|-1159378998&clientread; username=-1401881807&2893085489|-1159378998&3135588298; CCSHOW=100001; webp=1; new_mail_num=-1401881807&0|-1159378998&1");
            XHttpClient.doRequest(httpGet);

        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
    }
}
