package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class GetWscInfoRequest extends AfterLoginRequest {

	private Integer wscId;
	private String wscDate;
	private String location;
	private String speaker;
	private String name;
    private String wscTime;

	public Integer getWscId() {
		return wscId;
	}

	public void setWscId(Integer wscId) {
		this.wscId = wscId;
	}

	public String getWscDate() {
		return wscDate;
	}

	public void setWscDate(String wscDate) {
		this.wscDate = wscDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getWscTime() {
        return wscTime;
    }

    public void setWscTime(String wscTime) {
        this.wscTime = wscTime;
    }
}
