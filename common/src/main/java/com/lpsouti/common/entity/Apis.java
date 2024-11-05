package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * api接口表
 * @TableName apis
 */
@TableName(value ="apis")
@Data
public class Apis implements Serializable {
    /**
     * api接口id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求路径。不能使用路径参数
     */
    private String path;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述。markdown格式，会展示在接口文档中
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean isEnabled;

    /**
     * 是否为免费接口
     */
    private Boolean isFree;

    /**
     * 每次调用消耗余额
     */
    private Integer consumeBalance;

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
        Apis other = (Apis) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getIsEnabled() == null ? other.getIsEnabled() == null : this.getIsEnabled().equals(other.getIsEnabled()))
            && (this.getIsFree() == null ? other.getIsFree() == null : this.getIsFree().equals(other.getIsFree()))
            && (this.getConsumeBalance() == null ? other.getConsumeBalance() == null : this.getConsumeBalance().equals(other.getConsumeBalance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getIsEnabled() == null) ? 0 : getIsEnabled().hashCode());
        result = prime * result + ((getIsFree() == null) ? 0 : getIsFree().hashCode());
        result = prime * result + ((getConsumeBalance() == null) ? 0 : getConsumeBalance().hashCode());
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
        sb.append(", method=").append(method);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isFree=").append(isFree);
        sb.append(", consumeBalance=").append(consumeBalance);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}