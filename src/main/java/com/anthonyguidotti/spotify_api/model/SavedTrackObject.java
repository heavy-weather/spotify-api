package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SavedTrackObject {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    private TrackObject track;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public TrackObject getTrack() {
        return track;
    }

    public void setTrack(TrackObject track) {
        this.track = track;
    }
}
