package com.fastcampus.web3.dao;


import com.fastcampus.web3.dto.CommentDTO;
import java.rmi.server.ExportException;
import java.util.List;

public interface CommentDao {

    int countAll() throws Exception;
    int count(Integer bno) throws Exception;
    CommentDTO select(CommentDTO commentDTO) throws Exception;
    List<CommentDTO> selectAll(Integer bno) throws Exception;
    int insert(CommentDTO commentDTO) throws Exception;
    int update(CommentDTO commentDTO) throws Exception;

    int delete(CommentDTO commentDTO) throws Exception;

    int deleteAll(Integer bno) throws Exception;
}
