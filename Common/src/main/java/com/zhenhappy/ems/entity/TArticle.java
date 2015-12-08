package com.zhenhappy.ems.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_article"
	, schema = "dbo"
)
public class TArticle implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	
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

	// Constructors

	/** default constructor */
	public TArticle() {
	}

	/** minimal constructor */
	public TArticle(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TArticle(String title, String titleEn, String digest,
			String digestEn, String content, String contentEn,
			Date createTime, Date updateTime, Integer createAdmin,
			Integer updateAdmin) {
		this.title = title;
		this.titleEn = titleEn;
		this.digest = digest;
		this.digestEn = digestEn;
		this.content = content;
		this.contentEn = contentEn;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createAdmin = createAdmin;
		this.updateAdmin = updateAdmin;
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

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "title_en", length = 200)
	public String getTitleEn() {
		return this.titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	@Column(name = "digest", length = 1000)
	public String getDigest() {
		return this.digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	@Column(name = "digest_en", length = 1000)
	public String getDigestEn() {
		return this.digestEn;
	}

	public void setDigestEn(String digestEn) {
		this.digestEn = digestEn;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "content_en")
	public String getContentEn() {
		return this.contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	@Column(name = "create_time", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 23)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "create_admin")
	public Integer getCreateAdmin() {
		return this.createAdmin;
	}

	public void setCreateAdmin(Integer createAdmin) {
		this.createAdmin = createAdmin;
	}

	@Column(name = "update_admin")
	public Integer getUpdateAdmin() {
		return this.updateAdmin;
	}

	public void setUpdateAdmin(Integer updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	@Override
	public String toString() {
		return "TArticle [id=" + id + ", title=" + title + ", titleEn="
				+ titleEn + ", digest=" + digest + ", digestEn=" + digestEn
				+ ", content=" + content + ", contentEn=" + contentEn
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", createAdmin=" + createAdmin + ", updateAdmin="
				+ updateAdmin + "]";
	}
	
}