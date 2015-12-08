package com.zhenhappy.dto;

import java.util.Date;

/**
 * 
 * @author rocsky
 * 
 */
public class CommentsRequest extends BaseRequest {
	private Integer commentsId;
	private Integer exhibitorId;
	private Integer userId;
	private String content;
	private Date createOn;

	public Integer getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}

	public Integer getExhibitorId() {
		return exhibitorId;
	}

	public void setExhibitorId(Integer exhibitorId) {
		this.exhibitorId = exhibitorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
}
