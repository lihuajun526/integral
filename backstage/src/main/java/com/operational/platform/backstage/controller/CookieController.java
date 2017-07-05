package com.operational.platform.backstage.controller;

import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.VipPlatform;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/cookie")
public class CookieController extends BaseController {


    @RequestMapping("/get")
    @ResponseBody
    public String get(String url) {

        Result<String> result = new Result<>();
        if (StringUtils.isEmpty(url)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "url不能为空");
            return result.toString();
        }

        if (url.indexOf(VipPlatform.Iqy.domain) != -1) {

        } else if (url.indexOf(VipPlatform.Youku.domain) != -1) {

        } else if (url.indexOf(VipPlatform.Txsp.domain) != -1) {

        }


        result.setData("QC006=593e71d502f456980546df603341c504; __uuid=f9f6c1a3-635d-55bc-4f45-0dd244d63da4; QC005=19741c29a31aa6c016dc78f0613b743e; P00004=1998006165.1499173721.8b7d791aa9; P00001=4fUkDnuS61G8Ow4AvQOR4cdf5ktKrscm1cz0kkasiym3U6cFgA9tgBakDTLZTBHGXgkF69; P00003=85840559; P00010=85840559; P01010=1499184000; P00007=4fUkDnuS61G8Ow4AvQOR4cdf5ktKrscm1cz0kkasiym3U6cFgA9tgBakDTLZTBHGXgkF69; P00PRU=85840559; P00002=%7B%22uid%22%3A%2285840559%22%2C%22user_name%22%3A%22lihuajun526%22%2C%22email%22%3A%22515182557%40qq.com%22%2C%22nickname%22%3A%22heiyq365%22%2C%22pru%22%3A85840559%2C%22type%22%3A13%2C%22pnickname%22%3A%22heiyq365%22%7D; P000email=515182557%40qq.com; QC007=http%3A%2F%2Fm.iqiyi.com%2Fuser.html; QC008=88d3a26c7f7b09ee7740969bf7f3b184; __dfp=e18479664c55b742a796c470457f69628e5a416259ad43975e2ed3813b6d6bb815@1501765690871@1499173728871; Hm_lvt_5df871ab99f94347b23ca224fc7d013f=1499173724; Hm_lpvt_5df871ab99f94347b23ca224fc7d013f=1499173729");

        return result.toString();
    }


}
