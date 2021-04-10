package com.weebkun.api.auth;

import com.squareup.moshi.Moshi;
import com.sun.net.httpserver.HttpServer;
import com.weebkun.api.Client;
import com.weebkun.api.net.TokenResponse;
import com.weebkun.api.utils.exceptions.UnableToOpenBrowserException;
import okhttp3.*;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

/**
 * class for authenticating to the youtube api using OAuth 2.0.
 */
public class OAuth2 {

    private static String clientId;
    private static String secret;
    private static String codeVerifier;
    private static String authCode;

    private static String accessToken;
    private static String refreshToken;
    private static final CountDownLatch latch = new CountDownLatch(1);

    /**
     * requests the user for authorization through OAuth2.
     * note that this method will block the main thread.
     * @param clientId the clientId of the oauth token to use. see <a href="https://developers.google.com/youtube/v3/getting-started">the OAuth 2.0 page</a> for more info.
     * @param clientSecret the client secret associated with the client id.
     * @param scopes the list of {@link Scopes} to use
     * @see Scopes
     */
    public static void authenticate(String clientId, String clientSecret, String[] scopes) throws NoSuchAlgorithmException {
        OAuth2.clientId = clientId;
        secret = clientSecret;
        // create code verifier and challenge
        SecureRandom random = new SecureRandom();
        byte[] verifier = new byte[32];
        random.nextBytes(verifier);
        // code verifier
        codeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(verifier);
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        // code challenge
        String codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);

        // build url
        String url = String.format("https://accounts.google.com/o/oauth2/v2/auth?" +
                "client_id=%s&" +
                "redirect_uri=%s&" +
                "response_type=code&" +
                "scope=%s&" +
                "code_challenge=%s&" +
                "code_challenge_method=S256", clientId, "http%3A//127.0.0.1%3A5000", String.join("%20", scopes), codeChallenge);

        String os = System.getProperty("os.name").toLowerCase();

        // open browser
        try {
            if(Desktop.isDesktopSupported()) {
                // windows
                Desktop.getDesktop().browse(new URI(url));
                getTokens();
            } else {
                // unix or mac
                Runtime runtime = Runtime.getRuntime();
                if(os.contains("mac")) {
                    // mac
                    runtime.exec("open " + url);
                    getTokens();
                } else if(os.contains("nix") || os.contains("nux")) {
                    // linux or unix runtime
                    runtime.exec("xdg-open " + url);
                    getTokens();
                } else {
                    // os could not be found/ cannot launch browser
                    throw new UnableToOpenBrowserException();
                }
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    // todo async authentication

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    private static void getTokens() {
        try {
            // create server
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 5000), 0);
            server.createContext("/", request -> {
                if (request.getRequestMethod().equals("GET")) {
                    // get auth code
                    authCode = request.getRequestURI().getQuery().split("&")[0].split("=")[1];
                    if (authCode != "access_denied") {
                        // got auth code
                        // exchange for tokens
                        RequestBody body = RequestBody.create(
                                String.format(
                                        "client_id=%s&" +
                                                "client_secret=%s&" +
                                                "code=%s&" +
                                                "code_verifier=%s&" +
                                                "redirect_uri=http://127.0.0.1%%3A5000&" +
                                                "grant_type=authorization_code", clientId, secret, authCode, codeVerifier), MediaType.get("application/x-www-form-urlencoded"));
                        Request req = new Request.Builder()
                                .url("https://oauth2.googleapis.com/token")
                                .post(body)
                                .build();
                        try (Response response = Client.getClient().newCall(req).execute()) {
                            System.out.println("sending request");
                            TokenResponse token = Client.moshi.adapter(TokenResponse.class).fromJson(response.body().source());
                            accessToken = token.access_token;
                            refreshToken = token.refresh_token;
                            Client.accessToken = token.access_token;
                            Client.refreshToken = token.refresh_token;
                            server.stop(10);
                            latch.countDown();
                        }
                    }
                }
            });
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
