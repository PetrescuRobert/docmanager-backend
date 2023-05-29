package com.docmanager.docmanagerbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ){
        // todo
        AuthenticationResponse response = authenticationService.register(request);
        return response.getToken() != null ?
                ResponseEntity.ok(response)
                :
                ResponseEntity.status(HttpStatus.CONFLICT).body("User with same email already exists");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        // todo
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
