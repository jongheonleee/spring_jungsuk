package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.TmpUserDTO;

public interface TmpUserDao {
    TmpUserDTO selectUser(String id);
}
