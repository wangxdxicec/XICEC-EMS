package com.zhenhappy.ems.manager.dto;

import java.util.Date;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-07-03.
 */
public class ModifyArticleRequest extends BaseRequest {
	private Integer id;
	private String title;
	private String titleEn;
	private String digest;
	private String digestEn;
	private String content;
	private String contentEn;
	private Date createTime;
	private Date updateTime;
	private Integer createAdmin;
	private Integer updateAdmin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigestEn() {
		return digestEn;
	}

	public void setDigestEn(String digestEn) {
		this.digestEn = digestEn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(Integer createAdmin) {
		this.createAdmin = createAdmin;
	}

	public Integer getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(Integer updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

}
