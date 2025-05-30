package com.ibm.service.login.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$";
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        pattern= Pattern.compile(PASSWORD_PATTERN);
        matcher=pattern.matcher(value);
        return matcher.matches();
    }

}
