package com.lpsouti.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.properties.BalanceProperties;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class BalanceUtil {

    private final BalanceProperties balanceProperties;

    @Resource
    private final BaseMapper<Balance> balanceMapper;

    /**
     * 刷新免费余额。会加上悲观锁
     *
     * @param userId 用户id
     * @return 是否刷新成功
     */
    @Transactional
    public boolean refreshFreeBalance(Long userId) {
        // TODO 测试该方法
        // 构建查询条件
        LambdaQueryWrapper<Balance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Balance::getId, Balance::getRefreshDate, Balance::getFreeBalance)   // 指定查询字段
                .eq(Balance::getUserId, userId) // 指定用户id
                .last("for update");    // 使用悲观锁
        // 查询数据
        Balance balance = balanceMapper.selectOne(queryWrapper);
        log.debug("balance = {}, now date = {}", balance, LocalDate.now());
        // 没有查到数据、今天已经刷新过了、刷新后余额变少
        if (balance == null ||
                (balance.getRefreshDate() != null && balance.getRefreshDate().isEqual(LocalDate.now())) ||
                balanceProperties.getDailyFreeBalance().compareTo(balance.getFreeBalance()) <= 0
        ) {
            return false;
        }

        // 刷新免费余额
        LambdaUpdateWrapper<Balance> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Balance::getFreeBalance, balanceProperties.getDailyFreeBalance()) // 修改免费余额
                .set(Balance::getRefreshDate, LocalDate.now())  // 修改刷新日期
                .eq(Balance::getId, balance.getId());   // 指定余额id
        return balanceMapper.update(updateWrapper) != 0;
    }
}
