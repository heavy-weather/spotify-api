package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimplifiedTrackObject {
    private List<SimplifiedArtistObject> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    @JsonProperty("disc_number")
    private Integer discNumber;
    @JsonProperty("duration_ms")
    private Integer durationMs;
    private Boolean explicit;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private String href;
    private String id;
    @JsonProperty("is_local")
    private Boolean isLocal;
    @JsonProperty("is_playable")
    private Boolean isPlayable;
    @JsonProperty("linked_from")
    private LinkedTrackObject linkedFrom;
    private String name;
    @JsonProperty("preview_url")
    private String previewUrl;
    private TrackRestrictionsObject restrictions;
    @JsonProperty("track_number")
    private Integer trackNumber;
    private String type;
    private String uri;

    public List<SimplifiedArtistObject> getArtists() {
        return artists;
    }

    public void setArtists(List<SimplifiedArtistObject> artists) {
        this.artists = artists;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
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

    public Boolean getLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public Boolean getPlayable() {
        return isPlayable;
    }

    public void setPlayable(Boolean playable) {
        isPlayable = playable;
    }

    public LinkedTrackObject getLinkedFrom() {
        return linkedFrom;
    }

    public void setLinkedFrom(LinkedTrackObject linkedFrom) {
        this.linkedFrom = linkedFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public TrackRestrictionsObject getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(TrackRestrictionsObject restrictions) {
        this.restrictions = restrictions;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
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
