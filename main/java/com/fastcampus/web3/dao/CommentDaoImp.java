package com.fastcampus.web3.dao;


import com.fastcampus.web3.dto.CommentDTO;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImp implements CommentDao {

    private static String namespace = "com.fastcampus.web3.dao.CommentMapper.";

    @Autowired
    SqlSession session;
    @Override
    public int countAll() throws Exception {
        return session.selectOne(namespace + "countAll");
    }

    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(namespace + "count", bno);
    }

    @Override
    public CommentDTO select(CommentDTO commentDTO) throws Exception {
        return session.selectOne(namespace + "select", commentDTO);
    }

    @Override
    public List<CommentDTO> selectAll(Integer bno) throws Exception {
        return session.selectList(namespace + "selectAll", bno);
    }

    @Override
    public int insert(CommentDTO commentDTO) throws Exception {
        return session.insert(namespace + "insert", commentDTO);
    }

    @Override
    public int update(CommentDTO commentDTO) throws Exception {
        return session.update(namespace + "update", commentDTO);
    }

    @Override
    public int delete(CommentDTO commentDTO) throws Exception {
        return session.delete(namespace + "delete", commentDTO);
    }

    @Override
    public int deleteAll(Integer bno) throws Exception {
        return session.delete(namespace + "deleteAll", bno);
    }
}
