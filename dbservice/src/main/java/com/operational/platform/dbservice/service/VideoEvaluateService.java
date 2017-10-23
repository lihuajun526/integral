package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoEvaluate;

/**
 * Created by lihuajun on 2017/10/23.
 */
public interface VideoEvaluateService {

    VideoEvaluate getByUserAndVideo(Integer userid,Integer videoid);

    void save(VideoEvaluate videoEvaluate);

}
