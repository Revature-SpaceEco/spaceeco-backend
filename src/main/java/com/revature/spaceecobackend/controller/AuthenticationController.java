package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.LoginDto;
import com.revature.spaceecobackend.model.AuthenticationRequest;
import com.revature.spaceecobackend.model.AuthenticationResponse;
import com.revature.spaceecobackend.model.CustomUserDetails;
import com.revature.spaceecobackend.service.CustomUserDetailsService;
import com.revature.spaceecobackend.service.MfaService;
import com.revature.spaceecobackend.util.JwtUtil;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/user/{id}/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin page";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationTokenAndLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final CustomUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        if (mfaService.verifyCode(authenticationRequest.getMfaCode(), userDetails.getSecret())) {

            final String jwt = JwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getId()));
        }

        return ResponseEntity.status(400).body("Authentication Failed");

    }

}