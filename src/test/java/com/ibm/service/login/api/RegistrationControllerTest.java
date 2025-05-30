package com.ibm.service.login.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.service.login.model.RegisterPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testUsernameBadRequest() throws Exception {
        RegisterPayload request = new RegisterPayload();
        request.setUsername("");
        request.setPassword("Akas@123");
        request.setIpAddress("142.118.214.165");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assertTrue(response.contains("username must not be blank"));
                });
    }

    @Test
    void testPasswordBadRequest() throws Exception {
        RegisterPayload request = new RegisterPayload();
        request.setUsername("akash");
        request.setPassword("");
        request.setIpAddress("142.118.214.165");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assertTrue(response.contains("password must not be blank"));
                });
    }
@Test
    void testSuccessfulRegistration() throws Exception {
        RegisterPayload request = new RegisterPayload();
        request.setUsername("akash");
        request.setPassword("Akas@123");
        request.setIpAddress("142.118.214.165");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assertTrue(response.contains("welcome"));
                });
    }

}