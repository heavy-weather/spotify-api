package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkedTrackObject {
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private String href;
    private String id;
    private String type;
    private String uri;

    public ExternalUrlObject getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrlObject externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
