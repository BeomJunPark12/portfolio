package com.myportfolio.web.dao;

import com.myportfolio.web.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SqlSession session;

    private static String namespace = "com.myportfolio.web.dao.UserMapper.";

    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return session.insert(namespace+"insertUser", userDto);
    }

    @Override
    public UserDto select(String id) throws Exception {
        return session.selectOne(namespace + "select", id);
    }
}
