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
public class TWscTopic {
	private Integer topicId;
	private Integer wscId;
	private String name;
	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getWscId() {
		return wscId;
	}

	public void setWscId(Integer wscId) {
		this.wscId = wscId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
