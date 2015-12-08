package com.zhenhappy.ems.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wujianbin on 2014-05-15.
 */
@Entity
@Table(name = "t_contact", schema = "dbo")
public class TContact {
    private Integer id;
    private String name;
    private String position;
    private String phone;
    private String email;
    private String address;
    private String expressNumber;
    private Integer eid;
    private Integer isDelete;

    public TContact() {
		super();
		this.isDelete = 0;
	}

	public TContact(Integer eid, String name, String position, String phone, String email) {
		super();
		this.eid = eid;
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.isDelete = 0;
	}
	
	public TContact(Integer eid, String name, String position, String phone, String email, Integer isDelete) {
		super();
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.eid = eid;
		this.isDelete = isDelete;
	}
	
	public TContact(Integer id, String name, String position, String phone, String email, Integer eid, Integer isDelete) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.eid = eid;
		this.isDelete = isDelete;
	}

	@Basic
    @Column(name = "is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Basic
    @Column(name = "expressnumber")
	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	@Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((eid == null) ? 0 : eid.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((expressNumber == null) ? 0 : expressNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isDelete == null) ? 0 : isDelete.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TContact other = (TContact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (eid == null) {
			if (other.eid != null)
				return false;
		} else if (!eid.equals(other.eid))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (expressNumber == null) {
			if (other.expressNumber != null)
				return false;
		} else if (!expressNumber.equals(other.expressNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDelete == null) {
			if (other.isDelete != null)
				return false;
		} else if (!isDelete.equals(other.isDelete))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TContact [id=" + id + ", name=" + name + ", position="
				+ position + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", expressNumber=" + expressNumber
				+ ", eid=" + eid + ", isDelete=" + isDelete + "]";
	}

}
