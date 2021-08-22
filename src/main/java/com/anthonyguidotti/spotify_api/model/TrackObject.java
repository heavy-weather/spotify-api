package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrackObject implements TrackOrEpisode {
    private AlbumBase album;
    List<ArtistObject> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    @JsonProperty("disc_number")
    private Integer discNumber;
    @JsonProperty("duration_ms")
    private Integer durationMs;
    private Boolean explicit;
    @JsonProperty("external_ids")
    private ExternalIdObject externalIds;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private String href;
    private String id;
    @JsonProperty("is_local")
    private Boolean isLocal;
    @JsonProperty("is_playable")
    private Boolean isPlayable;
    @JsonProperty("linked_from")
    private LinkedTrackObject linked_from;
    private String name;
    private Integer popularity;
    @JsonProperty("preview_url")
    private String previewUrl;
    private TrackRestrictionsObject restrictions;
    @JsonProperty("track_number")
    private Integer trackNumber;
    private String type;
    private String uri;

    public AlbumBase getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBase album) {
        this.album = album;
    }

    public List<ArtistObject> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistObject> artists) {
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

    public ExternalIdObject getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIdObject externalIds) {
        this.externalIds = externalIds;
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

    public LinkedTrackObject getLinked_from() {
        return linked_from;
    }

    public void setLinked_from(LinkedTrackObject linked_from) {
        this.linked_from = linked_from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
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
