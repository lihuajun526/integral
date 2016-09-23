package com.vip.dbservice.service;

/**
 * Created by lihuajun on 2016/8/15.
 */
public interface IntegralService {

    /**
     * 奖励来自分享到朋友圈
     *
     * @param userid
     * @param count
     * @return
     */
    Boolean encourageFromShare(Integer userid, Integer count, Integer type, String desc);

    /**
     * 奖励来自推广
     *
     * @param userid
     * @param friendid
     * @param count
     * @return
     */
    Boolean encourageFromPopularize(Integer userid, Integer friendid, Integer count);

    /**
     * 奖励来自关注公众号
     *
     * @param userid
     * @param count
     * @return
     */
    Boolean encourageFromFocus(Integer userid, Integer count);

}
