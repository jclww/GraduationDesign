package com.lww.design.graduation.entity.po;

public class GoodsImg {
    private Long id;

    private Long spuId;

    private String smallImgUrl;

    private String midImgUrl;

    private String bigImgUrl;

    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl == null ? null : smallImgUrl.trim();
    }

    public String getMidImgUrl() {
        return midImgUrl;
    }

    public void setMidImgUrl(String midImgUrl) {
        this.midImgUrl = midImgUrl == null ? null : midImgUrl.trim();
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl == null ? null : bigImgUrl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}