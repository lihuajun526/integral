package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.VideoSuggestMapper;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.model.VideoSuggestExample;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/7/11.
 */
@Service("videoSuggestService")
public class VideoSuggestServiceImpl implements VideoSuggestService {

    @Autowired
    private VideoSuggestMapper videoSuggestMapper;

    @Override
    public List<VideoSuggest> listAll() {

        VideoSuggestExample example = new VideoSuggestExample();
        example.setOrderByClause("order_no");

        return videoSuggestMapper.selectByExample(example);
    }

    @Override
    public void save(VideoSuggest videoSuggest) {
        videoSuggestMapper.insert(videoSuggest);
    }

    @Override
    public void update(VideoSuggest videoSuggest) {
        videoSuggestMapper.updateByPrimaryKeySelective(videoSuggest);
    }

    @Override
    public List<VideoSuggest> listByStatus(Integer status) {

        VideoSuggestExample example = new VideoSuggestExample();
        VideoSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        example.setOrderByClause("order_no");

        return videoSuggestMapper.selectByExample(example);
    }

    @Override
    public void delBySrc(Integer srcId) {
        VideoSuggestExample example = new VideoSuggestExample();
        VideoSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andSrcIdEqualTo(srcId);
        videoSuggestMapper.deleteByExample(example);
    }

    @Override
    public VideoSuggest getBySrc(Integer srcId) {
        return null;
    }

    @Override
    public List<VideoSuggest> listByChnlAndPage(Integer channelid, Integer pagesize, Integer pageindex, String keyword) {

        Map<String, Object> condition = new HashMap<>();
        condition.put("channelid", channelid);
        condition.put("pagesize", pagesize);
        condition.put("start", (pageindex - 1) * pagesize);
        condition.put("keyword", keyword);

        return videoSuggestMapper.listByChnlAndPage(condition);
    }

    @Override
    public Long countByChnlAndPage(Integer channelid, String keyword) {

        Map<String, Object> condition = new HashMap<>();
        condition.put("channelid", channelid);
        condition.put("keyword", keyword);

        return videoSuggestMapper.countByChnlAndPage(condition);
    }

    @Override
    public VideoSuggest getByUrl(String url) {
        VideoSuggestExample example = new VideoSuggestExample();
        VideoSuggestExample.Criteria criteria = example.createCriteria();
        criteria.andUrlEqualTo(url);

        List<VideoSuggest> list = videoSuggestMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }
}
