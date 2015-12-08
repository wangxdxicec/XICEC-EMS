package com.zhenhappy.dto;

/**
 * User: Haijian Liang Date: 13-11-21 Time: 下午10:32 Function:
 */
public class GetCompanyInfoRequest extends BaseRequest {

    private Integer companyId;
    
    private String remark;
    
    private Integer userId;

	private String booth;

	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
	}
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
