package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public SpotifyClient spotifyClient(
            @Value("${OAUTH_AUTHORIZATION-URL}") String authorizationUrl,
            @Value("${OAUTH_API-URL}") String apiUrl,
            @Value("${OAUTH_CLIENT-ID}") String clientId,
            @Value("${OAUTH_CLIENT-SECRET}") String clientSecret,
            @Value("${OAUTH_REDIRECT-URI}") String redirectUri
    ) {
        return new SpotifyClient(authorizationUrl, apiUrl, clientId, clientSecret, redirectUri);
    }
}
