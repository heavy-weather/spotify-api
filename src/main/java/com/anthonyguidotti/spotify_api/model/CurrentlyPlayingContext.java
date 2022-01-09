package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentlyPlayingContext {
    private Disallows actions;
    private Context context;
    @JsonProperty("currently_playing_type")
    private String currentlyPlayingType;
    private Device device;
    @JsonProperty("is_playing")
    private Boolean isPlaying;
    private TrackOrEpisode item;
    @JsonProperty("progress_ms")
    private Integer progressMs;
    @JsonProperty("repeat_state")
    private String repeatState;
    @JsonProperty("shuffle_state")
    private String shuffleState;
    private Integer timestamp;

    public Disallows getActions() {
        return actions;
    }

    public void setActions(Disallows actions) {
        this.actions = actions;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getCurrentlyPlayingType() {
        return currentlyPlayingType;
    }

    public void setCurrentlyPlayingType(String currentlyPlayingType) {
        this.currentlyPlayingType = currentlyPlayingType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public TrackOrEpisode getItem() {
        return item;
    }

    public void setItem(TrackOrEpisode item) {
        this.item = item;
    }

    public Integer getProgressMs() {
        return progressMs;
    }

    public void setProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
    }

    public String getRepeatState() {
        return repeatState;
    }

    public void setRepeatState(String repeatState) {
        this.repeatState = repeatState;
    }

    public String getShuffleState() {
        return shuffleState;
    }

    public void setShuffleState(String shuffleState) {
        this.shuffleState = shuffleState;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
