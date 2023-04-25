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
        String name = userDto.getName();
        String email = userDto.getEmail();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");

        if (id == null || id.length() < 3 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[] {"3", "12"}, null);
        }

        if (password == null || password.length() < 3 || password.length() > 12) {
            errors.rejectValue("password", "invalidLength", new String[] {"3", "12"}, null);
        }

        if (name == null || name == "") {
            errors.rejectValue("name", "invalidLength", new String[] {"1"}, null);
        }

        if (email == null || email == "") {
            errors.rejectValue("email", "invalidLength", new String[] {"1"}, null);
        }
    }
}
