package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.BoardDao;
import com.fastcampus.web3.dto.BoardDTO;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImp implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    @Override
    public int remove(Integer bno) throws Exception {
        return boardDao.delete(bno);
    }

    @Override
    public int write(BoardDTO boardDTO) throws Exception {
        return boardDao.insert(boardDTO);
    }

    @Override
    public List<BoardDTO> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public BoardDTO read(Integer bno) throws Exception {
        int rowCnt = boardDao.incrementViewCnt(bno);
        return boardDao.select(bno);
    }

    @Override
    public int modify(BoardDTO boardDTO) throws Exception {
        return boardDao.update(boardDTO);
    }

    @Override
    public List<BoardDTO> getPage(Integer currPage, Integer pageSize) throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("offset", (currPage-1)*pageSize);
        map.put("pageSize", pageSize);
        return boardDao.selectPage(map);
    }
}
