package com.enterprise.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;

public class GetToken {

	public static String getTrustedTicket(String wgserver, String user, String remoteAddr) 
    	throws ServletException {
    OutputStreamWriter out = null;
    BufferedReader in = null;
    try {
        // Encode the parameters
        StringBuffer data = new StringBuffer();
        data.append(URLEncoder.encode("username", "UTF-8"));
        data.append("=");
        data.append(URLEncoder.encode(user, "UTF-8"));
        data.append("&");
        data.append(URLEncoder.encode("client_ip", "UTF-8"));
        data.append("=");
        data.append(URLEncoder.encode(remoteAddr, "UTF-8"));
        
        // Send the request
        URL url = new URL(wgserver);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        out = new OutputStreamWriter(conn.getOutputStream());
        out.write(data.toString());
        out.flush();
        
        // Read the response
        StringBuffer rsp = new StringBuffer();
        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ( (line = in.readLine()) != null) {
            rsp.append(line);
        }
        
        return rsp.toString();
        
    } catch (Exception e) {
        throw new ServletException(e);
    }
    finally {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
        }
        catch (IOException e) {}
    }
}

public static void main(String[] args) throws ServletException {
	String op1 = GetToken.getTrustedTicket("http://54.213.71.67/trusted", "Adminstrator", "134.213.24.67");
	String op2 = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/ActiveContainerManagement/ContainerDeliverySummary?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
	String op3 =GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/ContainerHistory/ContainerManagementSummary?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
	System.out.println("------1---=="+op1);
	System.out.println("------2---=="+op2);
	System.out.println("----------"+op3);
}
}
