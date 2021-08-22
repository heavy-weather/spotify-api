package com.anthonyguidotti.spotify_api.model;

import java.util.List;

public class CursorPagingObject<T> {
    private CursorObject cursors;
    private String href;
    private List<T> items;
    private Integer limit;
    private String next;
    private Integer total;

    public CursorObject getCursors() {
        return cursors;
    }

    public void setCursors(CursorObject cursors) {
        this.cursors = cursors;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
