package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.BoardDTO;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImp implements BoardDao {

    private static String namespace = "com.fastcampus.web3.dao.BoardMapper.";

    @Autowired
    SqlSession session;

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public BoardDTO select(Integer bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    }

    @Override
    public List<BoardDTO> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public int insert(BoardDTO boardDTO) throws Exception {
        return session.insert(namespace + "insert", boardDTO);
    }

    @Override
    public int delete(Integer bno) throws Exception {
        return session.delete(namespace + "delete", bno);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int update(BoardDTO boardDTO) throws Exception {
        return session.update(namespace + "update", boardDTO);
    }
}
