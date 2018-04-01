package com.aliaksandr.shynkevich.webserviceboot.controller;

import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Date: 2018-03-21
 *
 * @author Aliaksandr Shynkevich
 */
@RestController
@RequestMapping(value = "/oauth")
public class GoogleRestController {

    private static final String CLIENT_ID = "795701656531-k3dj6g72apole19khin2jktjbapoua12.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "5N_naxirxrGmOWm5oI8UHmsZ";
    private static final String REDIRECT_URI = "http://localhost:9999/oauth/auth";

    @GetMapping("/google-auth")
    public RedirectView redirectLoginProvider() {
        GoogleAuthorizationCodeRequestUrl requestUrl = new GoogleAuthorizationCodeRequestUrl(
                CLIENT_ID,
                REDIRECT_URI,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.email",
                        "https://www.googleapis.com/auth/userinfo.profile"));
        return new RedirectView(requestUrl.build());
    }

    @GetMapping("/auth")
    public Userinfoplus authentication(@RequestParam("code") String code) throws IOException {
        GoogleAuthorizationCodeTokenRequest tokenRequest =
                new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(),
                        new JacksonFactory(), CLIENT_ID,
                        CLIENT_SECRET, code, REDIRECT_URI);
        GoogleTokenResponse response = tokenRequest.execute();
//        new ApacheHttpTransport.Builder().trustCertificates()
        GoogleCredential credential = new GoogleCredential().setAccessToken(response.getAccessToken());
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setSuppressAllChecks(true).build();
        Userinfoplus execute = oauth2.userinfo().get().execute();
        return execute;
    }
}