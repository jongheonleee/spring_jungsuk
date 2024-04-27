package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.TmpUserDto;

public interface TmpUserDao {
    TmpUserDto selectUser(String id);
}
