package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户api key表
 * @TableName api_keys
 */
@TableName(value ="api_keys")
@Data
public class ApiKeys implements Serializable {
    /**
     * 用户api key id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id。一个用户可以有多个api key
     */
    private Long userId;

    /**
     * 密钥id。一个32字节、36进制整数
     */
    private String secretId;

    /**
     * MD5加密后的密钥
     */
    private String secretKey;

    /**
     * secret_key加密随机盐值
     */
    private String salt;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 过期时间。null表示永不过期
     */
    private Date expireTime;

    /**
     * 是否仅限免费接口
     */
    private Boolean onlyFree;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
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
        ApiKeys other = (ApiKeys) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSecretId() == null ? other.getSecretId() == null : this.getSecretId().equals(other.getSecretId()))
            && (this.getSecretKey() == null ? other.getSecretKey() == null : this.getSecretKey().equals(other.getSecretKey()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getIsEnabled() == null ? other.getIsEnabled() == null : this.getIsEnabled().equals(other.getIsEnabled()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getOnlyFree() == null ? other.getOnlyFree() == null : this.getOnlyFree().equals(other.getOnlyFree()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSecretId() == null) ? 0 : getSecretId().hashCode());
        result = prime * result + ((getSecretKey() == null) ? 0 : getSecretKey().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getIsEnabled() == null) ? 0 : getIsEnabled().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getOnlyFree() == null) ? 0 : getOnlyFree().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", secretId=").append(secretId);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", salt=").append(salt);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", onlyFree=").append(onlyFree);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}