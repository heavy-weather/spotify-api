package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Album {
    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("album_group")
    private IncludeGroup albumGroup;
    private List<SimplifiedArtist> artists;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    private List<Copyright> copyrights;
    @JsonProperty("external_ids")
    private ExternalId externalIds;
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private Integer popularity;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    private AlbumRestriction restrictions;
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    private CursorPaging<SimplifiedTrack> tracks;
    private String type;
    private String uri;
    private String label;

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

    public AlbumRestriction getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(AlbumRestriction restrictions) {
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

    public List<SimplifiedArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SimplifiedArtist> artists) {
        this.artists = artists;
    }

    public List<Copyright> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<Copyright> copyrights) {
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

    public CursorPaging<SimplifiedTrack> getTracks() {
        return tracks;
    }

    public void setTracks(CursorPaging<SimplifiedTrack> tracks) {
        this.tracks = tracks;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public IncludeGroup getAlbumGroup() {
        return albumGroup;
    }

    public void setAlbumGroup(IncludeGroup albumGroup) {
        this.albumGroup = albumGroup;
    }
}
