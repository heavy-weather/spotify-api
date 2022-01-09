package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.Artist;

import java.util.List;

public class ArtistRelatedArtistsResponse implements SpotifyAPIResponse {
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
