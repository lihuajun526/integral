package com.unionpay.spiderframework.proxyservice.monitor;

import com.unionpay.spiderframework.proxyservice.constant.Status;
import com.unionpay.spiderframework.proxyservice.loader.ProxyLoader;
import com.unionpay.spiderframework.proxyservice.util.SpringContext;
import com.unionpay.spiderframework.service.service.DBStatusService;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author: Zhou Xuanang
 * @Date: 17:30 2016/11/11.
 */
public class ProxyReloadMonitor {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProxyMonitor.class);

    public void execute() {
        ApplicationContext ctx = SpringContext.getContext();
        DBStatusService dbStatusService = (DBStatusService) ctx.getBean("dBStatusService");
        try {
            if (!ProxyLoader.checkDBState())
                return;
            if (Status.MONITOR_STATE == 1) {
                return;
            }
            LOGGER.info("重新加载代理...");
            ProxyLoader.load();
            LOGGER.info("修改数据库状态");
            dbStatusService.updateRuleStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
