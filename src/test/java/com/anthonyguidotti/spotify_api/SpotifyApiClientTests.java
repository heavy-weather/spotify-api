package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyClient;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.response.AccessTokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest(
		classes = {
				TestController.class,
				TestConfig.class,
				Authentication.class,
				TestApplication.class
		},
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class SpotifyApiClientTests {
	private static final Logger logger = LoggerFactory.getLogger(SpotifyApiClientTests.class);

	private final SpotifyClient spotifyClient;
	private final Authentication authentication;

	@Autowired
	public SpotifyApiClientTests(SpotifyClient spotifyClient, Authentication authentication) {
		this.spotifyClient = spotifyClient;
		this.authentication = authentication;
	}

	@BeforeEach
	public void before() {
		if (!StringUtils.hasLength(authentication.getAccessnToken())) {
			try {
				URL url = spotifyClient.authorizationCodeUrl(
						null,
						Arrays.asList(AuthorizationScope.values()),
						true
				);

				logger.info("\nClick to bootstrap tests: {}", url);

				while (!StringUtils.hasLength(authentication.getAccessnToken())) {
					Thread.sleep(1000);
					if (StringUtils.hasLength(authentication.getAuthorizationCode())) {
						HttpResponse<AccessTokenResponse> response = spotifyClient.accessTokenSync(
								authentication.getAuthorizationCode());
						authentication.setAccessnToken(response.body().getAccessToken());
					}
				}
			} catch (MalformedURLException | InterruptedException e) {
				throw new RuntimeException("Error bootstrapping test", e);
			}
		}
	}

	@Test
	public void accessTokenAsync() throws ExecutionException, InterruptedException {
		// Can set authorizationCode to null here because we already retrieved the
		// access token during the bootstrap phase
		authentication.setAuthorizationCode(null);

		try {
			URL url = spotifyClient.authorizationCodeUrl(
					null,
					Arrays.asList(AuthorizationScope.values()),
					true
			);

			logger.info("\nClick to test access token asynchronous method: {}", url);

			while (!StringUtils.hasLength(authentication.getAuthorizationCode())) {
				Thread.sleep(1000);
			}
		} catch (MalformedURLException | InterruptedException e) {
			throw new RuntimeException("Error bootstrapping test", e);
		}

		CompletableFuture<HttpResponse<AccessTokenResponse>> response = spotifyClient.accessTokenAsync(
				authentication.getAuthorizationCode()
		);

		HttpResponse<AccessTokenResponse> res = response.get();
		Assert.isTrue(res.statusCode() == 200, "Response must be successful");
		Assert.notNull(res.body(), "Body must not be null");
		Assert.notNull(res.body().getAccessToken(), "Body must contain access token");
	}
}
