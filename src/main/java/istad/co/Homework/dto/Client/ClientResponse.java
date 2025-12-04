package istad.co.Homework.dto.Client;

import lombok.Data;

@Data
public class ClientResponse {
    private String id;
    private String clientId;
    private String clientName;
    private String clientType; // "pkce", "normal", "client_credentials"
    private String message;
    private String tokenEndpoint = "/oauth2/token";
    private String authorizationEndpoint = "/oauth2/authorize";
    private String scopes;
}
