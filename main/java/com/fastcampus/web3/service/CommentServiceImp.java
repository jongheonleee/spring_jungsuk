package com.fastcampus.web3.service;


import com.fastcampus.web3.dao.BoardDao;
import com.fastcampus.web3.dao.CommentDao;
import com.fastcampus.web3.dto.CommentDTO;
import java.util.List;
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
//        boardDao.decreaseCommentCnt(); 해당 게시글 댓글 수 증가

        return commentDao.delete(commentDTO);
    }

    @Override
    public int write(CommentDTO commentDTO) throws Exception {
//        boardDao.incrementCommentCnt(commentDTO.getBno()); 해당 게시글 댓글 수 증가
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
