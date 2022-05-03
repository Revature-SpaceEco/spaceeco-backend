package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.model.AuthenticationRequest;
import com.revature.spaceecobackend.model.AuthenticationResponse;
import com.revature.spaceecobackend.model.CustomUserDetails;
import com.revature.spaceecobackend.service.CustomUserDetailsService;
import com.revature.spaceecobackend.service.MfaService;
import com.revature.spaceecobackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private MfaService mfaService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationTokenAndLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final CustomUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        if (mfaService.verifyCode(userDetails.getSecret(), authenticationRequest.getMfaCode())) {

            final String jwt = JwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getId()));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthenticationResponse());

    }

}
