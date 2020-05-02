package com.quartz.practice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-02 16:39
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobListResultDTO {

    private Integer count;
    private Integer code;
    private String msg;
    private List data;
}
