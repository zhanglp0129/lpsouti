package com.lpsouti.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {
    private Long total;
    private List<T> items;

    // 将Mybatis Plus的分页接口转为PageVO
    public static <T> PageVO<T> fromIPage(IPage<T> page) {
        return new PageVO<>(page.getTotal(), page.getRecords());
    }
}
