package com.ibm.service.login.service;

import com.ibm.service.login.data.User;
import com.ibm.service.login.data.UserRepository;
import com.ibm.service.login.model.ErrorResponse;
import com.ibm.service.login.model.LoginPayload;
import com.ibm.service.login.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

   public ResponseEntity loginUser(LoginPayload loginPayload, Map<String,String> requestHeader){
       User user= userRepository.findUserByUserNamePassword(loginPayload.getSigninId(),loginPayload.getPassword());
       if(user!=null) {
           return ResponseEntity.ok().body(new LoginResponse(user.getId(),"login successful", "welcome " + user.getUsername(), "{last_login_timestamp: "+new Date()+", channel_req_id:"+requestHeader.get("channels-request-id")+"}"));
       }
           List errors=new ArrayList();
           errors.add("wrong User id or password");
           return ResponseEntity.badRequest().body(new ErrorResponse("login failed",errors));
   }
}
