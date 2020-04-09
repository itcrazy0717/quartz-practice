package com.quartz.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: dengxin.chen
 * @date: 2020-04-07 22:42
 * @description:
 */
@Slf4j
@Controller
public class JobController {

    /**
     * 任务列表
     *
     * @return
     */
    @RequestMapping("/jobList")
    public String taskList() {
        return "job/jobListPage";
    }

}