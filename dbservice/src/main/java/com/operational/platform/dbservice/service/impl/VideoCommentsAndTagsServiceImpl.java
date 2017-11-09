package com.operational.platform.dbservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.VideoComment;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.model.VideoTag;
import com.operational.platform.dbservice.service.*;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lihuajun on 2017/8/1.
 */
@Service("videoCommentsAndTagsService")
public class VideoCommentsAndTagsServiceImpl implements VideoCommentsAndTagsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoCommentsAndTagsServiceImpl.class);

    @Autowired
    private VideoSuggestService videoSuggestService;
    @Autowired
    private AttackPageService attackPageService;
    @Autowired
    private VideoTagService videoTagService;
    @Autowired
    private VideoCommentService videoCommentService;

    @Override
    public void add() {

        List<VideoSuggest> list = videoSuggestService.listAll();

        for (VideoSuggest videoSuggest : list) {
            try {
                JSONObject attr = JSON.parseObject(attackPageService.get(videoSuggest.getSrcId()).getAttr());
                //保存tag
                if (videoTagService.listByVideo(videoSuggest.getId()).size() == 0) {
                    List<String> tags = JSON.parseObject(attr.getString("tags"), List.class);
                    for (int i = 0; tags != null && i < tags.size(); i++) {
                        VideoTag videoTag = new VideoTag();
                        videoTag.setTag(tags.get(i));
                        videoTag.setVideoid(videoSuggest.getId());
                        videoTagService.save(videoTag);
                    }
                }
                //保存评论
                if (videoCommentService.listByVideo(videoSuggest.getId()).size() == 0) {
                    String vId = null;
                    if (videoSuggest.getChannel() == 1) {//电影
                        vId = attr.getString("docid");
                    } else if (videoSuggest.getChannel() == 2) {
                        vId = attr.getString("tvid");
                    }
                    if (StringUtils.isEmpty(vId)) {
                        continue;
                    }
                    String commentUrl = String.format("http://api-t.iqiyi.com/qx_api/comment/get_video_comments?albumid=%s&categoryid=1&need_subject=true&need_total=1&page=1&page_size=10&qitan_comment_type=1&sort=hot&tvid=%s", vId, vId);
                    Thread.sleep(1500);
                    String result = XHttpClient.doRequest(new HttpGet(commentUrl));
                    JSONArray jsonArray = JSON.parseObject(result).getJSONObject("data").getJSONArray("comments");
                    if (jsonArray.size() == 0) {
                        LOGGER.info("[{}]评论不存在", videoSuggest.getSrcId());
                    }
                    for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
                        JSONObject comment = jsonArray.getJSONObject(i);
                        VideoComment videoComment = new VideoComment();
                        videoComment.setNick(URLEncoder.encode(comment.getJSONObject("userInfo").getString("uname"), "utf-8"));
                        videoComment.setPhoto(comment.getJSONObject("userInfo").getString("icon"));
                        videoComment.setVideoid(videoSuggest.getId());
                        videoComment.setContent(URLEncoder.encode(comment.getString("content"), "utf-8"));
                        videoCommentService.save(videoComment);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("给[{}]添加评论或tag失败", videoSuggest.getId(), e);
            }
        }
    }


}
