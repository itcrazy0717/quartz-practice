package com.quartz.practice.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author: dengxin.chen
 * @date: 2020-05-18 22:37
 * @description:将spring对象注入到Quartz
 */
@Component
public class TaskJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类方法进行创建对象
        Object instance = super.createJobInstance(bundle);
        autowireCapableBeanFactory.autowireBean(instance);
        return instance;
    }
}
