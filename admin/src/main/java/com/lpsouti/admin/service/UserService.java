package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.UserAddDTO;
import org.springframework.stereotype.Service;

public interface UserService {

    void add(UserAddDTO userAddDTO);
}
