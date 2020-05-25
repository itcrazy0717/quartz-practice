package com.quartz.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quartz.practice.domain.SysJob;
import com.quartz.practice.service.SysJobService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: dengxin.chen
 * @date: 2020-04-07 22:42
 * @description: 前端页面控制器
 */
@Slf4j
@Controller
public class JobPageController {

    @Autowired
    private SysJobService sysJobService;

    /**
     * 任务列表
     * by dengxin.chen
     *
     * @return
     */
    @RequestMapping("/jobList")
    public String taskList() {
        return "job/jobListPage";
    }

    /**
     * 任务详情
     * by dengxin.chen
     *
     * @return
     */
    @RequestMapping("/taskDetail")
    public String taskDetail(Long id, Model model) {
        SysJob job = sysJobService.getById(id);
        model.addAttribute("job", job);
        return "job/jobDetail";
    }

    /**
     * 任务修改界面
     * by dengxin.chen
     *
     * @return
     */
    @RequestMapping("/taskUpdate")
    public String taskUpdate(Long id, Model model) {
        SysJob job = sysJobService.getById(id);
        model.addAttribute("job", job);
        return "job/jobUpdate";
    }
}
