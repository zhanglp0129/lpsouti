package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import com.lpsouti.admin.mapper.LoginRecordMapper;
import com.lpsouti.admin.service.LoginRecordService;
import com.lpsouti.common.entity.LoginRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginRecordServiceImpl implements LoginRecordService {

    private final LoginRecordMapper loginRecordMapper;

    @Override
    public void edit(LoginRecordEditDTO dto) {
        // 创建修改实体
        LoginRecord loginRecord = new LoginRecord();
        // 属性拷贝
        BeanUtil.copyProperties(dto, loginRecord);

        // 修改数据
        loginRecordMapper.updateById(loginRecord);
    }
}
