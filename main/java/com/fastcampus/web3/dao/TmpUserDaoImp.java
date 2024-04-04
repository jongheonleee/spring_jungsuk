package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.TmpUserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TmpUserDaoImp implements TmpUserDao {

    private static String namespace = "com.fastcampus.web3.dao.TmpUserMapper.";
    @Autowired
    private SqlSession session;


    public TmpUserDaoImp() {}

    @Override
    public TmpUserDTO selectUser(String id) {
        return session.selectOne(namespace + "select", id);
    }

}
