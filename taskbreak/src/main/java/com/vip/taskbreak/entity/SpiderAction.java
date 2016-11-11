package com.vip.taskbreak.entity;

import java.util.Date;

/**
 * Created by lihuajun on 16-7-6.
 */
public class SpiderAction {

    private int id;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpiderAction{id=" + id + ",date=" + date + "}";
    }
}
