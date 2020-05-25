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
import com.quartz.practice.enums.Delete;
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
    public JobListResultDTO taskList(JobListQueryDTO input) {
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

    @Override
    public Integer deleteTask(Long id) {
        if (Objects.isNull(id)) {
            return 0;
        }
        SysJob condition = new SysJob();
        condition.setId(id);
        condition.setIsDelete(Delete.DELETE.getValue());
        return this.baseMapper.updateById(condition);
    }

    @Override
    public List<SysJob> queryTaskList(SysJob condition) {
        return this.baseMapper.selectList(new QueryWrapper<>(condition));
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
        condition.eq("is_delete", Delete.NO_DELETE.getValue());
    }
}
