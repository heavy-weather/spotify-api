package com.anthonyguidotti.spotify_api.response;

import com.anthonyguidotti.spotify_api.model.AlbumBase;

import java.util.List;

public class MultipleAlbumsResponse implements SpotifyAPIResponse {
    private List<AlbumBase> albums;

    public List<AlbumBase> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumBase> albums) {
        this.albums = albums;
    }
}
