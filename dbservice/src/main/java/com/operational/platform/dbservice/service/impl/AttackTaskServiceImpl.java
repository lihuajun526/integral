package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.AttackTaskMapper;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackTask;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.AttackTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/11/6.
 */
@Service("attackTaskService")
public class AttackTaskServiceImpl implements AttackTaskService {

    @Autowired
    private AttackTaskMapper attackTaskMapper;
    @Autowired
    private AttackPageService attackPageService;

    @Override
    public AttackTask getOneByBelongAndStatus(String belong, List<Integer> status) {
        List<AttackTask> list = this.listByBelongAndStatus(belong, status, 1);
        if (list.size() > 0)
            return list.get(0);
        else{
            List<AttackPage> l =  attackPageService.listByPointsAndLimit(Arrays.asList(33),1);
            AttackPage attackPage = l.get(0);
            AttackTask attackTask = new AttackTask();
            attackTask.setBelong("addQQ");
            attackTask.setStatus(0);
            attackTask.setData(attackPage.getLink().trim());

            attackTaskMapper.insert(attackTask);
            return attackTask;
        }
    }

    @Override
    public List<AttackTask> listByBelongAndStatus(String belong, List<Integer> status, Integer limit) {

        Map<String, Object> map = new HashMap<>();
        map.put("belong", belong);
        map.put("status", status);
        map.put("limit", limit);

        return attackTaskMapper.listByBelongAndStatus(map);
    }

    @Override
    public void save(AttackTask attackTask) {
        if (attackTask.getId() == null) {
            attackTaskMapper.insert(attackTask);
        } else {
            attackTaskMapper.updateByPrimaryKeySelective(attackTask);
        }
    }
}
