package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractPayCardInfo entity provides the base persistence definition of the
 * PayCardInfo entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractPayCardInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String design;
	private String title;
	private String depart;
	private String company;
	private String address;
	private String postCode;
	private String pobox;
	private String tel;
	private String homeTel;
	private String hp;
	private String did;
	private String fax;
	private String email;
	private String web;
	private String acct;
	private String bank;
	private String cable;
	private String telex;
	private String icq;
	private String addCountry;
	private String addProvince;
	private String addCity;
	private String addStreet;
	private String addBuliding;
	private String addNo;
	private String extra;
	private String memo;
	private String barCode;
	private String qrcode;
	private Integer printNumber;
	private String photoUrl;
	private Integer isDisabled;
	private String createdBy;
	private Timestamp createdTime;
	private String updatedBy;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public AbstractPayCardInfo() {
	}

	/** full constructor */
	public AbstractPayCardInfo(String name, String design, String title, String depart, String company, String address,
			String postCode, String pobox, String tel, String homeTel, String hp, String did, String fax, String email,
			String web, String acct, String bank, String cable, String telex, String icq, String addCountry,
			String addProvince, String addCity, String addStreet, String addBuliding, String addNo, String extra,
			String memo, String barCode, String qrcode, Integer printNumber, String photoUrl, Integer isDisabled,
			String createdBy, Timestamp createdTime, String updatedBy, Timestamp updateTime) {
		this.name = name;
		this.design = design;
		this.title = title;
		this.depart = depart;
		this.company = company;
		this.address = address;
		this.postCode = postCode;
		this.pobox = pobox;
		this.tel = tel;
		this.homeTel = homeTel;
		this.hp = hp;
		this.did = did;
		this.fax = fax;
		this.email = email;
		this.web = web;
		this.acct = acct;
		this.bank = bank;
		this.cable = cable;
		this.telex = telex;
		this.icq = icq;
		this.addCountry = addCountry;
		this.addProvince = addProvince;
		this.addCity = addCity;
		this.addStreet = addStreet;
		this.addBuliding = addBuliding;
		this.addNo = addNo;
		this.extra = extra;
		this.memo = memo;
		this.barCode = barCode;
		this.qrcode = qrcode;
		this.printNumber = printNumber;
		this.photoUrl = photoUrl;
		this.isDisabled = isDisabled;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Design")
	public String getDesign() {
		return this.design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Column(name = "Title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Depart")
	public String getDepart() {
		return this.depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@Column(name = "Company")
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "Address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PostCode")
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "POBOX")
	public String getPobox() {
		return this.pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	@Column(name = "Tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "HomeTel")
	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	@Column(name = "HP")
	public String getHp() {
		return this.hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	@Column(name = "DID")
	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	@Column(name = "Fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Web")
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "Acct")
	public String getAcct() {
		return this.acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	@Column(name = "Bank")
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "Cable")
	public String getCable() {
		return this.cable;
	}

	public void setCable(String cable) {
		this.cable = cable;
	}

	@Column(name = "Telex")
	public String getTelex() {
		return this.telex;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	@Column(name = "ICQ")
	public String getIcq() {
		return this.icq;
	}

	public void setIcq(String icq) {
		this.icq = icq;
	}

	@Column(name = "Add_country")
	public String getAddCountry() {
		return this.addCountry;
	}

	public void setAddCountry(String addCountry) {
		this.addCountry = addCountry;
	}

	@Column(name = "Add_province")
	public String getAddProvince() {
		return this.addProvince;
	}

	public void setAddProvince(String addProvince) {
		this.addProvince = addProvince;
	}

	@Column(name = "Add_city")
	public String getAddCity() {
		return this.addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	@Column(name = "Add_Street")
	public String getAddStreet() {
		return this.addStreet;
	}

	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}

	@Column(name = "Add_Buliding")
	public String getAddBuliding() {
		return this.addBuliding;
	}

	public void setAddBuliding(String addBuliding) {
		this.addBuliding = addBuliding;
	}

	@Column(name = "Add_No")
	public String getAddNo() {
		return this.addNo;
	}

	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}

	@Column(name = "Extra")
	public String getExtra() {
		return this.extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Column(name = "Memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "BarCode", length = 100)
	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Column(name = "QRCode", length = 100)
	public String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@Column(name = "PrintNumber")
	public Integer getPrintNumber() {
		return this.printNumber;
	}

	public void setPrintNumber(Integer printNumber) {
		this.printNumber = printNumber;
	}

	@Column(name = "PhotoURL", length = 200)
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Column(name = "IsDisabled")
	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	@Column(name = "CreatedBy")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CreatedTime", length = 23)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UpdatedBy")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "UpdateTime", length = 23)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}