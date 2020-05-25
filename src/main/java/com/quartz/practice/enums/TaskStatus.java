package com.quartz.practice.enums;

import lombok.Getter;

/**
 * @author: dengxin.chen
 * @date: 2020-05-25 22:06
 * @description:任务状态枚举
 */
@Getter
public enum TaskStatus {

    STOP(0, "停止状态0"),
    STARTUP(1, "启用状态");

    private int value;
    private String desc;

    TaskStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
