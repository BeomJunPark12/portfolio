package com.myportfolio.web.dao;

import com.myportfolio.web.domain.CommentDto;

import java.util.List;
import java.util.Map;

public interface CommentDao {
    int deleteAll(Integer bno) throws Exception;

    int delete(Integer cno, String commenter) throws Exception;

    int count(Integer bno) throws Exception;

    int insert(CommentDto commentDto) throws Exception;

    List<CommentDto> selectAll(Integer bno) throws Exception;

    CommentDto select(Integer cno) throws Exception;

    int update(CommentDto commentDto) throws Exception;



}
