package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.Album;

import java.util.List;

public class MultipleAlbumsResponse implements SpotifyAPIResponse {
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
