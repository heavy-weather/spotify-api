package com.anthonyguidotti.spotify_api.model;

import java.util.List;

public class Recommendations {
    private List<RecommendationSeed> seeds;
    private List<SimplifiedTrack> tracks;

    public List<RecommendationSeed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<RecommendationSeed> seeds) {
        this.seeds = seeds;
    }

    public List<SimplifiedTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<SimplifiedTrack> tracks) {
        this.tracks = tracks;
    }
}
