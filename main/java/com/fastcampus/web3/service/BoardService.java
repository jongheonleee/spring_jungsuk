package com.fastcampus.web3.service;

import com.fastcampus.web3.dto.BoardDTO;
import java.util.List;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

public interface BoardService {
    int getCount() throws Exception;
    int remove(Integer bno) throws Exception;
    int write(BoardDTO boardDTO) throws Exception;
    List<BoardDTO> getList() throws Exception;
    BoardDTO read(Integer bno) throws Exception;
    int modify(BoardDTO boardDTO) throws Exception;

    List<BoardDTO> getPage(Integer currPage, Integer pageSize) throws Exception;
    // int getSearchResultCnt(SearchCondition sc) throws Exception;
    // List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
