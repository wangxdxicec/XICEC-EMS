package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscSpeakerInfoResponse extends BaseResponse {
	private String speakerId;
	private String title;
	private String titleE;
	private String titleT;
	private String name;
	private String nameE;
	private String nameT;
	private String introduction;
	private String introductionE;
	private String introductionT;
	private String logoUrl;
	
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public WscSpeakerInfoResponse() {
	}

	public WscSpeakerInfoResponse(String speakerId, String title, String titleE, String titleT, String name, String nameE, String nameT,
			String introduction, String introductionE, String introductionT) {
		this.speakerId = speakerId;
		this.title = title;
		this.titleE = titleE;
		this.titleT = titleT;
		this.name = name;
		this.nameE = nameE;
		this.nameT = nameT;
		this.introduction = introduction;
		this.introductionE = introductionE;
		this.introductionT = introductionT;
	}

	public String getTitleE() {
		return titleE;
	}

	public void setTitleE(String titleE) {
		this.titleE = titleE;
	}

	public String getTitleT() {
		return titleT;
	}

	public void setTitleT(String titleT) {
		this.titleT = titleT;
	}

	public String getNameE() {
		return nameE;
	}

	public void setNameE(String nameE) {
		this.nameE = nameE;
	}

	public String getNameT() {
		return nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
	}

	public String getIntroductionE() {
		return introductionE;
	}

	public void setIntroductionE(String introductionE) {
		this.introductionE = introductionE;
	}

	public String getIntroductionT() {
		return introductionT;
	}

	public void setIntroductionT(String introductionT) {
		this.introductionT = introductionT;
	}

	public String getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(String speakerId) {
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
