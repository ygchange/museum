package com.museum.common.pojo;

import java.io.Serializable;
import java.util.List;

public class PageHelperResult implements Serializable {
    private int pages;
    private List rows;
    private int total;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
