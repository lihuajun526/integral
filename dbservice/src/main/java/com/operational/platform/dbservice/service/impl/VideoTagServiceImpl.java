package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.VideoTagMapper;
import com.operational.platform.dbservice.model.VideoTag;
import com.operational.platform.dbservice.model.VideoTagExample;
import com.operational.platform.dbservice.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/23.
 */
@Service
public class VideoTagServiceImpl implements VideoTagService {

    @Autowired
    private VideoTagMapper videoTagMapper;

    @Override
    public List<VideoTag> listByVideoAndUser(Integer videoid, Integer userid) {
        VideoTagExample example = new VideoTagExample();
        VideoTagExample.Criteria criteria = example.createCriteria();
        criteria.andVideoidEqualTo(videoid);
        criteria.andUseridEqualTo(userid);

        return videoTagMapper.selectByExample(example);
    }

    @Override
    public void save(VideoTag videoTag) {
        videoTagMapper.insert(videoTag);
    }

    @Override
    public List<VideoTag> listByVideo(Integer videoid) {
        VideoTagExample example = new VideoTagExample();
        VideoTagExample.Criteria criteria = example.createCriteria();
        criteria.andVideoidEqualTo(videoid);

        return videoTagMapper.selectByExample(example);
    }

    @Override
    public List<VideoTag> listByVideoAndTag(Integer videoid, String tag) {
        VideoTagExample example = new VideoTagExample();
        VideoTagExample.Criteria criteria = example.createCriteria();
        criteria.andVideoidEqualTo(videoid);
        criteria.andTagEqualTo(tag);

        return videoTagMapper.selectByExample(example);
    }
}
