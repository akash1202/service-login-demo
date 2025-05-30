package com.ibm.service.login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class LoginPayload implements Serializable {
    private static final long serialVersionUID= 4037483987306593810L;
    @NotBlank
    private String locale= "en-us";
    @NotBlank(message = "signinId can't be blank")
    private String signinId;
    @NotBlank(message = "password can't be blank")
    private String password;
}
