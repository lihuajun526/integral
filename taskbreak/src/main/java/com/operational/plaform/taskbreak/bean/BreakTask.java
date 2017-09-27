package com.operational.plaform.taskbreak.bean;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class BreakTask {

    private Integer taskid;
    private String description;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;



}
