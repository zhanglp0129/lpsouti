package com.lpsouti.admin.dto.api_record;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.constant.OrderField;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRecordPageDTO extends BasePageDTO implements Serializable {
    /**
     * api id
     */
    private Long apiId;

    /**
     * 调用记录创建时间，即调用时间。范围查找左边界
     */
    private LocalDateTime createTimeFrom;

    /**
     * 创建时间范围查找右边界
     */
    private LocalDateTime createTimeTo;

    /**
     * 调用者ip地址
     */
    private String ip;

    /**
     * 排序字段
     */
    @OrderFieldEnum({OrderField.CREATE_TIME, OrderField.CONSUME_BALANCE})
    private String orderBy = OrderField.CREATE_TIME;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 用户id
     */
    private Long userId;
}
