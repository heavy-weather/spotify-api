package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShowBase {
    @JsonProperty("available_markets")
    List<String> availableMarkets;
    private List<CopyrightObject> copyrights;
    private String description;
    private Boolean explicit;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private String href;
    @JsonProperty("html_description")
    private String htmlDescription;
    private String id;
    private List<ImageObject> images;
    @JsonProperty("is_externally_hosted")
    private Boolean isExternallyHosted;
    private List<String> languages;
    @JsonProperty("media_type")
    private String mediaType;
    private String name;
    private String publisher;
    @JsonProperty("total_episodes")
    private Integer totalEpisodes;
    private String type;
    private String uri;

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public List<CopyrightObject> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<CopyrightObject> copyrights) {
        this.copyrights = copyrights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

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

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageObject> getImages() {
        return images;
    }

    public void setImages(List<ImageObject> images) {
        this.images = images;
    }

    public Boolean getExternallyHosted() {
        return isExternallyHosted;
    }

    public void setExternallyHosted(Boolean externallyHosted) {
        isExternallyHosted = externallyHosted;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
}
