package com.zhenhappy.ems.entity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wujianbin on 2014-07-30.
 */
@Entity
@Table(name = "t_brand")
public class TBrand {

    private Integer id;

    private Integer eid;

    private String brandName;

    private Date createTime;

    private Integer isDefault;

    @Column(name = "is_default")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "brand_name")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TBrand)) return false;

        TBrand brand = (TBrand) o;

        if (id != null ? !id.equals(brand.id) : brand.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
