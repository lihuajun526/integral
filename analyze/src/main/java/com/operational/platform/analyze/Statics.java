package com.operational.platform.analyze;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.analyze.bean.SpringContext;
import com.operational.platform.analyze.component.smt.bean.Investor;
import com.operational.platform.analyze.component.smt.bean.OrgContact;
import com.operational.platform.analyze.component.smt.bean.Organise;
import com.operational.platform.dbservice.dao.AttackPageMapper;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackPageExample;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/17.
 */
public class Statics {

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");
        AttackPageMapper attackPageMapper = (AttackPageMapper) SpringContext.getContext().getBean("attackPageMapper");
        AttackPageExample example = new AttackPageExample();
        AttackPageExample.Criteria criteria = example.createCriteria();
        criteria.andBelongEqualTo("smt");
        criteria.andPointidEqualTo(40);

        List<AttackPage> list = attackPageMapper.selectByExampleWithBLOBs(example);

        int i = 0, j = 0, m = 0, n = 0;
        for (AttackPage attackPage : list) {
            Organise organise = JSON.parseObject(attackPage.getAttr(), Organise.class);
            if (organise.getOrgContactList() == null || organise.getOrgContactList().size() == 0)
                continue;

            List<OrgContact> l = organise.getOrgContactList();
            OrgContact orgContact = l.get(0);

            boolean hasTel = false;
            boolean hasEmail = false;
            for (OrgContact o : l) {
                if (!StringUtils.isEmpty(o.getEventContactTel())) {
                    hasTel = true;
                }
                if (!StringUtils.isEmpty(o.getEventContactEmail())) {
                    hasEmail = true;
                }
            }

            if (hasTel && hasEmail) {
                i++;
            }
            if (!hasTel && !hasEmail) {
                j++;
            }
            if (!hasTel && hasEmail) {
                m++;
            }
            if (hasTel && !hasEmail) {
                n++;
            }
        }

        System.out.println(i);
        System.out.println(j);
        System.out.println(m);
        System.out.println(n);

    }

}
