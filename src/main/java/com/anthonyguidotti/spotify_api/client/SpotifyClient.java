package com.anthonyguidotti.spotify_api.client;

import com.anthonyguidotti.spotify_api.jackson.JacksonDeserializerBodySubscriber;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.response.AccessTokenResponse;
import com.anthonyguidotti.spotify_api.response.MultipleAlbumsResponse;
import com.anthonyguidotti.spotify_api.response.SpotifyAPIResponse;
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

    private HttpRequest accessTokenRequest(String code) {
        byte[] headerBytes = (clientId + ":" + clientSecret).getBytes();
        String authHeader = "Basic " + new String(Base64Utils.encode(headerBytes));

        return HttpRequest.newBuilder()
                .uri(httpsUri(authorizationUrl, "/api/token"))
                .header("Authorization", authHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(urlEncodedKeyValueString(
                        new String[] {"grant_type", "authorization_code"},
                        new String[] {"code", code},
                        new String[] {"redirect_uri", redirectUri}
                )))
                .build();
    }

    public HttpResponse<SpotifyAPIResponse> accessTokenSync(String code) throws IOException, InterruptedException {
        return client.send(
                accessTokenRequest(code),
                (ri) -> new JacksonDeserializerBodySubscriber(AccessTokenResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> accessTokenAsync(String code) {
        return client.sendAsync(
                accessTokenRequest(code),
                (ri) -> new JacksonDeserializerBodySubscriber(AccessTokenResponse.class)
        );
    }

    private HttpRequest multipleAlbumsRequest(
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

        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();
    }

    public HttpResponse<SpotifyAPIResponse> multipleAlbumsSync(
            String accessToken,
            List<String> albumIds
    ) throws IOException, InterruptedException {
        return client.send(
                multipleAlbumsRequest(accessToken, albumIds),
                (ri) -> new JacksonDeserializerBodySubscriber(MultipleAlbumsResponse.class)
        );
    }

    public CompletableFuture<HttpResponse<SpotifyAPIResponse>> multipleAlbumsAsync(
            String accessToken,
            List<String> albumIds
    ) {
        return client.sendAsync(
                multipleAlbumsRequest(accessToken, albumIds),
                (ri) -> new JacksonDeserializerBodySubscriber(MultipleAlbumsResponse.class)
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
            if (queryParam.length == 0) {
                throw new IllegalArgumentException("Each query param array must have at least 1 argument");
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
