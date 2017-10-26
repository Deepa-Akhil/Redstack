package com.enterprise.web.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/*public static void main(String[] args) throws IOException {
		String[] retarr = new String[]{"Current Status Date#!#!Last Remark","41712.0#!#!"};
		
		Test test = new Test();
		retarr = test.parse("Current Status Date#!#!Last Remark");	
		for (String string : retarr) {
			System.out.println("====  >> "+string);
			//System.out.println("          #### "+string.split("#!#!").length);
		}
		String string = "41712.0#!#!";
		String[] nextLineValues = string.split("#!#!");
		if(string.endsWith("#!#!")){
			List<String> a = new ArrayList<String>(Arrays.asList(nextLineValues));
			a.add(null);
			nextLineValues = a.toArray(new String[a.size()]);
		}
		System.out.println("----nextLineValues------"+nextLineValues);
		//System.out.println("FROM:  " + address.toString());
		StringBuffer  address = new StringBuffer("\"Reynolds, Alastair\" <AReynolds@go2uti.com>");
		String fromAddress;
		int ii = address.toString().split("<").length;
		if(address.toString().split("<").length>1)
			fromAddress = address.toString().split("<")[1].replaceAll(">", "");
		else
			fromAddress = address.toString();
		System.out.println("-----fromAddress-----"+fromAddress);
	}*/
	
	 private final Pattern csvPattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:,|$)");	
	    private ArrayList<String> allMatches = new ArrayList<String>();;	
	    private Matcher matcher = null;
	    //private String match = null;
	    private int size;
	
	public String[] parse(String csvLine) {
    	matcher = csvPattern.matcher(csvLine);
    	allMatches.clear();
    	String match;
    	while (matcher.find()) {
    		match = matcher.group(1);
    		if (match!=null)
    			allMatches.add(match);
    		else
    			allMatches.add(matcher.group(2));
    	}
    	size = allMatches.size();		
    	if (size > 0)
    		return allMatches.toArray(new String[size]);
    	else
    		return new String[0];		
    }
	
	public static void main(String[] args) {
		byte ee = 81;
		System.out.println("----------"+ee);
		try {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("----    TTT    ------");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
