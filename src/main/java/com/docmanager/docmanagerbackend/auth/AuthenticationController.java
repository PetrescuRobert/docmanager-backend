package com.docmanager.docmanagerbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/api/register")
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

    //i want to write a controller that handle the login request
    //and return a response entity with the token
    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        //i want to check if the service will return
        // if is null, i want to return a response entity with a error mesaage
        // if is not null, i want to return a response entity with the token
        AuthenticationResponse response = authenticationService.authenticate(request);
        if (response.getToken() == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        else
            return ResponseEntity.ok(response);

    }

}
