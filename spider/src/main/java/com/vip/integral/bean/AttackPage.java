package com.vip.integral.bean;

import com.vip.integral.model.PageLink;

/**
 * Created by lihuajun on 16-7-16.
 * 攻击目标
 */
public abstract class AttackPage {

    private PageLink pageLink;

    public PageLink getPageLink() {
        return pageLink;
    }

    public void setPageLink(PageLink pageLink) {
        this.pageLink = pageLink;
    }
}
