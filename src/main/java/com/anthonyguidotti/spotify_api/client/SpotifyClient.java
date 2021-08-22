package com.anthonyguidotti.spotify_api.client;

import com.anthonyguidotti.spotify_api.jackson.JacksonDeserializerBodySubscriber;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.response.AccessTokenResponse;
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
    private final String authHeader;
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

        byte[] bearerNotEncoded = (clientId + ":" + clientSecret).getBytes();
        authHeader = "Basic " + new String(Base64Utils.encode(bearerNotEncoded));
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
            sb.append("&show_dialogue=true");
        }

        return new URL(sb.toString());
    }

    private HttpRequest accessTokenRequest(String code) {
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

    public HttpResponse<AccessTokenResponse> accessTokenSync(String code) {
        HttpRequest request = accessTokenRequest(code);
        try {
            return client.send(
                    request,
                    (ri) -> new JacksonDeserializerBodySubscriber<>(AccessTokenResponse.class)
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompletableFuture<HttpResponse<String>> accessTokenAsync(String code) {
        return client.sendAsync(
                accessTokenRequest(code),
                null
        );
    }

    private URI httpsUri(String host, String path, String[] ... queryParams) {
        String query = "";
        if (queryParams.length > 0) {
            query = "?" + urlEncodedKeyValueString(queryParams);
        }

        // Catch URISyntaxException so this doesn't need to be handled by endpoint methods
        try {
            return new URI("https", host, path, query);
        } catch (URISyntaxException e) {
            logger.error(
                    "Could not build URI with parameters scheme: {}, host: {}, path: {}, query: {}",
                    "http", host, path, query, e
            );
            throw new RuntimeException(e);
        }
    }

    private String urlEncodedKeyValueString(String[] ... queryParams) {
        StringBuilder qs = new StringBuilder();
        for (String[] queryParam : queryParams) {
            if (qs.length() > 0) {
                qs.append('&');
            }
            qs.append(URLEncoder.encode(queryParam[0], Charset.defaultCharset()))
                    .append('=')
                    .append(URLEncoder.encode(queryParam[1], Charset.defaultCharset()));
        }
        return qs.toString();
    }
}
