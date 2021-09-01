package com.anthonyguidotti.spotify_api.model;

public enum IncludeGroup {
    ALBUM("album"),
    SINGLE("single"),
    APPEARS_ON("appears_on"),
    COMPILATION("compilation");

    private final String name;

    IncludeGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
