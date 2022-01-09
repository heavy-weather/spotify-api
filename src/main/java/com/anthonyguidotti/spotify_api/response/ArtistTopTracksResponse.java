package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.Track;

import java.util.List;

public class ArtistTopTracksResponse implements SpotifyAPIResponse {
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
