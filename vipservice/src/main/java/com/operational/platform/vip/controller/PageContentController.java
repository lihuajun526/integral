package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.VideoComment;
import com.operational.platform.dbservice.model.VideoEvaluate;
import com.operational.platform.dbservice.model.VideoTag;
import com.operational.platform.dbservice.service.VideoCommentService;
import com.operational.platform.dbservice.service.VideoEvaluateService;
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

import java.util.*;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/page/content")
public class PageContentController extends BaseController {

    private List<List<String>> tagList = new ArrayList<List<String>>() {{
        add(Arrays.asList("欧美", "动作", "喜剧", "爱情"));
        add(Arrays.asList("大陆", "推理", "剧情", "伦理"));
        add(Arrays.asList("欧美", "科幻", "好莱坞", "冒险"));
        add(Arrays.asList("港台", "动作", "警匪", "剧情"));
        add(Arrays.asList("大陆", "历史", "悬疑", "动作"));
    }};

    private String[] sTags = {"欧美", "喜剧", "爱情", "推理", "伦理", "科幻", "好莱坞", "冒险", "港台", "动作", "警匪", "剧情", "大陆", "历史", "悬疑"};

    @Autowired
    private VideoCommentService videoCommentService;
    @Autowired
    private VideoTagService videoTagService;
    @Autowired
    private VideoEvaluateService videoEvaluateService;


    @RequestMapping("/info/{videoid}/{vipAccessToken}")
    @ResponseBody
    public String info(@PathVariable Integer videoid, @PathVariable String vipAccessToken) {
        Result<Map<String, Object>> result = new Result<>();

        Map<String, Object> info = new HashMap<>();
        User loginUser = Constant.SessionMap.get(vipAccessToken);

        List<VideoTag> tags = new ArrayList<>();
        List<VideoTag> cTags = videoTagService.listByVideoAndUser(videoid, loginUser.getId());
        if (cTags.size() < 4) {
            tags.addAll(tags);
            for (int i = 0; i < 4 - cTags.size(); i++) {
                for (String tag : sTags) {
                    boolean isHas = false;
                    for (VideoTag videoTag : cTags) {
                        if (tag.equals(videoTag.getTag())) {
                            isHas = true;
                            break;
                        }
                    }
                    if (!isHas) {
                        VideoTag videoTag = new VideoTag();
                        videoTag.setTag(tag);
                        videoTag.setIsCheck(0);
                        tags.add(videoTag);
                        break;
                    }
                }
            }
        } else
            tags = cTags.subList(0, 4);

        info.put("comments", videoCommentService.listByVideo(videoid));
        info.put("tags", tags);
        info.put("evaluate", videoEvaluateService.getByUserAndVideo(loginUser.getId(), videoid));
        result.setData(info);

        return result.toString();
    }

    @RequestMapping("/comment/list/{videoid}/{vipAccessToken}")
    @ResponseBody
    public String listComment(@PathVariable Integer videoid, @PathVariable String vipAccessToken) {
        Result<List<VideoComment>> result = new Result<>();

        List<VideoComment> list = videoCommentService.listByVideo(videoid);

        if (list.size() > 20)
            list = list.subList(0, 20);

        result.setData(list);

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
        videoComment.setPhoto("http://www.yka365.com/upload/supervip/yk365.jpg");

        videoCommentService.save(videoComment);

        return result.toString();
    }

    @RequestMapping("/tag/save/{videoid}/{vipAccessToken}/{tag}")
    @ResponseBody
    public String saveTag(@PathVariable Integer videoid, @PathVariable String vipAccessToken, @PathVariable String tag) {
        Result result = new Result<>();

        if (StringUtils.isEmpty(tag)) {
            result.set(-1, "tag不能为空");
            return result.toString();
        }

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        VideoTag videoTag = new VideoTag();
        videoTag.setVideoid(videoid);
        videoTag.setUserid(loginUser.getId());
        videoTag.setTag(tag);

        videoTagService.save(videoTag);

        return result.toString();
    }

    @RequestMapping("/evaluate/save/{videoid}/{vipAccessToken}/{isLike}")
    @ResponseBody
    public String saveEvaluate(@PathVariable Integer videoid, @PathVariable String vipAccessToken, @PathVariable Integer isLike) {
        Result result = new Result<>();

        User loginUser = Constant.SessionMap.get(vipAccessToken);
        VideoEvaluate videoEvaluate = new VideoEvaluate();
        videoEvaluate.setUserid(loginUser.getId());
        videoEvaluate.setIsLike(isLike);
        videoEvaluate.setVideoid(videoid);

        videoEvaluateService.save(videoEvaluate);

        return result.toString();
    }

    private List<String> randomList() {
        Random r = new Random();

        return tagList.get(r.nextInt(tagList.size()));
    }
}
