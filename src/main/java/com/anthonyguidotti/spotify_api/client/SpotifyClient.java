package com.anthonyguidotti.spotify_api.client;

import com.anthonyguidotti.spotify_api.jackson.JacksonDeserializerBodySubscriber;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.model.CursorPagingObject;
import com.anthonyguidotti.spotify_api.model.IncludeGroup;
import com.anthonyguidotti.spotify_api.model.SimplifiedTrackObject;
import com.anthonyguidotti.spotify_api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SpotifyClient {
    private static final Logger logger = LoggerFactory.getLogger(SpotifyClient.class);

    private final String authorizationUrl;
    private final String apiUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final HttpClient client;

    public SpotifyClient(
            String authorizationUrl,
            String apiUrl,
            String clientId,
            String clientSecret,
            String redirectUri
    ) {
        this.authorizationUrl = authorizationUrl;
        this.apiUrl = apiUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;

        client = HttpClient.newHttpClient();
    }

    public URL authorizationCodeUrl(
            String state,
            List<AuthorizationScope> scopes,
            boolean showDialogue
    ) throws MalformedURLException {
        Charset charset = Charset.defaultCharset();
        StringBuilder sb = new StringBuilder("https://").append(authorizationUrl).append("/authorize")
                .append("?client_id=").append(URLEncoder.encode(clientId, charset))
                .append("&response_type=code")
                .append("&redirect_uri=").append(URLEncoder.encode(redirectUri, charset));
        if (StringUtils.hasLength(state)) {
                sb.append("&state=").append(URLEncoder.encode(state, charset));
        }
        if (!CollectionUtils.isEmpty(scopes)) {
            String scopesValue = scopes.stream()
                    .map(AuthorizationScope::getName)
                    .collect(Collectors.joining(" "));

            sb.append("&scope=").append(URLEncoder.encode(scopesValue, charset));
        }
        if (showDialogue) {
            sb.append("&show_dialog=true");
        }

        return new URL(sb.toString());
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> accessToken(String code) {
        byte[] headerBytes = (clientId + ":" + clientSecret).getBytes();
        String authHeader = "Basic " + new String(Base64Utils.encode(headerBytes));

        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(authorizationUrl, "/api/token"))
                        .header("Authorization", authHeader)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(urlEncodedKeyValueString(
                                new String[] {"grant_type", "authorization_code"},
                                new String[] {"code", code},
                                new String[] {"redirect_uri", redirectUri}
                        )))
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(AccessTokenResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> multipleAlbums(
            String accessToken,
            List<String> albumIds
    ) {
        URI uri;
        if (albumIds != null && albumIds.size() > 0) {
            String[] idParam = new String[albumIds.size() + 1];
            idParam[0] = "ids";
            for (int i = 0; i < albumIds.size(); i++) {
                idParam[i + 1] = albumIds.get(i);
            }
            uri = httpsUri(apiUrl, "/albums", idParam);
        } else {
            uri = httpsUri(apiUrl, "/albums");
        }

        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(uri)
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(MultipleAlbumsResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> singleAlbum(
            String accessToken,
            String albumId,
            String market
    ) {
        if (!StringUtils.hasLength(albumId)) {
            throw new IllegalArgumentException("Parameter albumId is required");
        }
        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(
                                apiUrl,
                                "/albums/" + albumId,
                                new String[] {"market", market}
                        ))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(SingleAlbumResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> albumTracks(
            String accessToken,
            String albumId,
            String market,
            int limit,
            int offset
    ) {
        if (!StringUtils.hasLength(albumId)) {
            throw new IllegalArgumentException("Parameter albumId is required");
        }
        String limitString = null;
        if (offset > 0) {
            limitString = String.valueOf(limit);
        }

        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(
                                apiUrl,
                                "/albums/" + albumId + "/tracks",
                                new String[] {"market", market},
                                new String[] {"limit", limitString},
                                new String[] {"offset", String.valueOf(offset)}
                        ))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(AlbumTracksResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> multipleArtists(
            String accessToken,
            List<String> artistIds
    ) {
        URI uri;
        if (artistIds != null && artistIds.size() > 0) {
            String[] idParam = new String[artistIds.size() + 1];
            idParam[0] = "ids";
            for (int i = 0; i < artistIds.size(); i++) {
                idParam[i + 1] = artistIds.get(i);
            }
            uri = httpsUri(apiUrl, "/artists", idParam);
        } else {
            uri = httpsUri(apiUrl, "/artists");
        }

        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(uri)
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(MultipleArtistsResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> singleArtist(
            String accessToken,
            String artistId
    ) {
        if (!StringUtils.hasLength(artistId)) {
            throw new IllegalArgumentException("Parameter artistId is required");
        }
        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(apiUrl, "/artists/" + artistId))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(SingleArtistResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> artistTopTracks(
            String accessToken,
            String artistId,
            String market
    ) {
        if (!StringUtils.hasLength(artistId)) {
            throw new IllegalArgumentException("Parameter artistId is required");
        }
        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(
                                apiUrl,
                                "/artists/" + artistId + "/top-tracks",
                                new String[] {"market", market}
                        ))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(ArtistTopTracksResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> artistRelatedArtists(
            String accessToken,
            String artistId
    ) {
        if (!StringUtils.hasLength(artistId)) {
            throw new IllegalArgumentException("Parameter artistId is required");
        }
        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(apiUrl, "/artists/" + artistId + "/related-artists"))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(ArtistRelatedArtistsResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> artistAlbums(
            String accessToken,
            String artistId,
            List<IncludeGroup> includeGroups,
            String market,
            int limit,
            int offset
    ) {
        if (!StringUtils.hasLength(artistId)) {
            throw new IllegalArgumentException("Parameter artistId is required");
        }
        String limitString = null;
        if (offset > 0) {
            limitString = String.valueOf(limit);
        }

        String[] incGroupsParam;
        if (includeGroups != null && includeGroups.size() > 0) {
            incGroupsParam = new String[includeGroups.size() + 1];
            incGroupsParam[0] = "ids";
            for (int i = 0; i < includeGroups.size(); i++) {
                incGroupsParam[i + 1] = includeGroups.get(i).getName();
            }
        } else {
            incGroupsParam = null;
        }

        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(httpsUri(
                                apiUrl,
                                "/artists/" + artistId + "/albums",

                                new String[] {"market", market},
                                incGroupsParam,
                                new String[] {"limit", limitString},
                                new String[] {"offset", String.valueOf(offset)}
                        ))
                        .header("Authorization", "Bearer " + accessToken)
                        .GET()
                        .build(),
                (ri) -> new JacksonDeserializerBodySubscriber(ArtistAlbumsResponse.class)
        );
    }



    private URI httpsUri(String host, String path, String[] ... queryParams) {
        String query = "";
        if (queryParams.length > 0) {
            query = "?" + urlEncodedKeyValueString(queryParams);
        }

        return URI.create("https://" + host + path + query);
    }

    private String urlEncodedKeyValueString(String[] ... queryParams) {
        StringBuilder qs = new StringBuilder();
        for (String[] queryParam : queryParams) {
            if (queryParam.length < 2 || queryParam[1] == null) {
                continue;
            }
            if (qs.length() > 0) {
                qs.append('&');
            }

            qs.append(URLEncoder.encode(queryParam[0], Charset.defaultCharset())).append('=');
            for (int i = 1; i < queryParam.length; i++) {
                qs.append(URLEncoder.encode(queryParam[i], Charset.defaultCharset()));
                if (i < queryParam.length - 1) {
                    qs.append(',');
                }
            }
        }
        return qs.toString();
    }
}
