package com.fastcampus.web3.service;

import com.fastcampus.web3.dto.UserDto;
import java.util.List;

public interface UserService {
    int getCount() throws Exception;
    UserDto find(String id) throws Exception;
    List<UserDto> findAll() throws Exception;
    int remove(String id) throws Exception;
    int register(UserDto userDTO) throws Exception;
    int update(UserDto userDTO) throws Exception;
    int removeAll() throws Exception;
}
