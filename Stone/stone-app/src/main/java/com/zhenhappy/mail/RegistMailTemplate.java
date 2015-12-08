package com.zhenhappy.mail;

/**
 * 
 */

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zhenhappy.system.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @version 1.00.00
 * @description:
 * @author: 梁海舰
 * @date: 2012-6-21
 * @history:
 */
@Component("RegistMailTemplate")
public class RegistMailTemplate implements MailTemplateImpByFreemark {

	private Configuration configuration;
	private Template template;

	public RegistMailTemplate() throws IOException {
		configuration = new Configuration();
		configuration.setOutputEncoding("utf-8");
		configuration.setDefaultEncoding("utf-8");
		configuration
				.setDirectoryForTemplateLoading(new File(Constants.SYSTEM_BASE_PATH + File.separatorChar + "mail"));
		template = configuration.getTemplate("regist_mail_template.vm");
		template.setEncoding("utf-8");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smrwl.mail.MailTemplateImpByFreemark#getMailHtmlText(java.util.Map)
	 */
	public String getMailHtmlText(Map<Object, Object> model) throws Exception {
		Writer writer = new StringWriter();
		template.process(model, writer);
		String returnString = writer.toString();
		writer.close();
		return returnString;
	}

}
