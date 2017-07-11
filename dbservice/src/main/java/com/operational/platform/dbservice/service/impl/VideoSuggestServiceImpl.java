package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.VideoSuggestMapper;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.model.VideoSuggestExample;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/7/11.
 */
@Service("videoSuggestService")
public class VideoSuggestServiceImpl implements VideoSuggestService {

    @Autowired
    private VideoSuggestMapper videoSuggestMapper;

    @Override
    public List<VideoSuggest> list() {

        VideoSuggestExample example = new VideoSuggestExample();
        VideoSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("order_no");

        return videoSuggestMapper.selectByExample(example);
    }
}
