package com.anthonyguidotti.spotify_api.model;

import java.util.List;

public class RecommendationsObject {
    private List<RecommendationSeedObject> seeds;
    private List<SimplifiedTrackObject> tracks;

    public List<RecommendationSeedObject> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<RecommendationSeedObject> seeds) {
        this.seeds = seeds;
    }

    public List<SimplifiedTrackObject> getTracks() {
        return tracks;
    }

    public void setTracks(List<SimplifiedTrackObject> tracks) {
        this.tracks = tracks;
    }
}
