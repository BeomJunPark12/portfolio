package com.myportfolio.web.service;

import com.myportfolio.web.dao.UserDao;
import com.myportfolio.web.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public int saveUser(UserDto userDto) throws Exception {
        return userDao.insertUser(userDto);
    }
}
