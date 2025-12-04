package istad.co.Homework.dto.Client;

import lombok.Data;

@Data
public class ClientCredentialsRequest extends CreatedClient {
    private Integer accessTokenTTLHours = 8;
    private Boolean reuseRefreshTokens = true;
}