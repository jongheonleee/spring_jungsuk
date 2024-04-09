package com.fastcampus.web3.service;


import com.fastcampus.web3.dao.BoardDao;
import com.fastcampus.web3.dao.CommentDao;
import com.fastcampus.web3.dto.CommentDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }


    // 📌개선사항
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String writer) throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCno(cno);
        commentDTO.setBno(bno);
        commentDTO.setWriter(writer);

        Map<String, Integer> map = new HashMap<>();
        map.put("cno", cno);
        map.put("cnt", -1);

        boardDao.updateCommentCnt(map);
        return commentDao.delete(commentDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDTO commentDTO) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("bno", commentDTO.getCno());
        map.put("cnt", 1);
        boardDao.updateCommentCnt(map);
        return commentDao.insert(commentDTO);
    }

    @Override
    public List<CommentDTO> getList(Integer bno) throws Exception {
        return commentDao.selectAll(bno);
    }

    // 📌개선사항
    @Override
    public CommentDTO read(Integer cno) throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCno(cno);
        return commentDao.select(commentDTO);
    }

    @Override
    public int modify(CommentDTO commentDTO) throws Exception {
        return commentDao.update(commentDTO);
    }

}
