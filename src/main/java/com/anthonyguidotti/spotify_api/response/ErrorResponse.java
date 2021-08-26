package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.ErrorObject;

public class ErrorResponse implements SpotifyAPIResponse {
    private ErrorObject error;

    public ErrorObject getError() {
        return error;
    }

    public void setError(ErrorObject error) {
        this.error = error;
    }
}
