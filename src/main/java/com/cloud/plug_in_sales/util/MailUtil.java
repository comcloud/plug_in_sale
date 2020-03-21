package com.cloud.plug_in_sales.util;

import com.cloud.plug_in_sales.model.EmailAuthenticator;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 /**
  * @author 张玉雷
  */
 @SuppressWarnings("unused")
public class MailUtil {

    private final static String SENDER_ADDRESS = "2230817302@qq.com";
    private final static String SENDER_ACCOUNT = "2230817302@qq.com";
    private final static String SENDER_PASSWORD = "evdqabwwvrdxecac";
    private final static String MAIL_SERVER = "smtp.qq.com";
    private final static String MAIL_SUBJECT = "所需密钥";

     /**
      * @param receiveAddress 接收者地址
      * @param mailContent 密钥内容
      */
    public static void send(String receiveAddress,String mailContent){
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", MAIL_SERVER);
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        EmailAuthenticator mailauth =   new EmailAuthenticator(SENDER_ACCOUNT, SENDER_PASSWORD);
        Session mailSession = Session.getInstance(prop, mailauth);
        try {
        	MimeMessage message = getMimeMessage(mailSession,receiveAddress,mailContent);
        	Transport tran = mailSession.getTransport("smtp");
        	Transport.send(message, message.getAllRecipients());
        	tran.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage getMimeMessage(Session session,String receiveAddress,String mailContent){
        MimeMessage message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(SENDER_ADDRESS));
			message.setRecipient(Message.RecipientType.TO,
					             new InternetAddress(receiveAddress));
			message.setSubject("感谢购买，你所需要的密钥如下：\n" + MAIL_SUBJECT);
			message.setContent(mailContent, "text/html;charset=utf8");
			message.setSentDate(new Date());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return message;

    }
}   
