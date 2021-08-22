package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SavedEpisodeObject {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    private EpisodeBase episode;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public EpisodeBase getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeBase episode) {
        this.episode = episode;
    }
}
