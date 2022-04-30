package com.revature.spaceecobackend.model;

public class AuthenticationResponse {

    private String jwt;
    private Integer userId;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String jwt, Integer userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public Integer getUserId() {
        return userId;
    }

}