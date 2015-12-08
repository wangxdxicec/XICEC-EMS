package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-10.
 */
public class QueryProductTypeResponse {
	private Integer id;
	private String text;
	private String iconCls;
	private String state;
	private Boolean checked;
	private List<QueryProductType2Response> children;

	public QueryProductTypeResponse() {
		super();
	}

	public QueryProductTypeResponse(Integer id, String text, String iconCls,
			String state, Boolean checked, List<QueryProductType2Response> children) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.checked = checked;
		this.children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<QueryProductType2Response> getChildren() {
		return children;
	}

	public void setChildren(List<QueryProductType2Response> children) {
		this.children = children;
	}

}
