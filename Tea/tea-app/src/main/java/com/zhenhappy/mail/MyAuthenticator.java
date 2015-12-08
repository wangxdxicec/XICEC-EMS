package com.zhenhappy.mail;

/**
 * 
 */
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @version 1.00.00
 * @description: 
 * @author: 梁海舰
 * @date: 2012-6-17
 * @history:
 */
public class MyAuthenticator extends Authenticator{
	String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}
