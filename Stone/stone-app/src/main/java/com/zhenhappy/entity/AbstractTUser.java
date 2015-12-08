package com.zhenhappy.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * AbstractTUser entity provides the base persistence definition of the TUser entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractTUser implements java.io.Serializable {


    // Fields    

    private Integer userId;
    private String mobilePhone;
    private String username;
    private String password;
    private String email;
    private String name;
    private String company;
    private String position;
    private Integer isCheck;
    private Integer userType;
    private Integer isDisable;
    private Date createTime;
    private String updateUser;
    private Date updateTiime;
    private String usercode;


    // Constructors

    /**
     * default constructor
     */
    public AbstractTUser() {
    }

    /**
     * minimal constructor
     */
    public AbstractTUser(Integer userId, String password, Integer isCheck, Integer userType, Integer isDisable, Timestamp createTime) {
        this.userId = userId;
        this.password = password;
        this.isCheck = isCheck;
        this.userType = userType;
        this.isDisable = isDisable;
        this.createTime = createTime;
    }

    // Property accessors
    @Id

    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "mobile_phone", length = 20)
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Column(name = "password", nullable = false, length = 20)

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", length = 100)

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "name", length = 40)

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "company", length = 100)

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "position", length = 100)

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "isCheck", nullable = false)
    public Integer getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    @Column(name = "user_type", nullable = false)

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Column(name = "isDisable", nullable = false)

    public Integer getIsDisable() {
        return this.isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    @Column(name = "create_time", nullable = false, length = 23)

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user", length = 100)

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_tiime", length = 23)
    public Date getUpdateTiime() {
        return this.updateTiime;
    }

    public void setUpdateTiime(Date updateTiime) {
        this.updateTiime = updateTiime;
    }
    
    @Column(name = "username", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    @Column(name = "usercode", length = 32)
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}