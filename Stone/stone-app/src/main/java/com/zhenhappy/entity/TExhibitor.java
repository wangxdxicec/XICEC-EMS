package com.zhenhappy.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TExhibitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_exhibitor", schema = "dbo")
public class TExhibitor implements java.io.Serializable {

	// Fields

	private Integer eid;
	private String username;
	private String password;
	private Integer level;
	private String company;
	private String companye;
	private String companyt;
	private Integer area;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Integer isLogout;
	private Integer createUser;
	private Date createTime;
	private Integer updateUser;
	private Date updateTime;
	private Integer tag;
	private Integer province;
	private Integer country;
	private Integer group;

    // Constructors

	/** default constructor */
	public TExhibitor() {
	}

	/** minimal constructor */
	public TExhibitor(Integer eid) {
		this.eid = eid;
	}

	/** full constructor */
	public TExhibitor(Integer eid, String username, String password,
					  Integer level, String company, String companye, Integer area,
					  Date lastLoginTime, String lastLoginIp, Integer isLogout,
					  Integer createUser, Date createTime, Integer updateUser,
					  Date updateTime, Integer tag, Integer province, Integer country,
					  Integer group) {
		super();
		this.eid = eid;
		this.username = username;
		this.password = password;
		this.level = level;
		this.company = company;
		this.companye = companye;
		this.area = area;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.isLogout = isLogout;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.tag = tag;
		this.province = province;
		this.country = country;
		this.group = group;
	}
	
	// Property accessors
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "eid", unique = true, nullable = false)
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "[level]")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "company", length = 200)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "companye", length = 200)
	public String getCompanye() {
		return this.companye;
	}

	public void setCompanye(String companye) {
		this.companye = companye;
	}

	public String getCompanyt() {
		return companyt;
	}

	public void setCompanyt(String companyt) {
		this.companyt = companyt;
	}

	@Column(name = "area")
	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "last_login_time", length = 23)
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip", length = 20)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "is_logout")
	public Integer getIsLogout() {
		return this.isLogout;
	}

	public void setIsLogout(Integer isLogout) {
		this.isLogout = isLogout;
	}

	@Column(name = "create_user")
	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
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
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "tag")
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	@Column(name = "province")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "country")
	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	@Column(name = "[group]")
	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "TExhibitor [eid=" + eid + ", username=" + username
				+ ", password=" + password + ", level=" + level + ", company="
				+ company + ", companye=" + companye + ", area=" + area
				+ ", lastLoginTime=" + lastLoginTime + ", lastLoginIp="
				+ lastLoginIp + ", isLogout=" + isLogout + ", createUser="
				+ createUser + ", createTime=" + createTime + ", updateUser="
				+ updateUser + ", updateTime=" + updateTime + ", tag=" + tag
				+ ", province=" + province + ", country=" + country
				+ ", group=" + group + "]";
	}

}