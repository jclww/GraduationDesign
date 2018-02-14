package com.lww.design.graduation.entity.dao.base;

import java.io.Serializable;

public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sortKey;

    private String sortOrder;

    public Sort() {
        super();
    }

    public Sort(String sortKey, String sortOrder) {
        super();
        this.sortKey = sortKey;
        this.sortOrder = sortOrder;
    }

    public Sort(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public static Sort valueOf(String sortKey) {
        return new Sort(sortKey);
    }

    public static Sort valueOf(String sortKey, String sortDir) {
        return new Sort(sortKey, sortDir);
    }
}

