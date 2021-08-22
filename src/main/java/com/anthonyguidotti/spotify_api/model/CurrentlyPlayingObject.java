package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentlyPlayingObject {
    private ContextObject context;
    @JsonProperty("currently_playing_type")
    private String currentlyPlayingType;
    @JsonProperty("is_playing")
    private Boolean isPlaying;
    private TrackOrEpisode item;
    @JsonProperty("progress_ms")
    private Integer progressMs;
    private Integer timestamp;

    public ContextObject getContext() {
        return context;
    }

    public void setContext(ContextObject context) {
        this.context = context;
    }

    public String getCurrentlyPlayingType() {
        return currentlyPlayingType;
    }

    public void setCurrentlyPlayingType(String currentlyPlayingType) {
        this.currentlyPlayingType = currentlyPlayingType;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public Integer getProgressMs() {
        return progressMs;
    }

    public void setProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public TrackOrEpisode getItem() {
        return item;
    }

    public void setItem(TrackOrEpisode item) {
        this.item = item;
    }
}
