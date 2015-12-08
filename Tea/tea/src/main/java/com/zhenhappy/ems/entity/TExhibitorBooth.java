package com.zhenhappy.ems.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wujianbin on 2014-04-26.
 */
@Entity
@Table(name = "t_exhibitor_booth", schema = "dbo")
public class TExhibitorBooth {
    private Integer id;
    private Integer eid;
    private String boothNumber;
    private String exhibitionArea;
    private String mark;
    private Integer createUser;
    private Date createTime;
    private Integer updateUser;
    private Date updateTime;

    public TExhibitorBooth() {
		super();
	}

	public TExhibitorBooth(Integer id, Integer eid, String boothNumber,
                           String exhibitionArea, String mark, Integer createUser,
                           Date createTime, Integer updateUser, Date updateTime) {
		super();
		this.id = id;
		this.eid = eid;
		this.boothNumber = boothNumber;
		this.exhibitionArea = exhibitionArea;
		this.mark = mark;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "booth_number")
    public String getBoothNumber() {
        return boothNumber;
    }

    public void setBoothNumber(String boothNumber) {
        this.boothNumber = boothNumber;
    }

    @Basic
    @Column(name = "exhibition_area")
    public String getExhibitionArea() {
        return exhibitionArea;
    }

    public void setExhibitionArea(String exhibitionArea) {
        this.exhibitionArea = exhibitionArea;
    }

    @Basic
    @Column(name = "mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "create_user")
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_user")
    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TExhibitorBooth that = (TExhibitorBooth) o;

        if (boothNumber != null ? !boothNumber.equals(that.boothNumber) : that.boothNumber != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null) return false;
        if (eid != null ? !eid.equals(that.eid) : that.eid != null) return false;
        if (exhibitionArea != null ? !exhibitionArea.equals(that.exhibitionArea) : that.exhibitionArea != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (boothNumber != null ? boothNumber.hashCode() : 0);
        result = 31 * result + (exhibitionArea != null ? exhibitionArea.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "TExhibitorBooth [id=" + id + ", eid=" + eid + ", boothNumber="
				+ boothNumber + ", exhibitionArea=" + exhibitionArea
				+ ", mark=" + mark + ", createUser=" + createUser
				+ ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + "]";
	}
    
}
