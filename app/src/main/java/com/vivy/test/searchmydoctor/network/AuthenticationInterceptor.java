package com.vivy.test.searchmydoctor.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;
    private String type;

    public AuthenticationInterceptor(String type, String token) {
        this.authToken = token;
        this.type = type;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", type + " " + authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}