package com.fastcampus.web3.validator;

import com.fastcampus.web3.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 25;
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;
        String id = user.getId();
        String pwd = user.getPwd();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if (isInvalidId(id)) {
            errors.rejectValue("id", "invalidLength",
                    new String[] {String.valueOf(MIN_LENGTH), String.valueOf(MAX_LENGTH)},
                    "id의 길이는 반드시 3~25이여야합니다.");
        }

        if (isInvalidPwd(pwd)) {
            errors.rejectValue("pwd", "invalidLength",
                    new String[] {String.valueOf(MIN_LENGTH), String.valueOf(MAX_LENGTH)},
                    "pwd의 길이는 반드시 3~25이여야합니다.");

        }

    }

    private boolean isInvalidId(String id) {
        return id == null || id.length() < MIN_LENGTH || id.length() > MAX_LENGTH;
    }

    private boolean isInvalidPwd(String pwd) {
        return pwd == null || pwd.length() < MIN_LENGTH || pwd.length() > MAX_LENGTH;
    }


}
