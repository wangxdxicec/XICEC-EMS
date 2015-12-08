package com.zhenhappy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author rocsky
 * 
 */
@Entity
@Table(name = "t_comments", schema = "dbo")
public class TComments implements java.io.Serializable {
	private Integer commentsId;
	private Integer exhibitorId;
	private Integer userId;
	private String content;
	private Date createOn;
	@Id
	@Column(name = "comments_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}

	@Column(name = "exhibitor_id")
	public Integer getExhibitorId() {
		return exhibitorId;
	}

	public void setExhibitorId(Integer exhibitorId) {
		this.exhibitorId = exhibitorId;
	}
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "create_on")
	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

}
