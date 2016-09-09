package com.vip.integral.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.model.Goods;
import com.vip.integral.model.IntegralRecord;
import com.vip.integral.model.User;
import com.vip.integral.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {


    /**
     * 分享到朋友圈获得积分奖励
     *
     * @param userid
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping("/share/encourage")
    public String encourageFromShare(Integer userid, Integer count) {


        Result<Boolean> result = new Result<>();

        //result.set(0, userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }


    @RequestMapping("/rec")
    public ModelAndView integralRec() {

        ModelAndView modelAndView = new ModelAndView("home");
        Goods goods = new Goods();
        goods.setStatus(1);
        //modelAndView.addObject("goodsList", goodsService.listByCondition(goods));
        return modelAndView;
    }

    /**
     * 赚积分
     *
     * @return
     */
    @RequestMapping("/earn")
    public ModelAndView integralEarn() {
        ModelAndView modelAndView = new ModelAndView("integral_earn");

        return modelAndView;
    }

}
