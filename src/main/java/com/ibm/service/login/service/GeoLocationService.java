package com.ibm.service.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.service.login.data.User;
import com.ibm.service.login.data.UserRepository;
import com.ibm.service.login.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GeoLocationService {

    @Autowired
    UserRepository userRepository;
    private static boolean isValidLocation=false;
    private static ResponseIPAPI responseIPAPI=null;
    private static String URL_IP_API="http://ip-api.com/json/";
    public boolean isValidLocation(String ip){
        String result= new RestTemplate().getForObject(URL_IP_API+ip,String.class);

        try{
            responseIPAPI=new ObjectMapper().readValue(result,ResponseIPAPI.class);
            if (responseIPAPI.getCountryCode().equalsIgnoreCase("CA")) isValidLocation=true;
            else isValidLocation=false;
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return isValidLocation;
    }

    public ResponseEntity checkValidLocation(RegisterPayload registerPayload){
        List<String> errors = new ArrayList<>();
        if(!isValidLocation(registerPayload.getIpAddress())){
            errors.add("User can register only in canada!");
            ErrorResponse errorResponse=new ErrorResponse("User is not eligible to register", errors);
            return ResponseEntity.ok().body(errorResponse);
        }else{
            User user=userRepository.findUserByUserName(registerPayload.getUsername());
            if(user!=null) {
                return ResponseEntity.ok().body(new RegisterResponse(user.getId(), "welcome " + registerPayload.getUsername(), getCityName()));
            } else {
                user = new User(UUID.randomUUID().toString(), registerPayload.getUsername(), registerPayload.getPassword());
                userRepository.save(user);
                return ResponseEntity.ok().body(new RegisterResponse(user.getId(), "welcome " + registerPayload.getUsername(), getCityName()));
            }
        }
    }

    public static String getCityName(){
            if (isValidLocation&&responseIPAPI.getCity()!=null)
                return responseIPAPI.getCity();
        return null;
    }

}
