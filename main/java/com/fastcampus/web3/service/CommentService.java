package com.fastcampus.web3.service;

import com.fastcampus.web3.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    int getCount(Integer bno) throws Exception;
    int remove(Integer cno, Integer bno, String writer) throws Exception;
    int write(CommentDTO commentDTO) throws Exception;
    List<CommentDTO> getList(Integer bno) throws Exception;

    CommentDTO read(Integer cno) throws Exception;
    int modify(CommentDTO commentDTO) throws Exception;

}
