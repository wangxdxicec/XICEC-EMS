package com.zhenhappy.ems.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TProductImage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_product_image"
    ,schema="dbo"
)

public class TProductImage  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer productId;
     private String image;
     private Date createTime;
     private Date updateTime;
     private Integer admin;
     private Date adminUpdateTime;


    // Constructors

    /** default constructor */
    public TProductImage() {
    }

    
    /** full constructor */
    public TProductImage(Integer productId, String image, Date createTime, Date updateTime, Integer admin, Date adminUpdateTime) {
        this.productId = productId;
        this.image = image;
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
    
    @Column(name="image", length=500)

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
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