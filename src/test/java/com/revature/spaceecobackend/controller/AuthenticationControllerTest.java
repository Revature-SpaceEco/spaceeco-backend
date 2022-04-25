package com.revature.spaceecobackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.spaceecobackend.model.AuthenticationRequest;
import com.revature.spaceecobackend.model.AuthenticationResponse;
import com.revature.spaceecobackend.model.CustomUserDetails;
import com.revature.spaceecobackend.service.CustomUserDetailsService;
import com.revature.spaceecobackend.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Test
    void positiveTest_createAuthenticationToken() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("admin1", "password");
        String requestJson = (new ObjectMapper()).writeValueAsString(request);

        String response = mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).
                content(requestJson)).andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        AuthenticationResponse authResponse = new ObjectMapper().readValue(response, AuthenticationResponse.class);
        String actual = JwtUtil.extractUsername(authResponse.getJwt());

        Assertions.assertEquals(request.getUsername(), actual);
    }

}
