package com.zhenhappy.ems.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TExhibitorClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_exhibitor_class", schema="dbo")
public class TExhibitorClass  implements java.io.Serializable {

    // Fields

     private Integer id;
     private Integer einfoId;
     private Integer parentClassId;
     private Integer classId;
     private Date createTime;
     private Date updateTime;
     private Integer admin;
     private Date adminUpdateTime;


    // Constructors

    /** default constructor */
    public TExhibitorClass() {
    }

    
    /** full constructor */
    public TExhibitorClass(Integer einfoId, Integer parentClassId, Integer classId, Date createTime, Date updateTime, Integer admin, Date adminUpdateTime) {
        this.einfoId = einfoId;
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
    
    @Column(name="einfo_id")

    public Integer getEinfoId() {
        return this.einfoId;
    }
    
    public void setEinfoId(Integer einfoId) {
        this.einfoId = einfoId;
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
    
    public void setUpdateTime(Timestamp updateTime) {
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

	@Override
	public String toString() {
		return "TExhibitorClass [id=" + id + ", einfoId=" + einfoId
				+ ", parentClassId=" + parentClassId + ", classId=" + classId
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", admin=" + admin + ", adminUpdateTime=" + adminUpdateTime
				+ "]";
	}
   
}