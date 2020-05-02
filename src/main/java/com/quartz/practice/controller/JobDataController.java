package com.quartz.practice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.practice.dto.JobListQueryDTO;
import com.quartz.practice.dto.JobListResultDTO;
import com.quartz.practice.service.SysJobService;

/**
 * @author: dengxin.chen
 * @date: 2020-05-02 16:37
 * @description:
 */
@RestController
public class JobDataController {

    @Autowired
    private SysJobService sysJobService;

    @GetMapping("/jobPageList")
    public JobListResultDTO jobList(@RequestBody @Valid JobListQueryDTO input) {
        return sysJobService.jobList(input);
    }
}
