package com.fastcampus.web3.dto;

public class TmpUserDTO {

    private String id;
    private String user_name;
    private String user_pwd;

    public TmpUserDTO() {};

    public TmpUserDTO(String id, String user_name, String user_pwd) {
        this.id = id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public String toString() {
        return "id : " + id + ", name : " + user_name + ", pwd : " + user_pwd;
    }


}

