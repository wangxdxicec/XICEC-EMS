package com.zhenhappy.mail;


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
 * 作用:
 * 
 * @author 梁海舰 2012下午10:55:02
 */
@Component("FindBackMailTemplate")
public class FindBackMailTemplate implements MailTemplateImpByFreemark {

	private Configuration configuration;
	private Template template;

	public FindBackMailTemplate() throws IOException {
		configuration = new Configuration();
		configuration.setOutputEncoding("utf-8");
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(Constants.SYSTEM_BASE_PATH + File.separatorChar
				+ "mail"));
		template = configuration.getTemplate("findback_mail_template.vm");
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
