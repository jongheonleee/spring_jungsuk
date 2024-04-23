package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.UserDao;
import com.fastcampus.web3.dto.UserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public int getCount() throws Exception {
        return dao.count();
    }

    @Override
    public UserDTO find(String id) throws Exception {
        return dao.select(id);
    }

    @Override
    public List<UserDTO> findAll() throws Exception {
        return dao.selectAll();
    }

    @Override
    public int remove(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public int register(UserDTO userDTO) throws Exception {
        return dao.insert(userDTO);
    }

    @Override
    public int update(UserDTO userDTO) throws Exception {
        return dao.update(userDTO);
    }

    @Override
    public int removeAll() throws Exception {
        return dao.deleteAll();
    }
}
