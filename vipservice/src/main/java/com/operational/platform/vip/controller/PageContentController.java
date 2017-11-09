package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.*;
import com.operational.platform.dbservice.service.VideoCommentService;
import com.operational.platform.dbservice.service.VideoEvaluateService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import com.operational.platform.dbservice.service.VideoTagService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/page/content")
public class PageContentController extends BaseController {

    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private VideoTagService videoTagService;
    @Autowired
    private VideoEvaluateService videoEvaluateService;
    @Autowired
    private VideoSuggestService videoSuggestService;


    @RequestMapping("/info/{videoid}/{vipAccessToken}")
    @ResponseBody
    public String info(@PathVariable Integer videoid, @PathVariable String vipAccessToken) throws UnsupportedEncodingException {
        Result<Map<String, Object>> result = new Result<>();

        Map<String, Object> info = new HashMap<>();
        User loginUser = Constant.SessionMap.get(vipAccessToken);

        List<VideoTag> tags = videoTagService.listByVideo(videoid);
        VideoEvaluate videoEvaluate = videoEvaluateService.getByUserAndVideo(loginUser.getId(), videoid);
        if (videoEvaluate.getId() != null) {
            for (VideoTag tag : tags) {
                if (videoEvaluate.getTags().contains("#" + tag.getId() + "#")) {
                    tag.setIsCheck(1);
                } else {
                    tag.setIsCheck(0);
                }
            }
        }
        VideoSuggest videoSuggest = videoSuggestService.get(videoid);

        String data = videoSuggest.getData();
        List<Map<String, String>> directors = null;
        List<Map<String, String>> actors = null;
        Integer timeLength = 90;
        if (!StringUtils.isEmpty(data)) {
            JSONObject jsonObject = JSON.parseObject(data);
            directors = JSON.parseObject(jsonObject.getString("directors"), List.class);
            actors = JSON.parseObject(jsonObject.getString("actors"), List.class);
            timeLength = jsonObject.getIntValue("timeLength");
            timeLength = timeLength == null ? 90 : timeLength / 60;
        }
        videoSuggest.setData(null);
        videoSuggest.setManual(null);
        videoSuggest.setSrcId(null);
        videoSuggest.setTimeLength(timeLength);

        List<VideoComment> comments = videoCommentService.listByVideo(videoid);
        for (VideoComment comment : comments) {
            comment.setNick(URLDecoder.decode(comment.getNick(),"UTF-8"));
            comment.setContent(URLDecoder.decode(comment.getContent(),"UTF-8"));
        }

        info.put("video", videoSuggest);
        info.put("directors", directors);
        info.put("actors", actors);
        info.put("comments", comments);
        info.put("tags", tags);
        info.put("evaluate", videoEvaluateService.getByUserAndVideo(loginUser.getId(), videoid));
        result.setData(info);

        return result.toString();
    }

    @RequestMapping("/comment/list/{videoid}")
    @ResponseBody
    public String listComment(@PathVariable Integer videoid) throws UnsupportedEncodingException {
        Result<List<VideoComment>> result = new Result<>();

        List<VideoComment> list = videoCommentService.listByVideo(videoid);

        if (list.size() > 20)
            list = list.subList(0, 20);

        for (VideoComment comment : list) {
            comment.setNick(URLDecoder.decode(comment.getNick(),"UTF-8"));
            comment.setContent(URLDecoder.decode(comment.getContent(),"UTF-8"));
        }

        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/evaluate/save")
    @ResponseBody
    public String saveEvaluate(String vipAccessToken, VideoEvaluate videoEvaluate) {
        Result result = new Result<>();

        if (StringUtils.isEmpty(videoEvaluate.getContent())) {
            result.set(-1, "评价内容不能为空");
            return result.toString();
        }

        User loginUser = Constant.SessionMap.get(vipAccessToken);
        VideoEvaluate videoEvaluateDb = videoEvaluateService.getByUserAndVideo(loginUser.getId(), videoEvaluate.getVideoid());
        videoEvaluate.setId(videoEvaluateDb.getId());
        videoEvaluate.setUserid(loginUser.getId());
        videoEvaluateService.save(videoEvaluate);

        return result.toString();
    }

    @RequestMapping("/tag/save")
    @ResponseBody
    public String saveTag(Integer videoid, String tag) {
        Result result = new Result<>();

        if (StringUtils.isEmpty(tag)) {
            result.set(-1, "标签不能为空");
            return result.toString();
        }

        if (videoTagService.listByVideoAndTag(videoid, tag.trim()).size() > 0) {
            result.set(-1, "标签已存在");
            return result.toString();
        }

        VideoTag videoTag = new VideoTag();
        videoTag.setVideoid(videoid);
        videoTag.setTag(tag);

        videoTagService.save(videoTag);

        return result.toString();
    }

    @RequestMapping("/comment/save")
    @ResponseBody
    public String saveComment(String vipAccessToken, VideoComment videoComment) {
        Result result = new Result<>();

        if (StringUtils.isEmpty(videoComment.getContent())) {
            result.set(-1, "评论内容不能为空");
            return result.toString();
        }

        User loginUser = Constant.SessionMap.get(vipAccessToken);
        videoComment.setUserid(loginUser.getId());
        videoComment.setPhoto(loginUser.getHeadimgurl());
        videoComment.setNick(loginUser.getNickname());

        videoCommentService.save(videoComment);

        return result.toString();
    }
}
