package com.quartz.practice.config;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.DelegatingJob;
import org.springframework.util.ReflectionUtils;

/**
 * @author: dengxin.chen
 * @date: 2020-05-18 22:24
 * @description:将Spring的对象注入到Quartz Job
 */
public class AdaptableJobFactory implements JobFactory {

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        return newJob(triggerFiredBundle);
    }

    /**
     * 新建job
     * by dengxin.chen
     *
     * @param bundle
     * @return
     * @throws SchedulerException
     */
    private Job newJob(TriggerFiredBundle bundle) throws SchedulerException {
        try {
            // 返回Job实例
            Object jobObject = createJobInstance(bundle);
            return adaptJob(jobObject);
        } catch (Exception ex) {
            throw new SchedulerException("Job instantiation failed", ex);
        }
    }

    /**
     * 创建job实例
     * by dengxin.chen
     *
     * @param bundle
     * @return
     */
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Method getJobDetail = bundle.getClass().getMethod("getJobDetail");
        Object jobDetail = ReflectionUtils.invokeMethod(getJobDetail, bundle);
        Method getJobClass = jobDetail.getClass().getMethod("getJobClass");
        Class jobClass = (Class) ReflectionUtils.invokeMethod(getJobClass, jobDetail);

        return jobClass.newInstance();
    }

    /**
     * 适配job
     * by dengxin.chen
     *
     * @param jobObject
     * @return
     */
    protected Job adaptJob(Object jobObject) {
        if (jobObject instanceof Job) {
            return (Job) jobObject;
        } else if (jobObject instanceof Runnable) {
            return new DelegatingJob((Runnable) jobObject);
        } else {
            throw new IllegalArgumentException("Unable to execute job class [" + jobObject.getClass().getName() +
                                               "]: only [org.quartz.Job] and [java.lang.Runnable] supported.");
        }
    }
}
