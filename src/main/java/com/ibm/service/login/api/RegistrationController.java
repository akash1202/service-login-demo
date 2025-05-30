package com.ibm.service.login.api;

import com.ibm.service.login.model.RegisterPayload;
import com.ibm.service.login.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class RegistrationController implements RegistrationAPI{
    @Autowired
    GeoLocationService geoLocationService;

    @Override
    public ResponseEntity register(RegisterPayload registerPayload) {
        //  String ip="142.118.214.165";    //for hard cording we could pass any ip of canada
        //String ip="66.249.69.182";  //other ip
        return geoLocationService.checkValidLocation(registerPayload);
    }
}
