package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-22
 * Time: 下午8:15
 * Function:
 */
public class ModifyPasswordRequest extends AfterLoginRequest {

	private String oldPassword;
	
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
