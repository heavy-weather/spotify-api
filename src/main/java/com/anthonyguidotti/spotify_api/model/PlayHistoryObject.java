package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PlayHistoryObject {
    private ContextObject context;
    @JsonProperty("played_at")
    private LocalDateTime playedAt;
    private SimplifiedTrackObject track;

    public ContextObject getContext() {
        return context;
    }

    public void setContext(ContextObject context) {
        this.context = context;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public SimplifiedTrackObject getTrack() {
        return track;
    }

    public void setTrack(SimplifiedTrackObject track) {
        this.track = track;
    }
}
