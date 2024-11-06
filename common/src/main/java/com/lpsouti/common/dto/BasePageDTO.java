package com.lpsouti.common.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageDTO implements Serializable {
    @NotNull
    @Min(1)
    protected Integer pageNum;

    @NotNull
    @Min(0)
    protected Integer pageSize;

    protected Boolean asc = false;
}
