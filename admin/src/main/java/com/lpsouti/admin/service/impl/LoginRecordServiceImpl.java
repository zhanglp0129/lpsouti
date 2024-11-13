package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import com.lpsouti.admin.dto.login_record.LoginRecordPageDTO;
import com.lpsouti.admin.mapper.LoginRecordMapper;
import com.lpsouti.admin.service.LoginRecordService;
import com.lpsouti.common.entity.LoginRecord;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.utils.LoginUtil;
import com.lpsouti.common.utils.RedisKeyUtil;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginRecordServiceImpl implements LoginRecordService {

    private final LoginRecordMapper loginRecordMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final LoginUtil loginUtil;

    @Override
    public void edit(LoginRecordEditDTO dto) {
        // 创建修改实体
        LoginRecord loginRecord = new LoginRecord();
        // 属性拷贝
        BeanUtil.copyProperties(dto, loginRecord);

        // 修改数据
        loginRecordMapper.updateById(loginRecord);
    }

    @Override
    public PageVO<LoginRecord> pageQuery(LoginRecordPageDTO dto) {
        // 构建分页
        IPage<LoginRecord> page = dto.toIPage();
        // 构建查询条件
        LambdaQueryWrapper<LoginRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getUserId() != null, LoginRecord::getUserId, dto.getUserId())
                .eq(StrUtil.isNotEmpty(dto.getToken()), LoginRecord::getToken, dto.getToken())
                .eq(StrUtil.isNotEmpty(dto.getIp()), LoginRecord::getIp, dto.getIp())
                .like(StrUtil.isNotEmpty(dto.getUserAgent()), LoginRecord::getUserAgent, dto.getUserAgent())
                .ge(dto.getExpireTimeFrom() != null, LoginRecord::getExpireTime, dto.getExpireTimeFrom())
                .le(dto.getExpireTimeTo() != null, LoginRecord::getExpireTime, dto.getExpireTimeTo())
                .eq(dto.getIsOffline() != null, LoginRecord::getIsOffline, dto.getIsOffline())
                .eq(dto.getRole() != null, LoginRecord::getRole, dto.getRole())
                .ge(dto.getCreateTimeFrom() != null, LoginRecord::getCreateTime, dto.getCreateTimeFrom())
                .le(dto.getCreateTimeTo() != null, LoginRecord::getCreateTime, dto.getCreateTimeTo());
        // 查询数据
        page = loginRecordMapper.selectPage(page, wrapper);
        // 封装返回结果
        PageVO<LoginRecord> vo = PageVO.fromIPage(page);
        log.debug("page query vo = {}", vo);
        return vo;
    }

    @Override
    public LoginRecord queryById(Long id) {
        return loginRecordMapper.selectById(id);
    }

    @Override
    @Transactional
    public void delete(Long id, Boolean offline) {
        if (!offline) {
            // 不用强制下线，仅删除记录即可
            if (loginRecordMapper.deleteById(id) == 0) {
                throw new CommonException("删除登陆记录失败");
            }
            return;
        }

        // 获取登录记录的token，方便强制下线
        LoginRecord loginRecord = loginRecordMapper.selectById(id);
        if (loginRecord == null) {
            throw new CommonException("登录记录不存在");
        }
        // 删除数据库的记录
        if (loginRecordMapper.deleteById(id) == 0) {
            throw new CommonException("删除登陆记录失败");
        }
        // 已经下线了
        if (loginRecord.getIsOffline() || loginRecord.getExpireTime().isBefore(LocalDateTime.now())) {
            return;
        }
        // 强制下线
        String key = RedisKeyUtil.login(loginRecord.getToken());
        redisTemplate.delete(key);
    }

    @Override
    public void forceOffline(String token) {
        loginUtil.forceOfflineByToken(token);
    }
}
