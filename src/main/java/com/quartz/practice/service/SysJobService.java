package com.quartz.practice.service;

import java.util.List;

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
    JobListResultDTO taskList(JobListQueryDTO input);

    /**
     * 删除任务
     * by dengxin.chen
     *
     * @param id
     * @return
     */
    Integer deleteTask(Long id);

    /**
     * 查询任务列表
     * by dengxin.chen
     *
     * @param condition 查询条件
     * @return
     */
    List<SysJob> queryTaskList(SysJob condition);

}
