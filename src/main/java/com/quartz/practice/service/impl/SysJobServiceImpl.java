package com.quartz.practice.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quartz.practice.dao.SysJobMapper;
import com.quartz.practice.domain.SysJob;
import com.quartz.practice.dto.JobListQueryDTO;
import com.quartz.practice.dto.JobListResultDTO;
import com.quartz.practice.service.SysJobService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author code_template
 * @since 2020-04-09
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    @Override
    public JobListResultDTO jobList(JobListQueryDTO input) {
        JobListResultDTO result = new JobListResultDTO();
        if (Objects.isNull(input)) {
            return result;
        }
        QueryWrapper<SysJob> condition = new QueryWrapper<>();
        buildQueryCondition(condition, input);
        List<SysJob> list = this.baseMapper.selectList(condition);
        result.setCode(0);
        result.setCount(list.size());
        result.setData(list);
        result.setMsg("数据请求成功");
        return result;
    }

    /**
     * 构建请求条件
     * by dengxin.chen
     *
     * @param condition 查询条件
     * @param input     请求参数
     */
    private void buildQueryCondition(QueryWrapper<SysJob> condition, JobListQueryDTO input) {
        if (Objects.nonNull(input.getId())) {
            condition.eq("id", input.getId());
        }
        if (Objects.nonNull(input.getJobName())) {
            condition.eq("jobName", input.getJobName());
        }
        if (Objects.nonNull(input.getJobGroup())) {
            condition.eq("jobGroup", input.getJobGroup());
        }
        if (Objects.nonNull(input.getJobCron())) {
            condition.eq("jobCron", input.getJobCron());
        }
        if (Objects.nonNull(input.getJobClassPath())) {
            condition.eq("jobClassPath", input.getJobClassPath());
        }
        if (Objects.nonNull(input.getJobDescribe())) {
            condition.eq("jobDescribe", input.getJobDescribe());
        }
    }
}
