package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyClient;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
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

	// Constants
	private final String ALBUM_ID = "382ObEPsp2rxGrnsizN5TX";

	@Autowired
	public SpotifyApiClientTests(SpotifyClient spotifyClient, Authentication authentication) {
		this.spotifyClient = spotifyClient;
		this.authentication = authentication;
	}

	private URL url() throws MalformedURLException {
		return spotifyClient.authorizationCodeUrl(
				null,
				Arrays.asList(AuthorizationScope.values()),
				false
		);
	}

	@BeforeEach
	public void before() {
		if (!StringUtils.hasLength(authentication.getAccessnToken())) {
			try {
				logger.info("\nClick to bootstrap tests: {}", url());

				while (!StringUtils.hasLength(authentication.getAccessnToken())) {
					Thread.sleep(1000);
					if (StringUtils.hasLength(authentication.getAuthorizationCode())) {
						SpotifyAPIResponse response = spotifyClient.accessToken(
								authentication.getAuthorizationCode()).get().body();
						if (response instanceof AccessTokenResponse) {
							authentication.setAccessnToken(((AccessTokenResponse)response).getAccessToken());
						} else {
							throw new RuntimeException(((ErrorResponse) response).getError().getMessage());
						}
					}
				}
			} catch (IOException | InterruptedException | ExecutionException e) {
				throw new RuntimeException("Error bootstrapping test", e);
			}
		}
	}

	@Test
	public void multipleAlbumsAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.multipleAlbums(
				authentication.getAccessnToken(), Collections.singletonList(ALBUM_ID)
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		MultipleAlbumsResponse body = (MultipleAlbumsResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getAlbums(), "Body must contain access token");
		Assert.notNull(body.getAlbums().get(0), "Body must contain at least one album");
	}

	@Test
	public void singleAlbumsAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.singleAlbum(
				authentication.getAccessnToken(), ALBUM_ID, "ES");

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		SingleAlbumResponse body = (SingleAlbumResponse) spotifyAPIResponse;
		Assert.notNull(body.getName(), "Body must contain access token");
	}

	@Test
	public void albumTracksAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.albumTracks(
				authentication.getAccessnToken(), ALBUM_ID, null, 0, 0);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		AlbumTracksResponse body = (AlbumTracksResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getItems(), "Body must contain access token");
		Assert.notNull(body.getItems().get(0), "Body must contain at least one track");
	}
}
