package com.enterprise.web.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailOutBox {

	public static void main(String[] args) throws Exception {
		//sendUserDetailMail1("akhil2126@gmail.com");
	}

	@SuppressWarnings("rawtypes")
	public static void sendUserDetailMail1(String eMailId, String name, List infoMessage,
			List errorOrSuccMsg, String errorType, String loadType, String packageName, Long pkgID) throws Exception{
		String userName = EmailCredential.DATAUSERNAME;
		String pwd = EmailCredential.DATAPASSWORD;
		
		final String username = userName;
		final String password = pwd;
 
		Properties props = new Properties();
		props.put("mail.smtp.user",eMailId); 
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
		props.setProperty("mail.smtp.port", "465"); 
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		javax.mail.Session session = javax.mail.Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(eMailId));
			message.setSubject(loadType+" Data Load Confirmation");
			StringBuffer msgBody = new StringBuffer("<h4>Hi "+name+",</h4>");
			msgBody.append("Your file for "+packageName+" (PackageID : "+pkgID+") has been successfully loaded to the cloud servers.");
			if(infoMessage!=null){
				msgBody.append("<div style=\"margin-top:4px; margin-bottom:4px;"
						+ "border-top:1px solid green; border-bottom:1px solid green;"
						+ "padding:0 0 0 10px;background-color:rgb(233,243,233);height: 63px;\">");
				for (Object object : infoMessage) 
					msgBody.append("<div style=\"margin-top:5px; color:green;\">"+object+"</div>");
				msgBody.append("</div>");
			}
			if(errorType.equals("Success")){
				msgBody.append("<br/>");
				msgBody.append("File processed successfully.");
				msgBody.append("<br/>");
			} else if(errorType.equals("Errors")){
				msgBody.append("<div style=\"overflow-x:hidden;overflow-y:auto;\">");
				msgBody.append("<span style=\"color:red;\">File processing failed:</span><br/><br/>");
				for (Object object : errorOrSuccMsg) {
					msgBody.append("<span style=\"color:red;\">"+object+"</span>");
					msgBody.append("<br/>");
				}
				msgBody.append("</div>");
			} else if(errorType.equals("Warning")){
				msgBody.append("<div style=\"overflow-x:hidden;overflow-y:auto;\">");
				msgBody.append("<br/>");
				msgBody.append("<span style=\"color:green;\">File processed successfully, but with warnings:</span>");
				msgBody.append("<br/>");
				msgBody.append("<br/>");
				for (Object object : errorOrSuccMsg) {
					msgBody.append("<span style=\"color:#ff7900;\">"+object+"</span>");
					msgBody.append("<br/>");
				}
				msgBody.append("</div>");
			}
			message.setContent(msgBody.toString(),"text/html");
 
			Transport.send(message);
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
