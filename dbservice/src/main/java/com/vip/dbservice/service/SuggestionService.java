package com.vip.dbservice.service;

import com.vip.dbservice.model.Suggestion;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
public interface SuggestionService {

    int save(Suggestion suggestion);

    Suggestion get(Integer id);

    List<Suggestion> listByCondition(Suggestion suggestion);

}
