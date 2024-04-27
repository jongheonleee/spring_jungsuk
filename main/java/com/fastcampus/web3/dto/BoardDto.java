package com.fastcampus.web3.dto;

import java.util.Date;

public class BoardDto {

    private int bno; // 번호
    private String title; // 글제목
    private String writer; // 글쓴이
    private String content; // 글내용
    private int view_cnt; // 뷰카운트
    private int comment_cnt; // 댓글카운트
    private Date reg_date; // 등록일
    private Date up_date; // 수정일

    public BoardDto() {}

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public BoardDto(int bno, String title, String content, String writer, int view_cnt, int comment_cnt, Date reg_date, Date up_date) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view_cnt = view_cnt;
        this.comment_cnt = comment_cnt;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }


    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }
}
