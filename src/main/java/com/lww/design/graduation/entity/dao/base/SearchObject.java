package com.lww.design.graduation.entity.dao.base;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SearchObject implements Serializable {

    private static final long serialVersionUID = -1L;

    private List<Sort> sorts = new LinkedList<Sort>();

    private Integer pageSize;

    private Integer pageNo;

    private Integer offSet;

    public SearchObject() {
        super();
    }

    public SearchObject(SearchObject so) {
        super();
        this.sorts = so.sorts;
        this.pageSize = so.pageSize;
        this.pageNo = so.pageNo;
    }

    public void addSort(Sort sort) {
        sorts.add(sort);
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getOffSet() {
        if (pageNo == null || pageSize == null) {
            return null;
        }
        return (pageNo - 1) * pageSize;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}