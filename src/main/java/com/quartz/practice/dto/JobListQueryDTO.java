package com.quartz.practice.dto;

import javax.validation.constraints.NotNull;

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
     * 任务名称
     */
    private String jobName;

    /**
     * 页码
     */
    @NotNull(message = "页码为空")
    private Integer page;

    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数为空")
    private Integer limit;
}
