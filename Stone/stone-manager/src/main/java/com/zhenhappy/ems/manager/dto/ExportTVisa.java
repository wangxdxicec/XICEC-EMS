package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-11-28.
 */
public class ExportTVisa {

	private String exhibitor;		//展商
	private String passportName;	//Passport Name
	private String passportNo;		//Passport No
	private String nationality;		//国籍
	private String birth;			//出生日期
	private String gender;			//称呼
	private String jobTitle;		//职务
	private String companyName;		//公司
	private String boothNo;			//展位号
	private String address; 		//地址
	private String applyFor;		//申请地
	private String from;			//停留时间开始
	private String to;				//停留时间结束
	private String email;			//Email
	private String emailCompany;	//公司Email
	private String companyWebsite;	//网址
	private String tel;				//电话
	private String fax;				//传真
	private String needPost;		//需要邮寄
	private String expressTp;		//快递公司
	private String expressNo;		//快递单号
	private String expDate;			//Exp date 护照有效期
	private String createTime;		//网上提交时间

	public String getExhibitor() {
		return exhibitor;
	}

	public void setExhibitor(String exhibitor) {
		this.exhibitor = exhibitor;
	}

	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getApplyFor() {
		return applyFor;
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
	}

	public String getNeedPost() {
		return "";
	}

	public void setNeedPost(String needPost) {
		this.needPost = needPost;
	}

	public String getExpressTp() {
		return "";
	}

	public void setExpressTp(String expressTp) {
		this.expressTp = expressTp;
	}

	public String getExpressNo() {
		return "";
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getEmailCompany() {
		return emailCompany;
	}

	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
	}
}
