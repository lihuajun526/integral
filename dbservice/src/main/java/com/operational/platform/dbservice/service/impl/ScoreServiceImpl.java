package com.operational.platform.dbservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.ScoreService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lihuajun on 2017/8/1.
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreServiceImpl.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private Calendar c = Calendar.getInstance();

    @Autowired
    private AttackPageService attackPageService;
    @Autowired
    private VideoSuggestService videoSuggestService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void score(List<Integer> pointids) {
        List<AttackPage> attackPageList = attackPageService.listByPoints(pointids);
        for (AttackPage attackPage : attackPageList) {
            try {
                int flag = attackPage.getFlag().intValue();
                JSONObject attr = JSON.parseObject(attackPage.getAttr());
                VideoSuggest videoSuggest = videoSuggestService.getBySrc(attackPage.getId());
                /*if ("true".equals(attr.getString("isPay")))//付费/用券
                    continue;
                if (flag == 0) {//已不在会员片库
                    LOGGER.info("已不在会员片库[{}]", attackPage.getAttr());
                    videoSuggestService.delBySrc(attackPage.getId());
                    attackPageService.del(attackPage.getId());
                    continue;
                } else if (flag == 1) {
                    videoSuggest = new VideoSuggest();
                    videoSuggest.setStatus(1);
                    videoSuggest.setDescription(attr.getString("desc"));
                    videoSuggest.setScore(attr.getString("score"));
                    videoSuggest.setPhoto(attr.getString("logo"));
                    videoSuggest.setUrl(attackPage.getLink());
                    videoSuggest.setTitle(attackPage.getTitle());
                    videoSuggest.setSrcId(attackPage.getId());
                    videoSuggest.setManual(0);
                    if (attackPage.getCategory().contains("电影"))
                        videoSuggest.setChannel(1);
                    else if (attackPage.getCategory().contains("电视剧"))
                        videoSuggest.setChannel(2);
                    videoSuggest.setOverallScore(getOverallScore(attr));
                    videoSuggestService.save(videoSuggest);
                } else if (flag == 2) {
                    videoSuggest.setScore(attr.getString("score"));
                    videoSuggest.setOverallScore(getOverallScore(attr));
                    videoSuggestService.update(videoSuggest);
                }*/
                if (videoSuggest == null) {
                    videoSuggest = new VideoSuggest();
                    videoSuggest.setStatus(1);
                    videoSuggest.setDescription(attr.getString("desc"));
                    videoSuggest.setScore(attr.getString("score"));
                    videoSuggest.setPhoto(attr.getString("logo"));
                    videoSuggest.setUrl(attackPage.getLink());
                    videoSuggest.setTitle(attackPage.getTitle());
                    videoSuggest.setSrcId(attackPage.getId());
                    videoSuggest.setManual(0);
                    if (attackPage.getCategory().contains("电影"))
                        videoSuggest.setChannel(1);
                    else if (attackPage.getCategory().contains("电视剧"))
                        videoSuggest.setChannel(2);
                    videoSuggest.setOverallScore(getOverallScore(attr));
                    videoSuggestService.save(videoSuggest);
                } else {
                    videoSuggest.setScore(attr.getString("score"));
                    videoSuggest.setOverallScore(getOverallScore(attr));
                    videoSuggestService.update(videoSuggest);
                }
            } catch (Exception e) {
                LOGGER.error("对[{}]进行评分失败", attackPage.getId(), e);
            }
        }
        //恢复attackPage中flag的默认值
        attackPageService.recoverFlag(pointids);
    }

    private float getOverallScore(JSONObject attr) {
        /**
         * 评分规则，总分100
         * 1、平台的评分占90%
         * 2、是否独播占5%
         * 3、上映日期占5%（
         最近二个月100%
         最近四个月%60%
         最近半年40%
         最近一年20%
         * ）
         */
        String score = "6.0";
        if (!StringUtils.isEmpty(attr.getString("score")))
            score = attr.getString("score");
        float overAllScore = 0f;
        overAllScore = Float.valueOf(score) * 10 * 90 / 100;
        String releaseDate = attr.getString("releaseDate");
        if (!StringUtils.isEmpty(releaseDate)) {
            try {
                if (releaseDate.length() == 4)
                    releaseDate = releaseDate + "0101";
                Date dReleaseDate = sdf.parse(releaseDate);
                c.setTime(new Date());
                c.add(Calendar.MONTH, -2);
                if (dReleaseDate.getTime() >= c.getTime().getTime()) {//近两个月
                    overAllScore += 5;
                } else {
                    c.add(Calendar.MONTH, -2);
                    if (dReleaseDate.getTime() >= c.getTime().getTime()) {//近四个月
                        overAllScore += 5 * 3 / 5;
                    } else {
                        c.add(Calendar.MONTH, -2);
                        if (dReleaseDate.getTime() >= c.getTime().getTime()) {//近半年
                            overAllScore += 5 * 2 / 5;
                        } else {
                            c.add(Calendar.MONTH, -6);
                            if (dReleaseDate.getTime() >= c.getTime().getTime()) {//近一年
                                overAllScore += 5 * 1 / 5;
                            }
                        }
                    }
                }
            } catch (ParseException e) {
                LOGGER.error("字符串[{}]转日期失败", releaseDate);
            }

        }
        Boolean isExclusive = attr.getBoolean("isExclusive");
        if (isExclusive != null && isExclusive)
            overAllScore += 5;
        return overAllScore;
    }
}
