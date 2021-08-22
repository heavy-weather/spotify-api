package com.anthonyguidotti.spotify_api.model;

import java.util.List;

public class CategoryObject {
    private String href;
    private List<ImageObject> icons;
    private String id;
    private String name;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<ImageObject> getIcons() {
        return icons;
    }

    public void setIcons(List<ImageObject> icons) {
        this.icons = icons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
