package com.anthonyguidotti.spotify_api.model;

public class TrackRestrictionsObject {
    private RestrictionReason reason;

    public RestrictionReason getReason() {
        return reason;
    }

    public void setReason(RestrictionReason reason) {
        this.reason = reason;
    }
}
