package com.ibm.service.login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class ErrorResponse implements Serializable{

    private static final long serialVersionUID= 1223391075778321342L;
    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

}
