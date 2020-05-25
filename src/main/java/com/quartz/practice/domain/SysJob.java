package com.quartz.practice.domain;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author code_template
 * @date 2020-04-09
 */
@Data
public class SysJob {

    /**
     * ID
     */
    private Long id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 时间表达式
     */
    private String jobCron;

    /**
     * 类路径,全类型
     */
    private String jobClassPath;

    /**
     * 传递map参数
     */
    private String jobDataMap;

    /**
     * 状态: 1启用 0停用
     */
    private Integer jobStatus;

    /**
     * 任务功能描述
     */
    private String jobDescribe;

    /**
     * 是否删除：0-不删除 1-删除
     */
    private Integer isDelete;

}
