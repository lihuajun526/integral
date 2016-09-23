package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.SuggestionMapper;
import com.vip.dbservice.model.Suggestion;
import com.vip.dbservice.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("suggestionService")
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionMapper suggestionMapper;

    @Override
    public int save(Suggestion suggestion) {
        return suggestionMapper.insert(suggestion);
    }

    @Override
    public Suggestion get(Integer id) {
        return null;
    }

    @Override
    public List<Suggestion> listByCondition(Suggestion suggestion) {
        return null;
    }
}
