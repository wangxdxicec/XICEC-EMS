package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;


/**
 * AbstractTUserCompany entity provides the base persistence definition of the TUserCompany entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractTUserCompany implements java.io.Serializable {

    private Integer userId;
    private Integer companyId;
    private String remark;
    private Date createTime;
    private Integer isDelete;

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "is_delete")
    public Integer getDelete() {
        return isDelete;
    }

    public void setDelete(Integer delete) {
        isDelete = delete;
    }

// Constructors

    /**
     * default constructor
     */
    public AbstractTUserCompany() {
    }


}