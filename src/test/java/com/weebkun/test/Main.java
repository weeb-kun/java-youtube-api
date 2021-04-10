package com.weebkun.test;

import com.weebkun.api.auth.OAuth2;
import com.weebkun.api.auth.Scopes;
import junit.framework.TestCase;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class Main extends TestCase {

    @Test
    public void main() {
        try {
            OAuth2.authenticate("679567228193-ackiub7jtemhpvu1ck21sa7b0p4ud7kc.apps.googleusercontent.com", System.getenv("youtube-api-secret") , new String[]{Scopes.READONLY});
            System.out.println(OAuth2.getAccessToken());
            assertNotNull(OAuth2.getAccessToken());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
