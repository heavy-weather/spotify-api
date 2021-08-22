package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PlaylistTrackObject {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    @JsonProperty("added_by")
    private PublicUserObject addedBy;
    @JsonProperty("is_local")
    private Boolean isLocal;
    private TrackOrEpisode track;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public PublicUserObject getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(PublicUserObject addedBy) {
        this.addedBy = addedBy;
    }

    public Boolean getLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public TrackOrEpisode getTrack() {
        return track;
    }

    public void setTrack(TrackOrEpisode track) {
        this.track = track;
    }
}
