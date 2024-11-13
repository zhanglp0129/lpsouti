package com.lpsouti.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.LoginRecord;
import com.lpsouti.common.exception.CommonException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Resource
    private final BaseMapper<LoginRecord> loginRecordMapper;

    /**
     * 根据用户id强制下线
     *
     * @param userId 用户id
     */
    @Transactional
    public void forceOfflineByUserId(Long userId) {
        // 查询所有在线的token
        LambdaQueryWrapper<LoginRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(LoginRecord::getId, LoginRecord::getToken)
                .eq(LoginRecord::getUserId, userId)
                .gt(LoginRecord::getExpireTime, LocalDateTime.now())
                .eq(LoginRecord::getIsOffline, false);
        List<LoginRecord> loginRecords = loginRecordMapper.selectList(queryWrapper);
        log.debug("online login records = {}", loginRecords);

        // 获取结果ids
        List<Long> ids = loginRecords.stream().map(LoginRecord::getId).toList();
        log.debug("ids = {}", ids);
        // 将所有在线的token设为离线，不需要加锁
        LambdaUpdateWrapper<LoginRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(LoginRecord::getIsOffline, true)
                .in(LoginRecord::getId, ids);
        loginRecordMapper.update(updateWrapper);

        // 删除redis中的token
        List<String> keys = loginRecords.stream()
                .map(LoginRecord::getToken)
                .map(RedisKeyUtil::login).toList();
        log.debug("keys = {}", keys);
        redisTemplate.delete(keys);
    }

    /**
     * 根据登录token强制下线
     *
     * @param token  登录token
     */
    public void forceOfflineByToken(String token) {
        // 将该token设为离线
        LambdaUpdateWrapper<LoginRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(LoginRecord::getIsOffline, true)
                .eq(LoginRecord::getToken, token);
        int rows = loginRecordMapper.update(updateWrapper);
        if (rows == 0) {
            throw new CommonException("强制下线失败");
        }

        // 删除redis中的token
        String key = RedisKeyUtil.login(token);
        redisTemplate.delete(key);
    }
}
