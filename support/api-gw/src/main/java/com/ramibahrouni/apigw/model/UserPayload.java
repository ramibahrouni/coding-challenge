package com.ramibahrouni.apigw.model;

public record UserPayload(
    String username,
    String password,
    String role
) {}
