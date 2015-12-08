package com.zhenhappy.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TProductType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_product_type", schema = "dbo")
public class TProductType implements java.io.Serializable {


    // Fields

    private Integer id;
    private String className;
    private String classNameEn;
    private String classNameTw;
    private Integer level;
    private Integer parentId;
    private Integer sort;
    private String mark;
    private Integer createBy;
    private Timestamp createTime;
    private Integer updateUser;
    private Timestamp updateTime;
    private Integer isOther;

    @Column(name = "is_other")
    public Integer getIsOther() {
        return isOther;
    }

    public void setIsOther(Integer isOther) {
        this.isOther = isOther;
    }
    // Constructors

    /**
     * default constructor
     */
    public TProductType() {
    }


    /**
     * full constructor
     */
    public TProductType(String className, String classNameEn, String classNameTw, Integer level, Integer parentId, Integer sort, String mark, Integer createBy, Timestamp createTime, Integer updateUser, Timestamp updateTime) {
        this.className = className;
        this.classNameEn = classNameEn;
        this.classNameTw = classNameTw;
        this.level = level;
        this.parentId = parentId;
        this.sort = sort;
        this.mark = mark;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "class_name", length = 500)

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "class_name_en", length = 500)

    public String getClassNameEn() {
        return this.classNameEn;
    }

    public void setClassNameEn(String classNameEn) {
        this.classNameEn = classNameEn;
    }

    @Column(name = "class_name_tw", length = 500)

    public String getClassNameTw() {
        return this.classNameTw;
    }

    public void setClassNameTw(String classNameTw) {
        this.classNameTw = classNameTw;
    }

    @Column(name = "level")

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "parent_id")

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "sort")

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "mark", length = 2000)

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Column(name = "create_by")

    public Integer getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Column(name = "create_time", length = 23)

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user")

    public Integer getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_time", length = 23)

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TProductType{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classNameEn='" + classNameEn + '\'' +
                ", classNameTw='" + classNameTw + '\'' +
                ", level=" + level +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", mark='" + mark + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                ", isOther=" + isOther +
                '}';
    }
}