package com.anthonyguidotti.spotify_api;

import org.springframework.stereotype.Component;

@Component
public class Authentication {
    private String authorizationCode;
    private String accessnToken;
    private String refreshToken;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAccessnToken() {
        return accessnToken;
    }

    public void setAccessnToken(String accessnToken) {
        this.accessnToken = accessnToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
