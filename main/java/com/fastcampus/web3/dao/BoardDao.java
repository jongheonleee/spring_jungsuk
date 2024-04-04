package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.BoardDTO;
import java.util.List;
import java.util.Map;

// 1차 기본 기능만 테스트, 2차는 그 이후
public interface BoardDao {
    int count() throws Exception;
    BoardDTO select(Integer bno) throws Exception;
    List<BoardDTO> selectAll() throws Exception;
    int insert(BoardDTO boardDTO) throws Exception;
    int delete(Integer bno) throws Exception;
    int deleteAll() throws Exception;
    int update(BoardDTO boardDTO) throws Exception;
    int incrementViewCnt(Integer bno) throws Exception;
    // offSet(), pageSize
    List<BoardDTO> selectPage(Map map) throws Exception;

}
