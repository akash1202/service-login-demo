package com.ibm.service.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class LoginResponse implements Serializable {

        private static final long serialVersionUID= 1223591075778321345L;
        private String uuid;
        private String status;
        private String message;
        private String journey_session_data;

}
