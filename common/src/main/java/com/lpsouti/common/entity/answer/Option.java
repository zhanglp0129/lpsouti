package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    /**
     * 选项索引，一般为大写英文字母
     */
    @NotNull
    private String index;
    /**
     * 选项内容
     */
    @NotNull
    private String content;
}
