package com.operational.platform.operate.task;

import com.operational.platform.dbservice.model.AttackTask;
import com.operational.platform.dbservice.model.AttackerAttr;
import com.operational.platform.dbservice.service.AttackTaskService;
import com.operational.platform.dbservice.service.AttackerAttrService;
import com.operational.platform.operate.bean.IMessage;
import com.operational.platform.operate.component.attack.impl.tx.AddQQUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lihuajun on 16-7-19.
 */
@Component
public class TimerAddQQUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerAddQQUser.class);

    @Autowired
    private AddQQUser addQQUser;
    @Autowired
    private AttackerAttrService attackerAttrService;
    @Autowired
    private AttackTaskService attackTaskService;

    public void execute() {

        List<AttackerAttr> list = attackerAttrService.listByBelong("qq");
        for (AttackerAttr attackerAttr : list) {
            AttackTask task = attackTaskService.getOneByBelongAndStatus("addQQ", Arrays.asList(0));
            if (task == null)
                break;
            try {
                LOGGER.info("添加{}",task.getData());
                IMessage iMessage = new IMessage();
                iMessage.setTarget(task.getData());
                iMessage.setAttackerAttr(attackerAttr);
                addQQUser.exe(iMessage);
                task.setStatus(1);
            } catch (Exception e) {
                task.setStatus(2);
                LOGGER.error("error:", e);
            }
            attackTaskService.save(task);
        }
    }
}
