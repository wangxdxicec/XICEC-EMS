package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractVQrcodeInfo entity provides the base persistence definition of the
 * VQrcodeInfo entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractVQrcodeInfo implements java.io.Serializable {

	// Fields

		private String qrcode;
		private String name;
		private String telephone;
		private String phone;
		private String fax;
		private String email;
		private String website;
		private String company;
		private String position;
		private String address;
		private String sourceType;

		// Constructors
		
		@Id
		@Column(name = "QRCode", length = 100)
		public String getQrcode() {
			return this.qrcode;
		}

		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}

		@Column(name = "name")
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Column(name = "Telephone")
		public String getTelephone() {
			return this.telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		@Column(name = "Phone")
		public String getPhone() {
			return this.phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
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

		@Column(name = "Website")
		public String getWebsite() {
			return this.website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}

		@Column(name = "Company")
		public String getCompany() {
			return this.company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		@Column(name = "Position")
		public String getPosition() {
			return this.position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		@Column(name = "Address")
		public String getAddress() {
			return this.address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Column(name = "SourceType", nullable = false, length = 8)
		public String getSourceType() {
			return this.sourceType;
		}

		public void setSourceType(String sourceType) {
			this.sourceType = sourceType;
		}
}