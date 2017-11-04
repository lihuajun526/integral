package com.operational.platform.operate.bean;

import com.operational.platform.dbservice.model.AttackerAttr;

/**
 * Created by lihuajun on 2017/11/3.
 */
public class IMessage {
    private String target;
    private AttackerAttr attackerAttr;;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public AttackerAttr getAttackerAttr() {
        return attackerAttr;
    }

    public void setAttackerAttr(AttackerAttr attackerAttr) {
        this.attackerAttr = attackerAttr;
    }
}
