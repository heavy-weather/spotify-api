package com.anthonyguidotti.spotify_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Authentication authentication;

    public TestController(Authentication authentication) {
        this.authentication = authentication;
    }

    @GetMapping(value = "/")
    public String home(
            @RequestParam String code
    ) {
        authentication.setAuthorizationCode(code);
        return code;
    }
}
