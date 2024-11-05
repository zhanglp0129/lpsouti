package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录记录表
 * @TableName login_records
 */
@TableName(value ="login_records")
@Data
public class LoginRecords implements Serializable {
    /**
     * 登录记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录token
     */
    private String token;

    /**
     * 登录ip。可用于获取登录所在地
     */
    private String ip;

    /**
     * 登录时的UA。可用于获取登录设备信息
     */
    private String userAgent;

    /**
     * 登录信息过期时间。不存在永不过期的登录信息
     */
    private Date expireTime;

    /**
     * 登录角色。1管理员 2普通用户
     */
    private Integer role;

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
        LoginRecords other = (LoginRecords) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getUserAgent() == null ? other.getUserAgent() == null : this.getUserAgent().equals(other.getUserAgent()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
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
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getUserAgent() == null) ? 0 : getUserAgent().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
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
        sb.append(", token=").append(token);
        sb.append(", ip=").append(ip);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", role=").append(role);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}