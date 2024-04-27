package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.BoardDto;
import java.util.List;
import java.util.Map;

// 1차 기본 기능만 테스트, 2차는 그 이후
public interface BoardDao {
    int count() throws Exception;
    BoardDto select(Integer bno) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    int insert(BoardDto boardDTO) throws Exception;
    int delete(Integer bno) throws Exception;
    int deleteAll() throws Exception;
    int update(BoardDto boardDTO) throws Exception;
    int incrementViewCnt(Integer bno) throws Exception;

    int updateCommentCnt(Map map) throws Exception;
    // offSet(), pageSize
    List<BoardDto> selectPage(Map map) throws Exception;

}
