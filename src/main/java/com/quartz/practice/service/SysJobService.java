package com.quartz.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quartz.practice.domain.SysJob;
import com.quartz.practice.dto.JobListQueryDTO;
import com.quartz.practice.dto.JobListResultDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author code_template
 * @since 2020-04-09
 */
public interface SysJobService extends IService<SysJob> {

    /**
     * 返回任务列表
     * by dengxin.chen
     *
     * @param input 入参
     * @return
     */
    JobListResultDTO jobList(JobListQueryDTO input);

}
