package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoComment;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/23.
 */
public interface VideoCommentService {

    List<VideoComment> listByVideo(Integer videoid);

    void save(VideoComment videoComment);

}
