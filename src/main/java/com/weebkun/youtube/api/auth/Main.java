package com.weebkun.youtube.api.auth;

import com.weebkun.youtube.api.auth.server.Server;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        try {
            Server server = new Server("/test");
            OAuth2.authorize("679567228193-ackiub7jtemhpvu1ck21sa7b0p4ud7kc.apps.googleusercontent.com", new String[]{Scopes.READONLY}, "http%3A%2F%2Flocalhost%3A5000%2Fauth");
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
