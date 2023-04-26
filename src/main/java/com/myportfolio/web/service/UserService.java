package com.myportfolio.web.service;

import com.myportfolio.web.domain.UserDto;

public interface UserService {

    int saveUser(UserDto userDto) throws Exception;
    UserDto findUser(String id) throws Exception;
}
