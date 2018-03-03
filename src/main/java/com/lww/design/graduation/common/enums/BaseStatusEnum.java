package com.lww.design.graduation.common.enums;

/**
 * @author liweiwei
 * @date   2018/3/3
 * @description 表记录status 枚举 查询时只能查USING
 *
 */
public enum BaseStatusEnum {
    USING(1, "可用"),
    DELETED(0, "已经删除");

    private Integer code;

    private String desc;

    BaseStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
