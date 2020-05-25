package com.quartz.practice.enums;

import lombok.Getter;

/**
 * @author: dengxin.chen
 * @date: 2020-05-18 22:13
 * @description:
 */
@Getter
public enum Delete {

    DELETE(1, "删除"),
    NO_DELETE(0, "不删除");

    private int value;
    private String desc;

    Delete(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
