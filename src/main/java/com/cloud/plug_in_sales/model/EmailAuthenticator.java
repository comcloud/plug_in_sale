package com.cloud.plug_in_sales.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * EmailAuthenticator 
 * �̳���Authenticator
 * @author 张玉雷
 */
public class EmailAuthenticator extends Authenticator {
	private String username = null;   
	  
    private String userpass = null;   
  
    void setUsername(String username) {   
        this.username = username;   
    }   
  
    void setUserpass(String userpass) {   
    	this.userpass = userpass;   
    }   
  
    public EmailAuthenticator(String username, String userpass) {   
        super();   
        setUsername(username);   
        setUserpass(userpass);
    }
    
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, userpass);
    }
}
