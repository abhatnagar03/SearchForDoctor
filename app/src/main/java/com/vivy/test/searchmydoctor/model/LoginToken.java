package com.vivy.test.searchmydoctor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginToken implements Serializable {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("refresh_token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
