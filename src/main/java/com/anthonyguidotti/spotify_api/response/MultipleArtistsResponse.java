package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.ArtistObject;

import java.util.List;

public class MultipleArtistsResponse implements SpotifyAPIResponse {
    private List<ArtistObject> artists;

    public List<ArtistObject> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistObject> artists) {
        this.artists = artists;
    }
}
