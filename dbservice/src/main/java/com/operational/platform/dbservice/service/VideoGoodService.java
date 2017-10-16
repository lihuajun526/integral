package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoGood;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/15.
 */
public interface VideoGoodService {

    VideoGood getBySuggest(Integer suggestid);

    void save(VideoGood videoGood);

    VideoGood get(Integer id);

    List<VideoGood> getLatest();
}
