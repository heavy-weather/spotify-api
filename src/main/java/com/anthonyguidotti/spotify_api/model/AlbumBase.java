package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlbumBase {
    @JsonProperty("album_type")
    private String albumType;
    private List<SimplifiedArtistObject> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    private List<CopyrightObject> copyrights;
    @JsonProperty("external_ids")
    private ExternalIdObject externalIds;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private List<String> genres;
    private String href;
    private String id;
    private List<ImageObject> images;
    private String name;
    private Integer popularity;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    private AlbumRestrictionObject restrictions;
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    private CursorPagingObject<SimplifiedTrackObject> tracks;
    private String type;
    private String uri;

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
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

    public List<ImageObject> getImages() {
        return images;
    }

    public void setImages(List<ImageObject> images) {
        this.images = images;
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

    public AlbumRestrictionObject getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(AlbumRestrictionObject restrictions) {
        this.restrictions = restrictions;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
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

    public List<SimplifiedArtistObject> getArtists() {
        return artists;
    }

    public void setArtists(List<SimplifiedArtistObject> artists) {
        this.artists = artists;
    }

    public List<CopyrightObject> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<CopyrightObject> copyrights) {
        this.copyrights = copyrights;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public CursorPagingObject<SimplifiedTrackObject> getTracks() {
        return tracks;
    }

    public void setTracks(CursorPagingObject<SimplifiedTrackObject> tracks) {
        this.tracks = tracks;
    }
}
