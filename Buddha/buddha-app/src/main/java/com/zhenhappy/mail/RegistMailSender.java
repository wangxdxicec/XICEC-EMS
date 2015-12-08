package com.zhenhappy.mail;

/**
 * 
 */

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.zhenhappy.entity.Verify;

/**
 * 用户注册,发送校验邮件
 * @version 1.00.00
 * @description: 
 * @author: 梁海舰
 * @date: 2012-6-19
 * @history:
 */
public class RegistMailSender implements Runnable{

	private Verify verify;
	private JavaMailSender javaMailSender;
	private RegistMailTemplate registMailTemplate;
	
	public RegistMailSender(Verify verify, JavaMailSender javaMailSender,RegistMailTemplate registMailTemplate) {
		super();
		this.verify = verify;
		this.javaMailSender = javaMailSender;
		this.registMailTemplate = registMailTemplate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			mimeMessageHelper.setTo(verify.getEmail());
			mimeMessageHelper.setFrom("info@buddhafair.com");
			mimeMessageHelper.setSubject("厦门会展集团-国际佛事用品展览会，用户注册验证邮件");
			Map<Object, Object> model = new HashMap<Object, Object>();
			model.put("verify", verify);
			mimeMessageHelper.setText(registMailTemplate.getMailHtmlText(model),true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Verify getVerify() {
		return verify;
	}

	public void setVerify(Verify verify) {
		this.verify = verify;
	}

	public RegistMailTemplate getRegistMailTemplate() {
		return registMailTemplate;
	}

	public void setRegistMailTemplate(RegistMailTemplate registMailTemplate) {
		this.registMailTemplate = registMailTemplate;
	}
}
