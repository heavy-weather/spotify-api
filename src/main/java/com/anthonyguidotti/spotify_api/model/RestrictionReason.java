package com.anthonyguidotti.spotify_api.model;

public enum RestrictionReason {
    MARKET("market"),
    PRODUCT("product"),
    EXPLICIT("explicit");

    private final String name;

    RestrictionReason (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
