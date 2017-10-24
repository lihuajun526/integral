package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.UserMapper;
import com.operational.platform.dbservice.dao.VideoCommentMapper;
import com.operational.platform.dbservice.model.VideoComment;
import com.operational.platform.dbservice.model.VideoCommentExample;
import com.operational.platform.dbservice.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/23.
 */
@Service
public class VideoCommentServiceImpl implements VideoCommentService {

    @Autowired
    private VideoCommentMapper videoCommentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<VideoComment> listByVideo(Integer videoid) {

        VideoCommentExample example = new VideoCommentExample();
        VideoCommentExample.Criteria criteria = example.createCriteria();
        criteria.andVideoidEqualTo(videoid);

        List<VideoComment> list = videoCommentMapper.selectByExample(example);
        for (VideoComment videoComment : list) {
            videoComment.setNick(userMapper.selectByPrimaryKey(videoComment.getUserid()).getNickname());
        }
        return list;
    }

    @Override
    public void save(VideoComment videoComment) {
        videoCommentMapper.insert(videoComment);
    }
}
