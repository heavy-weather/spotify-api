package com.anthonyguidotti.spotify_api.model;

public class PlayerError {
    private String message;
    private Reason reason;
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public enum Reason {
        NO_PREV_TRACK,
        NO_NEXT_TRACK,
        NO_SPECIFIC_TRACK,
        ALREADY_PAUSED,
        NOT_PAUSED,
        NOT_PLAYING_LOCALLY,
        NOT_PLAYING_TRACK,
        NOT_PLAYING_CONTEXT,
        ENDLESS_CONTEXT,
        CONTEXT_DISALLOW,
        ALREADY_PLAYING,
        RATE_LIMITED,
        REMOTE_CONTROL_DISALLOW,
        DEVICE_NOT_CONTROLLABLE,
        VOLUME_CONTROL_DISALLOW,
        NO_ACTIVE_DEVICE,
        PREMIUM_REQUIRED,
        UNKNOWN;
    }
}
