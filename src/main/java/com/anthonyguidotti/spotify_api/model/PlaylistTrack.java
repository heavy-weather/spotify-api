package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PlaylistTrack {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    @JsonProperty("added_by")
    private PublicUser addedBy;
    @JsonProperty("is_local")
    private Boolean isLocal;
    private TrackOrEpisode track;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public PublicUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(PublicUser addedBy) {
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
