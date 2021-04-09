package com.weebkun.youtube.api.auth;

import com.weebkun.youtube.api.utils.exceptions.UnableToOpenBrowserException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * class for authenticating to the youtube api using OAuth 2.0.
 */
public class OAuth2 {

    /**
     * requests the user for authorization through OAuth2.
     * @param clientId the clientId of the oauth token to use. see <a href="https://developers.google.com/youtube/v3/getting-started">the OAuth 2.0 page</a> for more info.
     * @param scopes the list of {@link Scopes} to use
     * @param redirectUri the redirect uri provided for the oauth to redirect to. should be specified in the credentials page in your developer console.
     * @see Scopes for more info
     */
    public static void authorize(String clientId, String[] scopes, String redirectUri) throws NoSuchAlgorithmException, IOException {
        // create code verifier and challenge
        SecureRandom random = new SecureRandom();
        byte[] verifier = new byte[32];
        random.nextBytes(verifier);
        // code verifier
        String codeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(verifier);
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
                "code_challenge_method=S256", clientId, redirectUri, String.join(" ", scopes), codeChallenge);

        String os = System.getProperty("os.name").toLowerCase();

        // open browser
        try {
            if(Desktop.isDesktopSupported()) {
                // windows
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // unix or mac
                Runtime runtime = Runtime.getRuntime();
                if(os.contains("mac")) {
                    // mac
                    runtime.exec("open " + url);
                } else if(os.contains("nix") || os.contains("nux")) {
                    // linux or unix runtime
                    runtime.exec("xdg-open " + url);
                } else {
                    // os could not be found/ cannot launch browser
                    throw new UnableToOpenBrowserException();
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
