package com.quartz.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-02 16:45
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobListQueryDTO {

    /**
     * 主键id
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
     * 任务功能描述
     */
    private String jobDescribe;
}
