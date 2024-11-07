package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.user.LoginDTO;
import com.lpsouti.admin.dto.user.UserAddDTO;
import com.lpsouti.admin.dto.user.UserEditDTO;
import com.lpsouti.admin.dto.user.UserPageDTO;
import com.lpsouti.admin.vo.user.LoginVO;
import com.lpsouti.admin.vo.user.UserVO;
import com.lpsouti.common.vo.PageVO;

import java.math.BigDecimal;

public interface UserService {

    void add(UserAddDTO userAddDTO);

    LoginVO login(LoginDTO loginDTO);

    Boolean exists();

    void edit(UserEditDTO userEditDTO);

    PageVO<UserVO> pageQuery(UserPageDTO userPageDTO);

    UserVO queryById(Long id);

    void editStatus(Long id, Byte status, Boolean offline);

    void delete(Long id, Boolean offline);

    void editBalance(Long id, BigDecimal balance, BigDecimal freeBalance);
}
