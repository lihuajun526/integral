package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.PlayRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.PlayRecordService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/play/record")
public class PlayRecordController extends BaseController {

    @Autowired
    private PlayRecordService playRecordService;

    /**
     * 保存播放记录
     *
     * @return
     */
    @RequestMapping("/save/v_login")
    @ResponseBody
    public String save(String url, String vipAccessToken) {

        Result result = new Result<>();

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        PlayRecord playRecord = new PlayRecord();
        try {
            playRecord.setUrl(URLDecoder.decode(url, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("解码失败[{}]", url);
            playRecord.setUrl(url);
        }
        playRecord.setUserid(loginUser.getId());

        playRecordService.save(playRecord);

        return result.toString();
    }

    /**
     * 获取最近播放记录
     *
     * @return
     */
    @RequestMapping("/list/latest/v_login")
    @ResponseBody
    public String listLatest(String vipAccessToken) {

        Result<List<PlayRecord>> result = new Result<>();

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        List<PlayRecord> list = playRecordService.listLatestByUser(loginUser);
        result.setData(list);

        return result.toString();
    }
}
