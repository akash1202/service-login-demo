package com.ibm.service.login.api;

import com.ibm.service.login.model.RegisterPayload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface RegistrationAPI {

    @PostMapping(value =EndPoints.ENDPOINT_REGISTER,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@Valid @RequestBody RegisterPayload registerPayload);
}
