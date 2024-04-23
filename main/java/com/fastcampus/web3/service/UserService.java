package com.fastcampus.web3.service;

import com.fastcampus.web3.dto.UserDTO;
import java.util.List;

public interface UserService {
    int getCount() throws Exception;
    UserDTO find(String id) throws Exception;
    List<UserDTO> findAll() throws Exception;
    int remove(String id) throws Exception;
    int register(UserDTO userDTO) throws Exception;
    int update(UserDTO userDTO) throws Exception;
    int removeAll() throws Exception;
}
