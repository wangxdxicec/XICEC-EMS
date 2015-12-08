package com.zhenhappy.ems.entity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wujianbin on 2014-05-13.
 */
@Entity
@Table(name = "t_invoice_apply", schema = "dbo")
public class TInvoiceApply {
    private Integer id;
    private String exhibitorNo;
    private String invoiceNo;
    private String title;
    private Integer eid;
    private Date createTime;
    
    public TInvoiceApply() {
		super();
	}

	public TInvoiceApply(String exhibitorNo, String invoiceNo, String title,
			Integer eid, Date createTime) {
		super();
		this.exhibitorNo = exhibitorNo;
		this.invoiceNo = invoiceNo;
		this.title = title;
		this.eid = eid;
		this.createTime = createTime;
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
    @Column(name = "exhibitor_no")
    public String getExhibitorNo() {
        return exhibitorNo;
    }

    public void setExhibitorNo(String exhibitorNo) {
        this.exhibitorNo = exhibitorNo;
    }

    @Basic
    @Column(name = "invoice_no")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TInvoiceApply that = (TInvoiceApply) o;

        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (eid != null ? !eid.equals(that.eid) : that.eid != null) return false;
        if (exhibitorNo != null ? !exhibitorNo.equals(that.exhibitorNo) : that.exhibitorNo != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (invoiceNo != null ? !invoiceNo.equals(that.invoiceNo) : that.invoiceNo != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (exhibitorNo != null ? exhibitorNo.hashCode() : 0);
        result = 31 * result + (invoiceNo != null ? invoiceNo.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
