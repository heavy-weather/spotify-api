package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PlayHistory {
    private Context context;
    @JsonProperty("played_at")
    private LocalDateTime playedAt;
    private SimplifiedTrack track;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public SimplifiedTrack getTrack() {
        return track;
    }

    public void setTrack(SimplifiedTrack track) {
        this.track = track;
    }
}
