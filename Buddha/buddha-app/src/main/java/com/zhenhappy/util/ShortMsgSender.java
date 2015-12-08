package com.zhenhappy.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jasson.im.api.APIClient;
import com.zhenhappy.system.SystemConfig;

/**
 * User: Haijian Liang Date: 13-11-18 Time: 下午9:59 Function:
 */
@Component
public class ShortMsgSender {
	private static Logger log = Logger.getLogger(ShortMsgSender.class);
	
	private static APIClient apiClient = null;
	/**
	 * 发送随即code到手机号码
	 * 
	 * @param phoneNumber
	 * @param code
	 */
    public static Boolean sender(String phoneNumber, String code, String url) {
        return senderBySms100(phoneNumber,code,url);
    }
    public static Boolean senderBySms100(String phoneNumber, String code, String url) {
        try {
        	log.info(phoneNumber + " 请求短信验证：" + code);
        	APIClient apiClient = new APIClient();
        	SystemConfig systemConfig = ApplicationContextUtil.getBean(SystemConfig.class);
        	apiClient.init(systemConfig.getVal("dbIP"), systemConfig.getVal("dbUser"), systemConfig.getVal("dbPwd"), systemConfig.getVal("apiCode"),systemConfig.getVal("dbName"),Integer.parseInt(systemConfig.getVal("dbPort")));
        	apiClient.setCharacterEncoding("UTF-8");
        	int ret=apiClient.sendSM(phoneNumber, code, 0);
        	apiClient.release();
            if (0 == ret) {
                return true;
            } else {
                log.error("发送验证码失败，错误码："+ret);
                return false;
            }
        } catch (Exception e) {
            log.error(phoneNumber + " 请求短信验证：" + code + " 失败。", e);
            return false;
        }
    }

    public static boolean getStatus(InputStream content){
        try{
            JAXBContext cxt = JAXBContext.newInstance(Sms100Response.class);
            Unmarshaller unmarshaller = cxt.createUnmarshaller();
            Sms100Response response = (Sms100Response) unmarshaller.unmarshal(content);
            log.info("send result:"+response.getMessage());
            if(response.getReturnstatus().toLowerCase().equals("success")){
                return true;
            }else{
                log.error("send short message error:"+response.getMessage());
                return false;
            }
        }catch (Exception e){
            try {
                log.error(IOUtils.toString(content));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            log.error("解析失败:",e);
            return false;
        }
    }

    @XmlRootElement(name = "returnsms")
    public static class Sms100Response{

        @XmlElement(name = "returnstatus")
        private String returnstatus;

        @XmlElement(name = "message")
        private String message;

        public String getReturnstatus() {
            return returnstatus;
        }


        public String getMessage() {
            return message;
        }

    }

    public static void main(String[] args) {
        senderBySms100("15080316454","感谢您注册中国厦门国际石材展手机客户端！验证码：287618请返回手机客户端进行验证。【厦门会展金泓信】","http://www.sms100.cc/sms.aspx");
    }
}
