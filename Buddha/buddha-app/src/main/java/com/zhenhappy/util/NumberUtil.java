
/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * NumberUtil.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-8-15 wangsu 最初版本
 */
package com.zhenhappy.util;

import javax.persistence.Entity;


/**
 * <b>Summary: </b>
 * TODO 
 */
@Entity public class NumberUtil {
	
	private static String[] numbers = {"一","二","三","四","五","六","七"};
	
	private static String getCNumber(int num){
		return numbers[num];
	}
}
