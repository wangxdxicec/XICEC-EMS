package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTUserProduct entity provides the base persistence definition of the
 * TUserProduct entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTUserProduct implements java.io.Serializable {

    // Fields

    private Integer userId;
    private Integer product_id;
    private String remark;
    private Timestamp createTime;
    private Integer isDelete;


    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "product_id")
    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }


    /**
     * default constructor
     */
    public AbstractTUserProduct() {
    }


    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "create_time", nullable = false, length = 23)
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "is_delete", nullable = false)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}