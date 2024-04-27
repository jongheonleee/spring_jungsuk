package com.fastcampus.web3.service;

import com.fastcampus.web3.dto.BoardDto;
import java.util.List;

public interface BoardService {
    int getCount() throws Exception;
    int remove(Integer bno) throws Exception;
    int write(BoardDto boardDTO) throws Exception;
    List<BoardDto> getList() throws Exception;
    BoardDto read(Integer bno) throws Exception;
    int modify(BoardDto boardDTO) throws Exception;

    List<BoardDto> getPage(Integer currPage, Integer pageSize) throws Exception;
    // int getSearchResultCnt(SearchCondition sc) throws Exception;
    // List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
