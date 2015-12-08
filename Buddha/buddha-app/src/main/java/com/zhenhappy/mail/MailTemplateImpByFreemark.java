package com.zhenhappy.mail;

/**
 * 
 */

import java.util.Map;

/**
 *
 * @version 1.00.00
 * @description: 
 * @author: 梁海舰
 * @date: 2012-6-21
 * @history:
 */
public interface MailTemplateImpByFreemark {
	
	/**
	 * 通过Freemark获得邮件内容模板
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String getMailHtmlText(Map<Object, Object> model) throws Exception;
}