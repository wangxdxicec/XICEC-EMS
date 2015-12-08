package com.zhenhappy.ems.dto;

/**
 * Created by wujianbin on 2014-07-10.
 */
public class QueryProductType2Response {
	private Integer id;
	private String text;
	private String iconCls;
	private Boolean checked;
	
	public QueryProductType2Response() {
		super();
	}

	public QueryProductType2Response(Integer id, String text, String iconCls, Boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
