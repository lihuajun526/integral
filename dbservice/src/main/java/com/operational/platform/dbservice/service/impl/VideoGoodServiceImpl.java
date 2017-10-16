package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.VideoGoodMapper;
import com.operational.platform.dbservice.model.VideoGood;
import com.operational.platform.dbservice.model.VideoGoodExample;
import com.operational.platform.dbservice.service.VideoGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/15.
 */
@Service
public class VideoGoodServiceImpl implements VideoGoodService {

    @Autowired
    private VideoGoodMapper videoGoodMapper;

    @Override
    public VideoGood getBySuggest(Integer suggestid) {
        VideoGoodExample example = new VideoGoodExample();
        VideoGoodExample.Criteria criteria = example.createCriteria();
        criteria.andSuggestidEqualTo(suggestid);

        List<VideoGood> videoGoods = videoGoodMapper.selectByExample(example);
        if (videoGoods.size() == 0)
            return null;

        return videoGoods.get(0);
    }

    @Override
    public void save(VideoGood videoGood) {
        if (videoGood.getId() == null)
            videoGoodMapper.insert(videoGood);
        else
            videoGoodMapper.updateByPrimaryKeySelective(videoGood);
    }

    @Override
    public VideoGood get(Integer id) {
        return videoGoodMapper.selectByPrimaryKey(id);
    }
}
