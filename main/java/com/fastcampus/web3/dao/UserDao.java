package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.UserDTO;
import java.util.List;

public interface UserDao {

    int count() throws Exception;
    UserDTO select(String id) throws Exception;
    List<UserDTO> selectAll() throws Exception;
    int delete(String id) throws Exception;
    int insert(UserDTO userDto) throws Exception;
    int update(UserDTO userDto) throws Exception;
    int deleteAll() throws Exception;

}
