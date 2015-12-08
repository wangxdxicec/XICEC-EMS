/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * LogUtil.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-2-5 Administrator 最初版本
 */
package com.zhenhappy.util;

import javax.persistence.Entity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

/**
 * <b>Summary: </b> TODO 日志记录工具类
 */
@Entity public class LogUtil {

	/**
	 * 
	 * <b>Summary: </b> logRequest 日志记录请求的参数
	 * 
	 * @param logger
	 * @param t
	 * @param request
	 */
	public static void logRequest(Logger logger, Object o, Object request) {
		logger.info("接口类:" + o.getClass().getSimpleName() + " \n参数 "
				+ ToStringBuilder.reflectionToString(request, ToStringStyle.MULTI_LINE_STYLE));
	}
}
