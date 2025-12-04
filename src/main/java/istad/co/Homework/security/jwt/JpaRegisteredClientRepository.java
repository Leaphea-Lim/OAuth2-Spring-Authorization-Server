//package istad.co.Homework.security.repository;
//
//import auth.res_server.demo.domain.Client;
//import auth.res_server.demo.repository.ClientRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JpaRegisteredClientRepository implements RegisteredClientRepository {
//
//    private final ClientRepository clientRepository;
//
//    @Override
//    @Transactional
//    public void save(RegisteredClient registeredClient) {
//        log.debug("Saving registered client: {}", registeredClient.getClientId());
//
//        Optional<Client> existingClient = clientRepository.findByClientId(registeredClient.getClientId());
//        Client client = existingClient.orElse(new Client());
//
//        // Update client properties
//        updateClientFromRegisteredClient(client, registeredClient);
//
//        clientRepository.save(client);
//        log.info("Client saved successfully: {}", registeredClient.getClientId());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public RegisteredClient findById(String id) {
//        log.debug("Finding client by ID: {}", id);
//        return clientRepository.findById(id)
//                .map(this::convertToRegisteredClient)
//                .orElse(null);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public RegisteredClient findByClientId(String clientId) {
//        log.debug("Finding client by clientId: {}", clientId);
//        return clientRepository.findByClientId(clientId)
//                .map(this::convertToRegisteredClient)
//                .orElse(null);
//    }
//
//    private void updateClientFromRegisteredClient(Client client, RegisteredClient registeredClient) {
//        client.setClientId(registeredClient.getClientId());
//        client.setClientSecret(registeredClient.getClientSecret());
//        client.setClientName(registeredClient.getClientName());
//
//        // Clear and set authentication methods
//        client.getClientAuthenticationMethods().clear();
//        registeredClient.getClientAuthenticationMethods()
//                .forEach(method -> client.getClientAuthenticationMethods().add(method.getValue()));
//
//        // Clear and set grant types
//        client.getAuthorizationGrantTypes().clear();
//        registeredClient.getAuthorizationGrantTypes()
//                .forEach(grant -> client.getAuthorizationGrantTypes().add(grant.getValue()));
//
//        // Clear and set scopes
//        client.getScopes().clear();
//        client.getScopes().addAll(registeredClient.getScopes());
//
//        // Clear and set redirect URIs
//        client.getRedirectUris().clear();
//        client.getRedirectUris().addAll(registeredClient.getRedirectUris());
//
//        // Set PKCE requirement
//        client.setRequireProofKey(registeredClient.getClientSettings().isRequireProofKey());
//
//        // Set token TTLs
//        client.setAccessTokenTtl(registeredClient.getTokenSettings()
//                .getAccessTokenTimeToLive().getSeconds());
//
//        if (registeredClient.getTokenSettings().getRefreshTokenTimeToLive() != null) {
//            client.setRefreshTokenTtl(registeredClient.getTokenSettings()
//                    .getRefreshTokenTimeToLive().getSeconds());
//        }
//
//        // Set refresh token reuse
//        client.setReuseRefreshTokens(registeredClient.getTokenSettings().isReuseRefreshTokens());
//
//        client.setUpdatedAt(Instant.now());
//
//        if (!clientRepository.existsByClientId(client.getClientId())) {
//            client.setCreatedAt(Instant.now());
//        }
//    }
//
//    private RegisteredClient convertToRegisteredClient(Client client) {
//        RegisteredClient.Builder builder = RegisteredClient.withId(client.getClientId())
//                .clientId(client.getClientId())
//                .clientSecret(client.getClientSecret())
//                .clientName(client.getClientName());
//
//        // Add authentication methods
//        client.getClientAuthenticationMethods().forEach(method ->
//                builder.clientAuthenticationMethod(new ClientAuthenticationMethod(method))
//        );
//
//        // Add grant types
//        client.getAuthorizationGrantTypes().forEach(grant ->
//                builder.authorizationGrantType(new AuthorizationGrantType(grant))
//        );
//
//        // Add scopes
//        client.getScopes().forEach(builder::scope);
//
//        // Add redirect URIs
//        client.getRedirectUris().forEach(builder::redirectUri);
//
//        // Configure client settings
//        ClientSettings clientSettings = ClientSettings.builder()
//                .requireProofKey(client.isRequireProofKey())
//                .build();
//
//        // Configure token settings
//        TokenSettings.Builder tokenSettingsBuilder = TokenSettings.builder()
//                .accessTokenTimeToLive(Duration.ofSeconds(
//                        Optional.ofNullable(client.getAccessTokenTtl()).orElse(1800L)))
//                .reuseRefreshTokens(client.isReuseRefreshTokens());
//
//        if (client.getRefreshTokenTtl() != null) {
//            tokenSettingsBuilder.refreshTokenTimeToLive(Duration.ofSeconds(client.getRefreshTokenTtl()));
//        }
//
//        TokenSettings tokenSettings = tokenSettingsBuilder.build();
//
//        return builder
//                .clientSettings(clientSettings)
//                .tokenSettings(tokenSettings)
//                .build();
//    }
//}