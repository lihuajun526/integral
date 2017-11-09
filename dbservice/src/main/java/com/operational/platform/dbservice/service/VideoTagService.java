package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoTag;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/23.
 */
public interface VideoTagService {

    List<VideoTag> listByVideoAndUser(Integer videoid,Integer userid);

    void save(VideoTag videoTag);

    List<VideoTag> listByVideo(Integer videoid);

}
