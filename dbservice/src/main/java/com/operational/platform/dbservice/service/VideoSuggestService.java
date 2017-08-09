package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.VideoSuggest;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface VideoSuggestService {


    List<VideoSuggest> listAll();

    void save(VideoSuggest videoSuggest);

    void update(VideoSuggest videoSuggest);

    List<VideoSuggest> listByStatus(Integer status);

    void delBySrc(Integer srcId);

    VideoSuggest getBySrc(Integer srcId);

    List<VideoSuggest> listByChnlAndPage(Integer channelid,Integer pagesize,Integer pageindex,String keyword);

    Long countByChnlAndPage(Integer channelid,String keyword);

}
