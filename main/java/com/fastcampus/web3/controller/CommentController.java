package com.fastcampus.web3.controller;

import com.fastcampus.web3.service.CommentService;
import com.fastcampus.web3.service.CommentServiceImp;
import org.checkerframework.checker.builder.qual.ReturnsReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    // 0. 모든 댓글을 보여줌
    @GetMapping("/read")
    public String getAllComments() {
        // 해당 게시글 관련 댓글
        return "";
    }


    // 1. 지정된 번호의 댓글을 보여줌
    @GetMapping("/read")
    public String getComment(Integer cno) {

        return "";
    }

    // 2. 새로운 댓글을 저장함
    @PostMapping("/write")
    public String write() {

        return "";
    }

    // 3. 지정된 번호의 댓글을 삭제
    @GetMapping("/remove")
    public String remove() {

        return "";
    }

    // 4. 수정된 댓글을 저장함
    @PostMapping("/modify")
    public String modify() {

        return "";
    }



}
