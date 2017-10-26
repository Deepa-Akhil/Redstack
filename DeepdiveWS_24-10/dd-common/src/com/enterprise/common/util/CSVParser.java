package com.enterprise.common.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  17 Mar 2014 10:25:32 AM
 * @author rafourie
 */
public class CSVParser {
	private static final CSVParser instance = new CSVParser();
	/*
     * This Pattern will match on either quoted text or text between commas, including
     * whitespace, and accounting for beginning and end of line.
     */
    private final Pattern csvPattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:,|$)");	
    private ArrayList<String> allMatches = null;	
    private Matcher matcher = null;
    //private String match = null;
    private int size;

    private CSVParser() {		
    	allMatches = new ArrayList<String>();
    	matcher = null;
    	//match = null;
    }

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

	public static CSVParser getInstance() {
		return instance;
	}
}