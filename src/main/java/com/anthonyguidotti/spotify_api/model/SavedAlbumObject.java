package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SavedAlbumObject {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    private AlbumBase album;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public AlbumBase getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBase album) {
        this.album = album;
    }
}
