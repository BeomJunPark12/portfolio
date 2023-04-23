package com.myportfolio.web.dao;

import com.myportfolio.web.domain.UserDto;

public interface UserDao {

    int insertUser(UserDto userDto) throws Exception;
}
