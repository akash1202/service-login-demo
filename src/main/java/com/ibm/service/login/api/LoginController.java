package com.ibm.service.login.api;

import com.ibm.service.login.model.LoginPayload;
import com.ibm.service.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/")
@RestController
public class LoginController implements LoginAPI{
    @Autowired
    LoginService loginService;

    @Override
    public ResponseEntity login(@Valid @RequestBody LoginPayload loginPayload,@RequestHeader Map<String,String> requestHeader) {
         return loginService.loginUser(loginPayload,requestHeader);
    }
}
