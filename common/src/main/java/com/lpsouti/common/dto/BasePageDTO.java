package com.lpsouti.common.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageDTO implements Serializable {
    @NotNull
    @Min(1)
    protected Long pageNum;

    @NotNull
    @Min(0)
    protected Long pageSize;

    protected Boolean asc = false;

    // 将PageDTO转为Mybatis Plus的IPage
    public <T> IPage<T> toIPage() {
        return new Page<>(pageNum, pageSize);
    }

    public <T> IPage<T> toIPage(String orderField) {
        Page<T> page = new Page<>(pageNum, pageSize);
        if (asc) {
            page.addOrder(OrderItem.asc(orderField));
        } else {
            page.addOrder(OrderItem.desc(orderField));
        }
        return page;
    }
}
