package com.operational.platform.backstage.controller;

import com.operational.platform.backstage.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
