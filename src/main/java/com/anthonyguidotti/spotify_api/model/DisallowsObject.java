package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisallowsObject {
    @JsonProperty("interrupting_playback")
    private Boolean interruptingPlayback;
    private Boolean pausing;
    private Boolean resuming;
    private Boolean seeking;
    @JsonProperty("skipping_next")
    private Boolean skippingNext;
    @JsonProperty("skipping_prev")
    private Boolean skippingPrev;
    @JsonProperty("toggling_repeat_context")
    private Boolean togglingRepeatContext;
    @JsonProperty("toggling_repeat_track")
    private Boolean togglingRepeatTrack;
    @JsonProperty("toggling_shuffle")
    private Boolean togglingShuffle;
    @JsonProperty("transferring_playback")
    private Boolean transferringPlayback;

    public Boolean getInterruptingPlayback() {
        return interruptingPlayback;
    }

    public void setInterruptingPlayback(Boolean interruptingPlayback) {
        this.interruptingPlayback = interruptingPlayback;
    }

    public Boolean getPausing() {
        return pausing;
    }

    public void setPausing(Boolean pausing) {
        this.pausing = pausing;
    }

    public Boolean getResuming() {
        return resuming;
    }

    public void setResuming(Boolean resuming) {
        this.resuming = resuming;
    }

    public Boolean getSeeking() {
        return seeking;
    }

    public void setSeeking(Boolean seeking) {
        this.seeking = seeking;
    }

    public Boolean getSkippingNext() {
        return skippingNext;
    }

    public void setSkippingNext(Boolean skippingNext) {
        this.skippingNext = skippingNext;
    }

    public Boolean getSkippingPrev() {
        return skippingPrev;
    }

    public void setSkippingPrev(Boolean skippingPrev) {
        this.skippingPrev = skippingPrev;
    }

    public Boolean getTogglingRepeatContext() {
        return togglingRepeatContext;
    }

    public void setTogglingRepeatContext(Boolean togglingRepeatContext) {
        this.togglingRepeatContext = togglingRepeatContext;
    }

    public Boolean getTogglingRepeatTrack() {
        return togglingRepeatTrack;
    }

    public void setTogglingRepeatTrack(Boolean togglingRepeatTrack) {
        this.togglingRepeatTrack = togglingRepeatTrack;
    }

    public Boolean getTogglingShuffle() {
        return togglingShuffle;
    }

    public void setTogglingShuffle(Boolean togglingShuffle) {
        this.togglingShuffle = togglingShuffle;
    }

    public Boolean getTransferringPlayback() {
        return transferringPlayback;
    }

    public void setTransferringPlayback(Boolean transferringPlayback) {
        this.transferringPlayback = transferringPlayback;
    }
}
