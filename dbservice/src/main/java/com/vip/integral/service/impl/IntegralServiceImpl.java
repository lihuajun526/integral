package com.vip.integral.service.impl;

import com.vip.integral.dao.IntegralRecordMapper;
import com.vip.integral.dao.UserMapper;
import com.vip.integral.model.IntegralRecord;
import com.vip.integral.model.User;
import com.vip.integral.service.ConfigService;
import com.vip.integral.service.IntegralRecordService;
import com.vip.integral.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("integralService")
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private ConfigService configService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public Boolean encourageFromShare(Integer userid, Integer count, Integer type, String desc) {

        User user = userMapper.selectByPrimaryKey(userid);

        //todo 只有第一次分享到微信朋友圈或qq空间才奖励
        IntegralRecord integralRecord = null;
        if (integralRecord != null) {
            return false;
        }
        integralRecord = new IntegralRecord();
        integralRecord.setUserid(userid);
        String str = "";
        if (type == 12) {
            str = "到微信朋友圈";
        } else if (type == 13) {
            str = "到qq空间";
        }
        integralRecord.setTag("分享" + str);
        integralRecord.setDes(desc);
        integralRecord.setCount(count);
        integralRecord.setType(type);
        //todo 积分记录表添加记录
        user.setIntegral(user.getIntegral() + count);
        //todo 用户表更新积分值
        return true;
    }

    @Override
    public Boolean encourageFromPopularize(Integer userid, Integer friendid, Integer count) {

        User sourceUser = null;
        Long interval = System.currentTimeMillis() - sourceUser.getPopulateTime().getTime();
        //24小时内推广的好友有效
        if ((interval / (1000 * 60 * 60)) > 24) {
            if (StringUtils.isEmpty(sourceUser.getSpreadRecord())) {
                sourceUser.setSpreadRecord(friendid + ":" + 0);
            } else {
                sourceUser.setSpreadRecord("#" + friendid + ":" + 0);
            }
            //todo 更新用户推广记录
            return false;
        }

        //todo 上层要判断该用户是否是新关注用户，只有新关注用户才送分给推广者
        int spreadCount = 0;
        if (StringUtils.isEmpty(sourceUser.getSpreadRecord())) {
            sourceUser.setSpreadRecord(friendid + ":" + 1);
        } else {
            //风控值，最多奖励积分数
            String[] spreadRecord = sourceUser.getSpreadRecord().split("#");
            for (String str : spreadRecord) {
                String[] kv = str.split(":");
                if (kv[1].equals("1")) {
                    spreadCount++;
                }
            }
            if (spreadCount >= configService.getInt("max.spread.count")) {
                sourceUser.setSpreadRecord("#" + friendid + ":" + 0);
                //todo 更新用户
                return false;
            }
            sourceUser.setSpreadRecord("#" + friendid + ":" + 1);
        }
        sourceUser.setIntegral(sourceUser.getIntegral() + count);
        //todo 更新用户推广记录及积分
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setCount(count);
        integralRecord.setDes("用户[" + friendid + "]通过扫描你的二维码关注了公众号");
        integralRecord.setTag("推广");
        integralRecord.setUserid(userid);
        integralRecord.setType(13);
        //todo 添加积分记录
        return true;
    }

    @Override
    public Boolean encourageFromFocus(Integer userid, Integer count) {

        //只有第一次关注公众号才奖励
        IntegralRecord condition = new IntegralRecord();
        condition.setUserid(userid);
        condition.setType(10);
        if (integralRecordMapper.selectByCondition(condition) != null) {
            return false;
        }
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setUserid(userid);
        integralRecord.setTag("关注");
        integralRecord.setDes("关注公众号");
        integralRecord.setCount(count);
        integralRecord.setType(10);
        integralRecordMapper.insert(integralRecord);
        return true;
    }


}
