package com.vip.integral.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: Zhou Xuanang
 * @Date: 14:04 16/6/24.
 */
@Component
public class JsonAnalyzerFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public JsonAnalyzer getJsonAnalyzer(String classPath) {
        return (JsonAnalyzer) applicationContext.getBean(classPath);
    }
}
