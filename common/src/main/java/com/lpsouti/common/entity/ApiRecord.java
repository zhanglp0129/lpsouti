package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * api调用记录表
 * @TableName api_records
 */
@TableName(value ="api_records")
@Data
public class ApiRecord implements Serializable {
    /**
     * api调用记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * api key id
     */
    private Long keyId;

    /**
     * api id
     */
    private Long apiId;

    /**
     * 消耗余额
     */
    private Integer consumeBalance;

    /**
     * 调用者ip
     */
    private String ip;

    /**
     * 调用错误信息。null表示调用成功
     */
    private String err;

    /**
     * 创建时间。接口调用时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ApiRecord other = (ApiRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKeyId() == null ? other.getKeyId() == null : this.getKeyId().equals(other.getKeyId()))
            && (this.getApiId() == null ? other.getApiId() == null : this.getApiId().equals(other.getApiId()))
            && (this.getConsumeBalance() == null ? other.getConsumeBalance() == null : this.getConsumeBalance().equals(other.getConsumeBalance()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getErr() == null ? other.getErr() == null : this.getErr().equals(other.getErr()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKeyId() == null) ? 0 : getKeyId().hashCode());
        result = prime * result + ((getApiId() == null) ? 0 : getApiId().hashCode());
        result = prime * result + ((getConsumeBalance() == null) ? 0 : getConsumeBalance().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getErr() == null) ? 0 : getErr().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keyId=").append(keyId);
        sb.append(", apiId=").append(apiId);
        sb.append(", consumeBalance=").append(consumeBalance);
        sb.append(", ip=").append(ip);
        sb.append(", err=").append(err);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}