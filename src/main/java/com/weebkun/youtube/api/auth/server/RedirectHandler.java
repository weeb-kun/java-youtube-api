package com.weebkun.youtube.api.auth.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * http handler class that handles the loopback request from oauth 2.0.
 */
public class RedirectHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // get redirect
        if(exchange.getRequestMethod().equals("GET")) {
            String code = exchange.getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("&")[0];

            // get client id from secrets file
            

            if(code.contains("error")) {
                // error occurred while authorising request.
            } else {
                // exchange authorisation code for access token
                RequestBody body = RequestBody.create(String.format("{" +
                        "'client_id': '%s'" +
                        "'client_secret'" +
                        "}"), MediaType.get("application/json"));
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://oauth2.googleapis.com/token")
                        .post(body)
                        .build();
            }
        }

    }
}
