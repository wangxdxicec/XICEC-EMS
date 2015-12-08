package com.zhenhappy.ems.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TExhibitorClassOther entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_exhibitor_class_other", schema="dbo")
public class TExhibitorClassOther  implements java.io.Serializable {

    // Fields

     private Integer id;
     private Integer exhibitorClassId;
     private String name;
     private Date createTime;
     private Date updateTime;
     private Integer admin;
     private Date adminUpdateTime;


    // Constructors

    /** default constructor */
    public TExhibitorClassOther() {
    }

    
    /** full constructor */
    public TExhibitorClassOther(Integer exhibitorClassId, String name, Date createTime, Date updateTime, Integer admin, Date adminUpdateTime) {
        this.exhibitorClassId = exhibitorClassId;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.admin = admin;
        this.adminUpdateTime = adminUpdateTime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="exhibitor_class_id")

    public Integer getExhibitorClassId() {
        return this.exhibitorClassId;
    }
    
    public void setExhibitorClassId(Integer exhibitorClassId) {
        this.exhibitorClassId = exhibitorClassId;
    }
    
    @Column(name="name", length=2000)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="create_time", length=23)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="update_time", length=23)

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="admin")

    public Integer getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
    
    @Column(name="admin_update_time", length=23)

    public Date getAdminUpdateTime() {
        return this.adminUpdateTime;
    }
    
    public void setAdminUpdateTime(Date adminUpdateTime) {
        this.adminUpdateTime = adminUpdateTime;
    }
   








}