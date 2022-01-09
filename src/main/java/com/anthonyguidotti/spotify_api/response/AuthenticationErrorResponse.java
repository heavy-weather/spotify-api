package com.anthonyguidotti.spotify_api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationErrorResponse implements SpotifyAPIResponse {
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
