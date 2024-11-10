package com.lpsouti.common.dto;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BasePageDTO implements Serializable {
    @NotNull
    @Min(1)
    protected Long pageNum;

    @NotNull
    @Min(0)
    protected Long pageSize;

    // 获取排序字段
    public abstract String getOrderBy();

    // 是否为升序
    protected Boolean asc = false;

    // 将PageDTO转为Mybatis Plus的IPage
    public <T> IPage<T> toIPage() {
        String orderField = getOrderBy();
        Page<T> page = new Page<>(pageNum, pageSize);
        // 判断是否存在排序字段
        if (StrUtil.isEmpty(orderField)) {
            return page;
        }
        // 存在排序字段，判断是否为升序
        if (asc) {
            page.addOrder(OrderItem.asc(orderField));
        } else {
            page.addOrder(OrderItem.desc(orderField));
        }
        return page;
    }
}
