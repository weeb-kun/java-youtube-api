package com.weebkun.api;

import com.squareup.moshi.Moshi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * main client for sending requests
 */
public class Client {
    public static String accessToken;
    public static String refreshToken;
    public static String root = "https://www.googleapis.com/youtube/v3";
    public static Moshi moshi = new Moshi.Builder().build();

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
            chain -> chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + accessToken).build())
    ).build();

    /**
     * gets the main Okhttp client.
     * it will automatically add the authorization header to all requests sent by this client.
     * @return the Okhttp client.
     */
    public static OkHttpClient getClient() {
        return client;
    }
}
