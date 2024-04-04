package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.UserDTO;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

    private static String namespace = "com.fastcampus.web3.dao.userMapper.";
    @Autowired
    private SqlSession session;


    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public UserDTO select(String id) throws Exception {
        return session.selectOne(namespace + "select", id);
    }

    @Override
    public List<UserDTO> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public int delete(String id) throws Exception {
        return session.delete(namespace + "delete", id);
    }

    @Override
    public int insert(UserDTO userDto) throws Exception {
        return session.insert(namespace + "insert", userDto);
    }

    @Override
    public int update(UserDTO userDto) throws Exception {
        return session.update(namespace + "update", userDto);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }
}
