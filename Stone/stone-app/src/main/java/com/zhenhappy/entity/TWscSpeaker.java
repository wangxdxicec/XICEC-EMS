package com.zhenhappy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 苏志锋
 * 
 */
public class TWscSpeaker {
	private Integer speakerId;
	private String title;
	private String name;
	private String introduction;
	public Integer getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Integer speakerId) {
		this.speakerId = speakerId;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
