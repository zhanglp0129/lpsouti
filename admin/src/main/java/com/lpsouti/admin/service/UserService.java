package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.LoginDTO;
import com.lpsouti.admin.dto.UserAddDTO;
import com.lpsouti.admin.vo.LoginVO;

public interface UserService {

    void add(UserAddDTO userAddDTO);

    LoginVO login(LoginDTO loginDTO);

    Boolean exists();
}
