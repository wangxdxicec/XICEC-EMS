package com.zhenhappy.mail;


import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.zhenhappy.entity.Verify;

/**
 * 作用:
 * @author 梁海舰
 * 2012下午10:52:31
 */
public class FindBackMailSender implements Runnable{

	private Verify verify;
	private JavaMailSender javaMailSender;
	private FindBackMailTemplate findBackMailTemplate;
	
	public FindBackMailSender(Verify verify, JavaMailSender javaMailSender,FindBackMailTemplate findBackMailTemplate) {
		super();
		this.verify = verify;
		this.javaMailSender = javaMailSender;
		this.findBackMailTemplate = findBackMailTemplate;
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
			mimeMessageHelper.setSubject("Xiamen Buddha fair. Find back password email.");
			Map<Object, Object> model = new HashMap<Object, Object>();
			model.put("verify", verify);
			mimeMessageHelper.setText(findBackMailTemplate.getMailHtmlText(model),true);
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

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public FindBackMailTemplate getFindBackMailTemplate() {
		return findBackMailTemplate;
	}

	public void setFindBackMailTemplate(FindBackMailTemplate findBackMailTemplate) {
		this.findBackMailTemplate = findBackMailTemplate;
	}

}
