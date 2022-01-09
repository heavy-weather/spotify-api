package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Episode implements TrackOrEpisode {
    @JsonProperty("audio_preview_url")
    private String audioPreviewUrl;
    private String description;
    @JsonProperty("duration_ms")
    private Integer durationMs;
    private Boolean explicit;
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    private String href;
    @JsonProperty("html_description")
    private String htmlDescription;
    private String id;
    private List<Image> images;
    @JsonProperty("is_externally_hosted")
    private Boolean isExternallyHosted;
    @JsonProperty("is_playable")
    private Boolean isPlayable;
    private String language;
    private List<String> languages;
    private String name;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    private EpisodeRestriction restrictions;
    @JsonProperty("resume_point")
    private ResumePoint resumePoint;
    private ShowBase show;
    private String type;
    private String uri;

    public String getAudioPreviewUrl() {
        return audioPreviewUrl;
    }

    public void setAudioPreviewUrl(String audioPreviewUrl) {
        this.audioPreviewUrl = audioPreviewUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public ExternalUrl getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrl externalUrls) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getExternallyHosted() {
        return isExternallyHosted;
    }

    public void setExternallyHosted(Boolean externallyHosted) {
        isExternallyHosted = externallyHosted;
    }

    public Boolean getPlayable() {
        return isPlayable;
    }

    public void setPlayable(Boolean playable) {
        isPlayable = playable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(String releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public EpisodeRestriction getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(EpisodeRestriction restrictions) {
        this.restrictions = restrictions;
    }

    public ResumePoint getResumePoint() {
        return resumePoint;
    }

    public void setResumePoint(ResumePoint resumePoint) {
        this.resumePoint = resumePoint;
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

    public ShowBase getShow() {
        return show;
    }

    public void setShow(ShowBase show) {
        this.show = show;
    }
}
