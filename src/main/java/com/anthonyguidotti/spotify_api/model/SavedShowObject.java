package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SavedShowObject {
    @JsonProperty("added_at")
    private LocalDateTime addedAt;
    private ShowBase show;

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public ShowBase getShow() {
        return show;
    }

    public void setShow(ShowBase show) {
        this.show = show;
    }
}
