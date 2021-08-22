package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumePointObject {
    @JsonProperty("fully_played")
    private Boolean fullyPlayed;
    @JsonProperty("resume_position_ms")
    private Integer resumePositionMs;

    public Boolean getFullyPlayed() {
        return fullyPlayed;
    }

    public void setFullyPlayed(Boolean fullyPlayed) {
        this.fullyPlayed = fullyPlayed;
    }

    public Integer getResumePositionMs() {
        return resumePositionMs;
    }

    public void setResumePositionMs(Integer resumePositionMs) {
        this.resumePositionMs = resumePositionMs;
    }
}
