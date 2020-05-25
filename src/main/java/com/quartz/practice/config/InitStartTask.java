package com.quartz.practice.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quartz.practice.domain.SysJob;
import com.quartz.practice.enums.Delete;
import com.quartz.practice.enums.TaskStatus;
import com.quartz.practice.service.SysJobService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: dengxin.chen
 * @date: 2020-05-25 22:02
 * @description:初始化任务
 */
@Component
@Slf4j
public class InitStartTask implements CommandLineRunner {

    @Autowired
    private SysJobService sysJobService;

    @Autowired
    private TaskJobFactory taskJobFactory;

    @Override
    public void run(String... args) throws Exception {

        SysJob conditon = new SysJob();
        conditon.setIsDelete(Delete.NO_DELETE.getValue());
        conditon.setJobStatus(TaskStatus.STARTUP.getValue());
        List<SysJob> taskList = sysJobService.queryTaskList(conditon);
        if (CollectionUtils.isEmpty(taskList)) {
            log.info("系统启动，任务列表为空，没有需要执行的任务......");
        }
        // 获取任务调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.setJobFactory(taskJobFactory);
        scheduler.start();

        for (SysJob task : taskList) {
            String jobName = task.getJobName();
            String jobGroup = task.getJobGroup();
            // 构建job信息  
            JobDetail jobDetail = JobBuilder.newJob(getClass(task.getJobClassPath()).getClass()).withIdentity(jobName, jobGroup).build();
            if (StringUtils.isNotEmpty(task.getJobDataMap())) {
                Gson gson = new Gson();
                Map<String, Object> dataMap = gson.fromJson(task.getJobDataMap(), new TypeToken<Map<String, Object>>() {}.getType());
                for (Map.Entry<String, Object> item : dataMap.entrySet()) {
                    jobDetail.getJobDataMap().put(item.getKey(), item.getValue());
                }
            }
            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getJobCron());
            // 根据cron表达式构建trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(cronScheduleBuilder).startNow().build();

            // 如果任务不存在，则添加
            if (!scheduler.checkExists(jobDetail.getKey())) {
                try {
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    log.error("定时任务创建失败", e);
                    throw new RuntimeException("定时任务创建失败");
                }
            }
        }

    }

    /**
     * 获取job实例
     * by dengxin.chen
     *
     * @param classname
     * @return
     * @throws Exception
     */
    private static Job getClass(String classname) throws Exception {
        Class<?> c = Class.forName(classname);
        return (Job) c.newInstance();
    }
}
