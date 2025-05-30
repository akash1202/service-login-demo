package com.ibm.service.login.api;

import com.ibm.service.login.model.LoginPayload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static com.ibm.service.login.api.EndPoints.ENDPOINT_LOGIN;

public interface LoginAPI {
    @PostMapping(value=ENDPOINT_LOGIN,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(LoginPayload loginPayload, Map<String,String> requestHeader);
}
