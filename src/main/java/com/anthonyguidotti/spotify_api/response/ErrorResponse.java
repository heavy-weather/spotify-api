package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.Error;

public class ErrorResponse implements SpotifyAPIResponse {
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
