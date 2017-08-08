package com.operational.platform.vip.controller;

import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.dbservice.model.Suggestion;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.SuggestionService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/suggest")
public class SuggestionController extends BaseController {

    @Autowired
    private SuggestionService suggestionService;

    @ResponseBody
    @RequestMapping("/save/v_login")
    public String save(Suggestion suggestion, String vipAccessionToken) {

        Result<Boolean> result = new Result<>();

        if (StringUtils.isEmpty(suggestion.getContent())) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, ExceptionCode.PARAM_IS_NULL_ERROR.description);
            return result.toString();
        }

        User loginUser = Constant.SessionMap.get(vipAccessionToken);
        suggestion.setUserid(loginUser.getId());
        suggestion.setType(1);
        suggestion.setStatus(1);

        suggestionService.save(suggestion);

        result.set("提交成功", true);

        return result.toString();
    }


}
