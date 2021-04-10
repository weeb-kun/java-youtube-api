package com.weebkun.api;

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

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
            chain -> chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + accessToken).build())
    ).build();
}
