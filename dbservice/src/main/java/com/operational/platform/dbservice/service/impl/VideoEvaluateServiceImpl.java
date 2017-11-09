package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.VideoEvaluateMapper;
import com.operational.platform.dbservice.model.VideoEvaluate;
import com.operational.platform.dbservice.model.VideoEvaluateExample;
import com.operational.platform.dbservice.service.VideoEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/23.
 */
@Service
public class VideoEvaluateServiceImpl implements VideoEvaluateService {

    @Autowired
    private VideoEvaluateMapper videoEvaluateMapper;

    @Override
    public VideoEvaluate getByUserAndVideo(Integer userid, Integer videoid) {

        VideoEvaluateExample example = new VideoEvaluateExample();
        VideoEvaluateExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        criteria.andVideoidEqualTo(videoid);
        List<VideoEvaluate> list = videoEvaluateMapper.selectByExample(example);
        if (list.size() == 0)
            return new VideoEvaluate();
        else
            return videoEvaluateMapper.selectByExample(example).get(0);
    }

    @Override
    public void save(VideoEvaluate videoEvaluate) {
        if (videoEvaluate.getId() == null) {
            videoEvaluateMapper.insert(videoEvaluate);
        } else {
            videoEvaluateMapper.updateByPrimaryKeySelective(videoEvaluate);
        }
    }
}
