package com.lww.design.graduation.common.enums;

/**
 * 订单状态
 */
public enum  OrderEnums {
    UNPAID(1, "待付款"),
    UNDELIVER(2, "待发货"),
    UNRECEIVE(4, "待收货"),
    UNASSESS(8, "待评价"),
    CLOSED(16, "订单关闭");
    private Integer code;

    private String desc;

    OrderEnums(Integer code, String desc) {
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
