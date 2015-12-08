package com.zhenhappy.ems.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TProductClassRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_product_class_relation"
    ,schema="dbo"
)

public class TProductClassRelation  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer productId;
     private Integer parentClassId;
     private Integer classId;
     private Date createTime;
     private Date updateTime;
     private Integer admin;
     private Date adminUpdateTime;


    // Constructors

    /** default constructor */
    public TProductClassRelation() {
    }

    
    /** full constructor */
    public TProductClassRelation(Integer productId, Integer parentClassId, Integer classId, Date createTime, Date updateTime, Integer admin, Date adminUpdateTime) {
        this.productId = productId;
        this.parentClassId = parentClassId;
        this.classId = classId;
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
    
    @Column(name="product_id")

    public Integer getProductId() {
        return this.productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    
    @Column(name="parent_class_id")

    public Integer getParentClassId() {
        return this.parentClassId;
    }
    
    public void setParentClassId(Integer parentClassId) {
        this.parentClassId = parentClassId;
    }
    
    @Column(name="class_id")

    public Integer getClassId() {
        return this.classId;
    }
    
    public void setClassId(Integer classId) {
        this.classId = classId;
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