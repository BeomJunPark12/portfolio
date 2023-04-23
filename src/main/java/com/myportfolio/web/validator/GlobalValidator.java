package com.myportfolio.web.validator;

import com.myportfolio.web.domain.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDto userDto = (UserDto) target;

        String id = userDto.getId();
        String password = userDto.getPassword();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");

        if (id == null || id.length() <= 3 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[] {"3", "12"}, null);
        }

        if (password == null || password.length() <= 5 || password.length() > 12) {
            errors.rejectValue("password", "invalidLength", new String[] {"5", "12"}, null);
        }

    }
}
