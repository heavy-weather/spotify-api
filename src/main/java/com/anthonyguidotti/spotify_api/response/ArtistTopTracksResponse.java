package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.TrackObject;

import java.util.List;

public class ArtistTopTracksResponse implements SpotifyAPIResponse {
    private List<TrackObject> tracks;

    public List<TrackObject> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackObject> tracks) {
        this.tracks = tracks;
    }
}
