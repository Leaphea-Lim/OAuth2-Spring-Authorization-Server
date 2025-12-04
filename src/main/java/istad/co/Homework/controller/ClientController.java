package istad.co.Homework.controller;

import istad.co.Homework.dto.Client.AuthorizationCodeRequest;
import istad.co.Homework.dto.Client.ClientCredentialsRequest;
import istad.co.Homework.dto.Client.ClientResponse;
import istad.co.Homework.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //todo: Create PKCE Client
    @PostMapping("/pkce")
    public ResponseEntity<ClientResponse> createPKCEClient(
            @Valid @RequestBody AuthorizationCodeRequest request) {
        request.setAuthCodeType("pkce");
        // Force PKCE to happen
        request.setRequireProofKey(true);
        ClientResponse response = clientService.createAuthorizationCodeClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //todo: Create Normal Authorization Code Client
    @PostMapping("/normal-auth")
    public ResponseEntity<ClientResponse> createNormalAuthClient(
            @Valid @RequestBody AuthorizationCodeRequest request) {
        request.setAuthCodeType("normal");
        ClientResponse response = clientService.createAuthorizationCodeClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //todo: Create Client Credentials Client
    @PostMapping("/client-credentials")
    public ResponseEntity<ClientResponse> createClientCredentialsClient(
            @Valid @RequestBody ClientCredentialsRequest request) {
        ClientResponse response = clientService.createClientCredentialsClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}