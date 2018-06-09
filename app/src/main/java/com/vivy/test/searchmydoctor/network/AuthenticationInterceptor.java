package com.vivy.test.searchmydoctor.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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