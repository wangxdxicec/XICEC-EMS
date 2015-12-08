
/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * SystemConfig.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-9-22 wujianbin 最初版本
 */
package com.zhenhappy.system;

import java.util.Map;


/**
 * <b>Summary: </b>
 * TODO 
 */
public class SystemConfig {
	
	private Map<String, String> configs;

	public Map<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}
	
	public String getVal(String key){
		return configs.get(key);
	}
	
}
