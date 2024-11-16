package com.lpsouti.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.properties.BalanceProperties;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class BalanceUtil {

    private final BalanceProperties balanceProperties;

    @Resource
    private final BaseMapper<Balance> balanceMapper;

    /**
     * 刷新免费余额。会加上悲观锁，刷新失败会抛出异常
     * @param userId 用户id
     */
    @Transactional
    public void refreshFreeBalance(Long userId) {
        // 构建查询条件
        LambdaQueryWrapper<Balance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Balance::getId, Balance::getRefreshDate, Balance::getFreeBalance)   // 指定查询字段
                .eq(Balance::getUserId, userId) // 指定用户id
                .last("for update");    // 使用悲观锁
        // 查询数据
        Balance balance = balanceMapper.selectOne(queryWrapper);
        log.debug("balance = {}, now date = {}", balance, LocalDate.now());

        // 没有查到数据
        if (balance == null) {
            throw new CommonException("没有用户余额数据");
        }
        // 今天已经刷新过了
        if (balance.getRefreshDate() != null && balance.getRefreshDate().isEqual(LocalDate.now())) {
            throw new CommonException("重复刷新余额");
        }

        // 刷新免费余额
        LambdaUpdateWrapper<Balance> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Balance::getFreeBalance, balanceProperties.getDailyFreeBalance()) // 修改免费余额
                .set(Balance::getRefreshDate, LocalDate.now())  // 修改刷新日期
                .eq(Balance::getId, balance.getId());   // 指定余额id
        balanceMapper.update(updateWrapper);
    }

    /**
     * 扣减余额
     *
     * @param userId 用户id
     * @param value  扣减值
     */
    @Transactional
    public void deductBalance(Long userId, BigDecimal value) {
        // 查询用户余额
        LambdaQueryWrapper<Balance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Balance::getId, Balance::getBalance)
                .eq(Balance::getUserId, userId)
                .last("for update");
        Balance balance = balanceMapper.selectOne(queryWrapper);
        log.debug("balance = {}", balance);

        // 没有查到数据
        if (balance == null || balance.getBalance() == null) {
            throw new CommonException("没用用户余额数据");
        }
        // 判断余额是否足够
        if (balance.getBalance().compareTo(value) < 0) {
            throw new CommonException("余额不足");
        }

        // 扣减余额
        balance.setBalance(balance.getBalance().subtract(value));
        log.debug("balance = {}", balance);
        // 写入数据库
        balanceMapper.updateById(balance);
    }

    /**
     * 扣减免费余额。同时会刷新免费余额
     *
     * @param userId 用户id
     * @param value  扣减值
     */
    @Transactional
    public void deductFreeBalance(Long userId, BigDecimal value) {
        // 查询用户余额
        LambdaQueryWrapper<Balance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Balance::getId, Balance::getFreeBalance, Balance::getRefreshDate)
                .eq(Balance::getUserId, userId)
                .last("for update");
        Balance balance = balanceMapper.selectOne(queryWrapper);
        log.debug("balance = {}", balance);

        // 没有查到数据
        if (balance == null || balance.getFreeBalance() == null) {
            throw new CommonException("没有用户余额数据");
        }
        // 刷新免费余额
        if (balance.getRefreshDate() == null || balance.getRefreshDate().isBefore(LocalDate.now())) {
            balance.setRefreshDate(LocalDate.now());
            balance.setFreeBalance(balanceProperties.getDailyFreeBalance());
        }
        // 判断余额是否足够
        if (balance.getFreeBalance().compareTo(value) < 0) {
            throw new CommonException("余额不足");
        }

        // 扣减余额
        balance.setFreeBalance(balance.getFreeBalance().subtract(value));
        log.debug("balance = {}", balance);
        // 写入数据库
        balanceMapper.updateById(balance);
    }
}
