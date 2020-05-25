package com.quartz.practice.controller;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.practice.domain.SysJob;
import com.quartz.practice.dto.JobListQueryDTO;
import com.quartz.practice.dto.JobListResultDTO;
import com.quartz.practice.exception.BizException;
import com.quartz.practice.service.SysJobService;

/**
 * @author: dengxin.chen
 * @date: 2020-05-02 16:37
 * @description:
 */
@RestController
@Valid
public class JobDataController {

    @Autowired
    private SysJobService sysJobService;

    /**
     * 任务列表
     * by dengxin.chen
     *
     * @param input 入参
     * @return
     */
    @PostMapping("/jobPageList")
    public JobListResultDTO jobList(@Valid JobListQueryDTO input) {
        return sysJobService.taskList(input);
    }

    /**
     * 删除任务
     * by dengxin.chen
     *
     * @param id 任务id
     * @return
     */
    @PostMapping("/delete")
    public Integer delete(@RequestParam("id") @NotNull(message = "任务id为空") Long id) {
        SysJob job = sysJobService.getById(id);
        if (Objects.isNull(job)) {
            throw new BizException("任务不存在");
        }
        Integer result = sysJobService.deleteTask(id);
        return result;
    }
}
