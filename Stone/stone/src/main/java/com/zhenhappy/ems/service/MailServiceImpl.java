/*
 *    Copyright 2014-2015 The Happy Network Corporation
 */
package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dao.SendMailDetailDao;
import com.zhenhappy.ems.dao.SendMailDetailDaoImp;
import com.zhenhappy.ems.email.Email;
import com.zhenhappy.ems.entity.TEmailSendDetail;
import com.zhenhappy.util.Page;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装 Spring 集成的邮件发送服务实现类
 * <p/>
 * 支持异步发送 利用Spring框架封装的JavaMail现实同步或异步邮件发送
 * spring 会负责每次发送后正确关闭 transport
 *
 * @author 吴剑斌
 * @project spring-mail
 * @create 2012-3-24 上午12:29:07
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    JavaMailSender mailSender;// 注入Spring封装的javamail，Spring的xml中已让框架装配
    @Autowired
    TaskExecutor taskExecutor;// 注入Spring封装的异步执行器
    @Autowired
    FreeMarkerConfigurer freeMarker;// 注入FreeMarker模版封装框架

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SendMailDetailDao sendMailDetailDao;

    /**
     * 同步发送
     */
    public void sendMailBySynchronizationMode(Email email) throws Exception {
        if (email.getReceivers() == null) {
            throw new IllegalArgumentException("收件人不能为空");
        }
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "utf-8");

        try {
            String receivers = email.getReceivers().replaceAll("\\;", ",");
            // 设置收件人，寄件人
            InternetAddress[] toAddress = InternetAddress.parse(receivers);
            mailMessage.setRecipients(Message.RecipientType.TO, toAddress); // 发送给多个账号
            messageHelper.setFrom(email.getFromAddress()); // 发件人
            messageHelper.setSubject(email.getSubject()); // 主题
            // true 表示启动HTML格式的邮件
            messageHelper.setText(getMailText(email), true); // 邮件内容，注意加参数true，表示启用html格式

            // 发送邮件
            mailSender.send(mailMessage);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 异步发送
     */
    public void sendMailByAsynchronousMode(final Email email, Integer eid) {
        if (logger.isDebugEnabled()) {
            logger.debug("当前邮件采取异步发送..");
        }
        final Integer id = saveSendDetail(email, eid);
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(email);
                    updateResult(true, id);
                    logger.info("邮件发送耗时任务完成");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    updateResult(false, id);
                }
            }
        });
    }

    public void retrySendMailByAsynchronousMode(final Email email, final Integer emailId) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(email);
                    updateResult(true, emailId);
                    logger.info("邮件发送耗时任务完成");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    updateResult(false, emailId);
                }
            }
        });
    }

    @Transactional
    private void updateResult(boolean sendResult, Integer detailId) {
        jdbcTemplate.update("update t_email_send_detail set result=? where id = ?", new Object[]{sendResult ? 1 : 0, detailId});
    }

    @Transactional
    private Integer saveSendDetail(Email email, Integer eid) {
        TEmailSendDetail detail = new TEmailSendDetail();
        detail.setEid(eid);
        detail.setAddress(email.getReceivers());
        detail.setCompanyName(email.getName());
        detail.setSendDate(new Date());
        detail.setLanguage(email.getFlag());
        detail.setGender(email.getGender());
        hibernateTemplate.save(detail);
        return detail.getId();
    }

    /**
     * 或者直接使用 spring 3.0 的异步框架 只需使用 @Async 注解, 这里的代码直接会用异步线程来运行。
     * 需要激活 <!-- 注解异步任务驱动 -->
     * <task:annotation-driven/>
     * 详细配置请见： spring-mail.xml
     */
    @Async
    public void sendMailByAsyncAnnotationMode(final Email email) {
        try {
            logger.debug("当前邮件采取spring 3 @Async注解异步任务驱动发送..");
            sendMailBySynchronizationMode(email);
            logger.info("邮件发送耗时任务完成");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void loadDetailByEid(Page page, Integer eid) {
        page.setDatas(sendMailDetailDao.queryPageByHQL("select count(*) from TEmailSendDetail where eid=?", "from TEmailSendDetail where eid=? order by sendDate desc", new Object[]{eid}, page));
    }

    @Override
    public TEmailSendDetail loadMailByMid(Integer mid, Integer eid) {
        List<TEmailSendDetail> results = sendMailDetailDao.queryByHql("from TEmailSendDetail where id=? and eid=?",new Object[]{mid,eid});
        if(results.size()>0){
            return results.get(0);
        }else{
            return null;
        }
    }

    /**
     * 通过模板构造邮件内容，参数content将替换模板文件中的${content}标签。
     */
    private String getMailText(Email email) throws Exception {
        // 通过指定模板名获取FreeMarker模板实例
        Template template = null;
        if (email.getFlag() == 2) {
            template = freeMarker.getConfiguration().getTemplate("mail/mailTemplate2.html");
        }else{
            template = freeMarker.getConfiguration().getTemplate("mail/mailTemplate1.html");
        }


        // FreeMarker通过Map传递动态数据
        Map<Object, Object> model = new HashMap<Object, Object>();
        model.put("email", email); // 注意动态数据的key和模板标签中指定的属性相匹配

        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        return htmlText;
    }


}
