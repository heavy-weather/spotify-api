package com.anthonyguidotti.spotify_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExplicitContentSettingObject {
    @JsonProperty("filter_enabled")
    private Boolean filterEnabled;
    @JsonProperty("filter_locked")
    private Boolean filterLocked;

    public Boolean getFilterEnabled() {
        return filterEnabled;
    }

    public void setFilterEnabled(Boolean filterEnabled) {
        this.filterEnabled = filterEnabled;
    }

    public Boolean getFilterLocked() {
        return filterLocked;
    }

    public void setFilterLocked(Boolean filterLocked) {
        this.filterLocked = filterLocked;
    }
}
