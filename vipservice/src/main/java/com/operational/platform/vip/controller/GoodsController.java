package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.constant.ExceptionTypeEnum;
import com.operational.platform.dbservice.exception.OrderException;
import com.operational.platform.dbservice.model.Goods;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.VipAccount;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.service.GoodsService;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.service.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private AppConfig appConfig;

    /**
     * 查找所有上架商品
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String list() {

        Result<List<Goods>> result = new Result<>();

        //获取全部商品
        Goods goods = new Goods();
        goods.setStatus(1);

        List<Goods> list = goodsService.listByCondition(goods);
        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/get")
    public ModelAndView getGoods(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("goods");
        modelAndView.addObject("goods", goodsService.selectByPrimaryKey(goods.getId()));
        modelAndView.addObject("effectiveTime", configService.getString("goods.effective.time"));
        return modelAndView;
    }

    @RequestMapping("/fix")
    public ModelAndView fix(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("pay_fix");
        modelAndView.addObject("goods", goodsService.selectByPrimaryKey(goods.getId()));
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order(Integer goodsid, HttpServletRequest request) {

        String openid = (String) request.getSession().getAttribute("openid");

        ModelAndView modelAndView = new ModelAndView("order_success");

        User user = userService.getByOpenid(openid);
        Goods goods = goodsService.selectByPrimaryKey(goodsid);

        VipAccount vipAccount = null;
        try {
            vipAccount = goodsService.order(user, goods);
        } catch (OrderException e) {
            logger.error("错误编码：{}，错误描述：{}", e.getCode(), e.getDescription());
            if (e.getCode().equals(ExceptionTypeEnum.NOT_IN_SELL_TIME_ERROR.code)) {
                modelAndView.addObject("errMsg", "对不起，请您在早上" + configService.getString("goods.begin.sell.time") + "点到次日凌晨" + configService.getString("goods.end.sell.time") + "点之间购买会员");
            } else if (e.getCode().equals(ExceptionTypeEnum.STOCKS_LOW_ERROR.code)) {
                modelAndView.addObject("errMsg", "对不起，该商品已售完");
            } else if (e.getCode().equals(ExceptionTypeEnum.BALANCE_LOW_ERROR.code)) {
                modelAndView.addObject("errMsg", "对不起，您的余额不足");
            } else if (e.getCode().equals(ExceptionTypeEnum.TODAY_HAS_BUY_ERROR.code)) {
                modelAndView.addObject("errMsg", "对不起，您今天已买过该商品，一天只能买一次哦");
            } else if (e.getCode().equals(ExceptionTypeEnum.STOCK_NOT_SYN_ERROR.code)) {
                modelAndView.addObject("errMsg", "对不起，系统异常，工程师正在玩命解决");
            }
            modelAndView.addObject("errCode", e.getCode());
            modelAndView.setViewName("order_fail");
            return modelAndView;
        }

        modelAndView.addObject("vipAccount", vipAccount);
        return modelAndView;
    }
}
