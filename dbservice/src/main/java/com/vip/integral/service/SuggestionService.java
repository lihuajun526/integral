package com.vip.integral.service;

import com.vip.integral.model.Suggestion;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
public interface SuggestionService {

    int save(Suggestion suggestion);

    Suggestion get(Integer id);

    List<Suggestion> listByCondition(Suggestion suggestion);

}
