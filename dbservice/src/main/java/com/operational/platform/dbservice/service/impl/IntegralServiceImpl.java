package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.IntegralRecordMapper;
import com.operational.platform.dbservice.dao.UserMapper;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.service.IntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("integralService")
public class IntegralServiceImpl implements IntegralService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegralServiceImpl.class);

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
        integralRecord.setDescription(desc);
        integralRecord.setIntegral(count);
        integralRecord.setType(type);
        //todo 积分记录表添加记录
        user.setIntegral(user.getIntegral() + count);
        //todo 用户表更新积分值
        return true;
    }

    @Override
    public Boolean encourageFromPopularize(Integer userid, Integer friendid, Integer count) {

        User sourceUser = userMapper.selectByPrimaryKey(userid);
        User updateUser = new User();
        updateUser.setId(sourceUser.getId());

        Long interval = System.currentTimeMillis() - sourceUser.getPopulateTime().getTime();
        //N小时外推广的好友无效
        if ((interval / (1000 * 60 * 60)) > configService.getInt("spread.effective")) {
            if (StringUtils.isEmpty(sourceUser.getSpreadRecord())) {
                sourceUser.setSpreadRecord(friendid + ":" + 0);
            } else {
                sourceUser.setSpreadRecord("#" + friendid + ":" + 0);
            }
            updateUser.setSpreadRecord(sourceUser.getSpreadRecord());
            userMapper.updateByPrimaryKeySelective(updateUser);
            LOGGER.info("推广的好友不在有效期内");
            return false;
        }

        int spreadCount = 0;
        if (StringUtils.isEmpty(sourceUser.getSpreadRecord())) {
            updateUser.setSpreadRecord(friendid + ":" + 1);
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
                updateUser.setSpreadRecord(sourceUser.getSpreadRecord() + "#" + friendid + ":" + 0);
                userMapper.updateByPrimaryKeySelective(updateUser);
                LOGGER.warn("超出最大推广人数");
                return false;
            }
            updateUser.setSpreadRecord(sourceUser.getSpreadRecord() + "#" + friendid + ":" + 1);
        }
        updateUser.setIntegral(sourceUser.getIntegral() + count);
        userMapper.updateByPrimaryKeySelective(updateUser);

        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setIntegral(count);
        integralRecord.setDescription("用户[" + friendid + "]通过扫描你的二维码关注了公众号");
        integralRecord.setTag("推广");
        integralRecord.setUserid(userid);
        integralRecord.setType(13);
        //添加积分记录
        integralRecordMapper.insert(integralRecord);
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
        integralRecord.setDescription("关注公众号");
        integralRecord.setIntegral(count);
        integralRecord.setType(10);
        integralRecordMapper.insert(integralRecord);
        return true;
    }


}
