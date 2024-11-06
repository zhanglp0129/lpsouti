package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.user.LoginDTO;
import com.lpsouti.admin.dto.user.UserAddDTO;
import com.lpsouti.admin.dto.user.UserEditDTO;
import com.lpsouti.admin.vo.user.LoginVO;

public interface UserService {

    void add(UserAddDTO userAddDTO);

    LoginVO login(LoginDTO loginDTO);

    Boolean exists();

    void edit(UserEditDTO userEditDTO);
}
