package com.operational.platform.backstage.controller;

import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.common.util.Config;
import com.operational.platform.dbservice.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {


    /**
     * 登录
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String name,String password, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            result.setMessage("账号和密码不能为空");
            return result.toString();
        }
        if (!name.equals(Config.get("back.admin.name"))) {
            result.setMessage("对不起，您不是管理员无法登录");
            return result.toString();
        }
        if (!password.equals(Config.get("back.admin.password"))) {
            result.setMessage("密码错误");
            return result.toString();
        }

        User user = new User();
        user.setNickname(name);

        session.setAttribute("loginUser", user);

        result.setData(true);
        return result.toString();
    }


}
