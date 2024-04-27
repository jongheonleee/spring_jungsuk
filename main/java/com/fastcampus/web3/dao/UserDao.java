package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.UserDto;
import java.util.List;

public interface UserDao {

    int count() throws Exception;
    UserDto select(String id) throws Exception;
    List<UserDto> selectAll() throws Exception;
    int delete(String id) throws Exception;
    int insert(UserDto userDto) throws Exception;
    int update(UserDto userDto) throws Exception;
    int deleteAll() throws Exception;

}
