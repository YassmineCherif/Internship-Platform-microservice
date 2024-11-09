package com.app.userservice.config;


import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;


@Slf4j
public class KeycloakConfig {

    static Keycloak keycloak = null;

    public KeycloakConfig() {
    }

    public static Keycloak getInstance() {
        if (keycloak == null) {

           /* keycloak = KeycloakBuilder.builder()
                    .serverUrl("http://localhost:8080/")
                    .realm("InternshipsRealm")
                    .grantType(OAuth2Constants.PASSWORD)
                    .username("super-admin")
                    .password("superadmin")
                    .clientId("login-app")
                    .clientSecret("eggxHlAtD6PBkQeCGp7qgi4B3lPkwr9q")
                    .build();
        }*/
            try {
                keycloak = KeycloakBuilder.builder()
                        .serverUrl("http://keycloak:8080/")
                        .realm("InternshipsRealm")
                        .grantType(OAuth2Constants.PASSWORD)
                        .username("super")
                        .password("super")
                        .clientId("admin-cli")
                        .build();
            }catch(Exception e)
            {
                log.error("Error while creating keycloak instance : " + e.getMessage());
            }
           try{
                keycloak.tokenManager().getAccessToken();
           }
           catch(Exception e){
               log.error("Error while getting access token from keycloak : " + e.getMessage());
           }
        }
        return keycloak;

    }

}
