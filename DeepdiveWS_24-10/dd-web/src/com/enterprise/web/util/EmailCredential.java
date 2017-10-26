package com.enterprise.web.util;

import java.util.ResourceBundle;

public class EmailCredential {
	private static ResourceBundle messages = ResourceBundle.getBundle("mail");
	public static final String DATAUSERNAME = messages.getString("imap.datausername");
	public static final String DATAPASSWORD = messages.getString("imap.datapassword");
}
