package com.vip.integral.service.impl;

import com.vip.integral.dao.SuggestionMapper;
import com.vip.integral.model.Suggestion;
import com.vip.integral.service.SuggestionService;
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
