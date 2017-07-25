package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoSuggest;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface VideoSuggestService {


    List<VideoSuggest> listAll();

    void save(VideoSuggest videoSuggest);

    void update(VideoSuggest videoSuggest);

    List<VideoSuggest> listByStatus(Integer status);

}
