package com.fastcampus.web3.dto;

import java.util.Date;

public class CommentDTO {
    private int cno;
    private int bno;
    private String writer;
    private String content;
    private Date reg_date;
    private Date up_date;

    public CommentDTO() {}
    public CommentDTO(int cno, int bno, String writer, String content, Date reg_date, Date up_date) {
        this.cno = cno;
        this.bno = bno;
        this.writer = writer;
        this.content = content;
        this.reg_date = reg_date;
        this.up_date = up_date;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
