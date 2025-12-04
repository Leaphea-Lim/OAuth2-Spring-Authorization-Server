package istad.co.Homework.dto.Client;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class AuthorizationCodeRequest extends CreatedClient {
    // Client Type: "pkce" or "normal"
    @NotBlank(message = "Auth code type is required (pkce or normal)")
    private String authCodeType = "pkce"; // Default to PKCE

    private List<String> redirectUris;
    private List<String> postLogoutRedirectUris;

    private Boolean requireAuthorizationConsent = true;
    private Boolean requireProofKey = null;
    private Integer accessTokenTTLMinutes = 30;
    private Integer refreshTokenTTLDays = 3;
    private Boolean reuseRefreshTokens = false;
}
