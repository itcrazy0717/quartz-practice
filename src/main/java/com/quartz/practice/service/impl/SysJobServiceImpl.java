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
        if (Objects.nonNull(input.getJobName())) {
            condition.like("job_name", input.getJobName());
        }
    }
}
