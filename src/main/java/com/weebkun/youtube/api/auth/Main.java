package com.weebkun.youtube.api.auth;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        try {
            OAuth2.authorize("679567228193-ackiub7jtemhpvu1ck21sa7b0p4ud7kc.apps.googleusercontent.com", System.getenv("youtube-api-secret") , new String[]{Scopes.READONLY});
            System.out.println(OAuth2.getAccessToken());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
