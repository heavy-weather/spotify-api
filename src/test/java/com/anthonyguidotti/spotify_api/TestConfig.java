package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public SpotifyAsyncClient spotifyClient(
            @Value("${OAUTH_AUTHORIZATION-URL}") String authorizationUrl,
            @Value("${OAUTH_API-URL}") String apiUrl,
            @Value("${OAUTH_CLIENT-ID}") String clientId,
            @Value("${OAUTH_CLIENT-SECRET}") String clientSecret,
            @Value("${OAUTH_REDIRECT-URI}") String redirectUri
    ) {
        return new SpotifyAsyncClient(authorizationUrl, apiUrl, clientId, clientSecret, redirectUri);
    }
}
