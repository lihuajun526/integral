package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.*;
import com.operational.platform.dbservice.model.*;
import com.operational.platform.dbservice.service.VideoGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/15.
 */
@Service
public class VideoGoodServiceImpl implements VideoGoodService {

    @Autowired
    private VideoGoodMapper videoGoodMapper;
    @Autowired
    private DbVideoRelationMapper dbVideoRelationMapper;
    @Autowired
    private DbVideoImageMapper dbVideoImageMapper;
    @Autowired
    private DbVideoPersonMapper dbVideoPersonMapper;
    @Autowired
    private DbShortCommentMapper dbShortCommentMapper;
    @Autowired
    private DbVideoTagMapper dbVideoTagMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public void save(VideoGood videoGood) {
        if (videoGood.getId() == null)
            videoGoodMapper.insert(videoGood);
        else
            videoGoodMapper.updateByPrimaryKeySelective(videoGood);

        DbVideoTagExample videoTagExample = new DbVideoTagExample();
        DbVideoTagExample.Criteria videoTagCriteria = videoTagExample.createCriteria();
        videoTagCriteria.andVideoidEqualTo(videoGood.getId());
        dbVideoTagMapper.deleteByExample(videoTagExample);
        for (DbVideoTag videoTag : videoGood.getVideoTags()) {
            videoTag.setVideoid(videoGood.getId());
            dbVideoTagMapper.insert(videoTag);
        }

        DbVideoRelationExample videoRelationExample = new DbVideoRelationExample();
        DbVideoRelationExample.Criteria videoRelationCriteria = videoRelationExample.createCriteria();
        videoRelationCriteria.andVideoidEqualTo(videoGood.getId());
        dbVideoRelationMapper.deleteByExample(videoRelationExample);
        for (DbVideoRelation videoRelation : videoGood.getVideoRelations()) {
            videoRelation.setVideoid(videoGood.getId());
            dbVideoRelationMapper.insert(videoRelation);
        }

        DbVideoPersonExample videoPersonExample = new DbVideoPersonExample();
        DbVideoPersonExample.Criteria videoPersonCriteria = videoPersonExample.createCriteria();
        videoPersonCriteria.andVideoidEqualTo(videoGood.getId());
        dbVideoPersonMapper.deleteByExample(videoPersonExample);
        for (DbVideoPerson videoPerson : videoGood.getVideoPersons()) {
            videoPerson.setVideoid(videoGood.getId());
            dbVideoPersonMapper.insert(videoPerson);
        }

        DbVideoImageExample videoImageExample = new DbVideoImageExample();
        DbVideoImageExample.Criteria videoImageCriteria = videoImageExample.createCriteria();
        videoImageCriteria.andVideoidEqualTo(videoGood.getId());
        dbVideoImageMapper.deleteByExample(videoImageExample);
        for (DbVideoImage videoImage : videoGood.getVideoImages()) {
            videoImage.setVideoid(videoGood.getId());
            dbVideoImageMapper.insert(videoImage);
        }

        DbShortCommentExample shortCommentExample = new DbShortCommentExample();
        DbShortCommentExample.Criteria shortCommentCriteria = shortCommentExample.createCriteria();
        shortCommentCriteria.andVideoidEqualTo(videoGood.getId());
        dbShortCommentMapper.deleteByExample(shortCommentExample);
        for (DbShortComment shortComment : videoGood.getShortComments()) {
            shortComment.setVideoid(videoGood.getId());
            dbShortCommentMapper.insert(shortComment);
        }

    }

    @Override
    public VideoGood get(Integer id) {

        VideoGood videoGood = videoGoodMapper.selectByPrimaryKey(id);

        DbVideoRelationExample videoRelationExample = new DbVideoRelationExample();
        DbVideoRelationExample.Criteria videoRelationCriteria = videoRelationExample.createCriteria();
        videoRelationCriteria.andVideoidEqualTo(id);
        List<DbVideoRelation> videoRelations = dbVideoRelationMapper.selectByExample(videoRelationExample);

        DbShortCommentExample shortCommentExample = new DbShortCommentExample();
        DbShortCommentExample.Criteria shortCommentCriteria = shortCommentExample.createCriteria();
        shortCommentCriteria.andVideoidEqualTo(id);
        List<DbShortComment> shortComments = dbShortCommentMapper.selectByExample(shortCommentExample);

        DbVideoImageExample videoImageExample = new DbVideoImageExample();
        DbVideoImageExample.Criteria videoImageCriteria = videoImageExample.createCriteria();
        videoImageCriteria.andVideoidEqualTo(id);
        List<DbVideoImage> videoImages = dbVideoImageMapper.selectByExample(videoImageExample);

        DbVideoTagExample videoTagExample = new DbVideoTagExample();
        DbVideoTagExample.Criteria videoTagCriteria = videoTagExample.createCriteria();
        videoTagCriteria.andVideoidEqualTo(id);
        List<DbVideoTag> videoTags = dbVideoTagMapper.selectByExample(videoTagExample);

        DbVideoPersonExample videoPersonExample = new DbVideoPersonExample();
        DbVideoPersonExample.Criteria videoPersonCriteria = videoPersonExample.createCriteria();
        videoPersonCriteria.andVideoidEqualTo(id);
        List<DbVideoPerson> videoPersons = dbVideoPersonMapper.selectByExample(videoPersonExample);

        videoGood.setVideoImages(videoImages);
        videoGood.setVideoPersons(videoPersons);
        videoGood.setVideoRelations(videoRelations);
        videoGood.setVideoTags(videoTags);
        videoGood.setShortComments(shortComments);

        return videoGood;
    }

    @Override
    public List<VideoGood> getLatest() {
        return videoGoodMapper.getLatest();
    }
}
