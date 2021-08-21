package com.anthonyguidotti.spotify_api;

import com.anthonyguidotti.spotify_api.client.SpotifyClient;
import com.anthonyguidotti.spotify_api.model.AuthorizationScope;
import com.anthonyguidotti.spotify_api.response.AccessTokenResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Arrays;

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

				System.out.println(url);

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
	public void testAuthtoken1() {
		HttpResponse<AccessTokenResponse> res = spotifyClient.accessTokenSync(authentication.getAuthorizationCode());
		Assert.notNull(res, "Response must not be null");
	}

	@Test
	public void testAuthtoken2() {
		HttpResponse<AccessTokenResponse> res = spotifyClient.accessTokenSync(authentication.getAuthorizationCode());
		Assert.notNull(res, "Response must not be null");
	}
}
