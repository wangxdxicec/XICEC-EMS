package com.zhenhappy.dto;

public class BaseRequest {
    /**
     * 1中文
     * 2英文
     * 3繁体
     */
    private Integer local;
    
    /**
     * 机器类型
     * 1-iOS
     * 2-android
     */
    private Integer machineType;

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}
}
