package com.lww.design.graduation.entity.po;

import java.math.BigDecimal;

public class GoodsSpu {
    private Long id;

    private String name;

    private Integer categoryId;

    private String details;

    private BigDecimal priceBottom;

    private BigDecimal priceTop;

    private Integer commentCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public BigDecimal getPriceBottom() {
        return priceBottom;
    }

    public void setPriceBottom(BigDecimal priceBottom) {
        this.priceBottom = priceBottom;
    }

    public BigDecimal getPriceTop() {
        return priceTop;
    }

    public void setPriceTop(BigDecimal priceTop) {
        this.priceTop = priceTop;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}