package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.UserDao;
import com.fastcampus.web3.dto.UserDto;
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
    public UserDto find(String id) throws Exception {
        return dao.select(id);
    }

    @Override
    public List<UserDto> findAll() throws Exception {
        return dao.selectAll();
    }

    @Override
    public int remove(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public int register(UserDto userDTO) throws Exception {
        return dao.insert(userDTO);
    }

    @Override
    public int update(UserDto userDTO) throws Exception {
        return dao.update(userDTO);
    }

    @Override
    public int removeAll() throws Exception {
        return dao.deleteAll();
    }
}
