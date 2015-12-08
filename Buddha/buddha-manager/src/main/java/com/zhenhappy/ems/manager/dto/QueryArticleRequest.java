package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-07-03.
 */
public class QueryArticleRequest extends EasyuiRequest {

	private String title;
	private String titleEn;
	private String digest;
	private String digestEn;
	private String content;
	private String contentEn;

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

}
