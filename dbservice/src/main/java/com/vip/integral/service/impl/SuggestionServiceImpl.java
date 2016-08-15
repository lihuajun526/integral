package com.vip.integral.service.impl;

import com.vip.integral.model.Suggestion;
import com.vip.integral.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("suggestionService")
public class SuggestionServiceImpl implements SuggestionService {
    @Override
    public int save(Suggestion suggestion) {
        return 0;
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
