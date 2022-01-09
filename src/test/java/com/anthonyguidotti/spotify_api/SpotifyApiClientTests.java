package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyAsyncClient;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.model.CountryCode;
import com.anthonyguidotti.spotify_api.model.IncludeGroup;
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

	private final SpotifyAsyncClient spotifyClient;
	private final Authentication authentication;

	// Constants
	private final String ALBUM_ID = "382ObEPsp2rxGrnsizN5TX";
	private final String ARTIST_ID = "2CIMQHirSU0MQqyYHq0eOx";
	private final CountryCode countryCode = CountryCode.US;

	@Autowired
	public SpotifyApiClientTests(SpotifyAsyncClient spotifyClient, Authentication authentication) {
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
						false
				);
				logger.info("\nClick to bootstrap tests: {}", url);

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
	public void multipleAlbums() throws ExecutionException, InterruptedException {
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
	public void singleAlbums() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.singleAlbum(
				authentication.getAccessnToken(), ALBUM_ID, countryCode);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		SingleAlbumResponse body = (SingleAlbumResponse) spotifyAPIResponse;
		Assert.notNull(body.getName(), "Body must contain access token");
	}

	@Test
	public void albumTracks() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.albumTracks(
				authentication.getAccessnToken(), ALBUM_ID, countryCode, 0, 0);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		AlbumTracksResponse body = (AlbumTracksResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getItems(), "Body must contain access token");
		Assert.notNull(body.getItems().get(0), "Body must contain at least one track");
	}

	@Test
	public void multipleArtists() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.multipleArtists(
				authentication.getAccessnToken(), Collections.singletonList(ARTIST_ID)
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		MultipleArtistsResponse body = (MultipleArtistsResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getArtists(), "Body must contain access token");
		Assert.notNull(body.getArtists().get(0), "Body must contain at least one album");
	}

	@Test
	public void singleArtist() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.singleArtist(
				authentication.getAccessnToken(), ARTIST_ID
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		SingleArtistResponse body = (SingleArtistResponse) spotifyAPIResponse;
		Assert.isTrue(StringUtils.hasLength(body.getId()), "Body must contain at least one album");
	}

	@Test
	public void artistTopTracks() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.artistTopTracks(
				authentication.getAccessnToken(), ARTIST_ID, countryCode
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		ArtistTopTracksResponse body = (ArtistTopTracksResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getTracks(), "Body must contain at least one album");
	}

	@Test
	public void artistRelatedArtists() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.artistRelatedArtists(
				authentication.getAccessnToken(), ARTIST_ID
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		ArtistRelatedArtistsResponse body = (ArtistRelatedArtistsResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getArtists(), "Body must contain at least one album");
	}

	@Test
	public void artistAlbums() throws ExecutionException, InterruptedException {
		CompletableFuture<HttpResponse<SpotifyAPIResponse>> future = spotifyClient.artistAlbums(
				authentication.getAccessnToken(),
				"0TnOYISbd1XYRBk9myaseg",
				Arrays.asList(IncludeGroup.SINGLE, IncludeGroup.APPEARS_ON),
				countryCode,
				0,
				0
		);

		HttpResponse<SpotifyAPIResponse> response = future.get();

		Assert.isTrue(response.statusCode() == 200, "Response must be successful");
		Assert.notNull(response.body(), "Body must not be null");

		SpotifyAPIResponse spotifyAPIResponse = response.body();
		ArtistAlbumsResponse body = (ArtistAlbumsResponse) spotifyAPIResponse;
		Assert.notEmpty(body.getItems(), "Body must contain at least one album");
	}
}
