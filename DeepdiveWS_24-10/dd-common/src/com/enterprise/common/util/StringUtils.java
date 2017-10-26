/*
 * Copyright (c) 2012, Momentum Africa / Metropolitan International (MMI) Pty.
 *
 * The program(s) herein may be used and/or copied only with the written permission of 
 * Momentum Africa Pty, (Metropolitan International, MMI, Pty) or in accordance with the 
 * terms and conditions stipulated in the agreement/contract under which the program(s) 
 * have been supplied. 
 */
package com.enterprise.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

/**
 * String utilities class
 * 
 * @since  2012/02/21
 * @author Ramon Fourie
 * 
 * ${Class: Entity.java}		{Modified: yyyy/mm/dd}	${Created/Modified}: ${Username}
 */
public final class StringUtils {
	
	public static String upper(String str) {
		if (!StringUtils.isEmpty(str))
			return str.toUpperCase();
		return str;
	}
	
	public static Double asDouble(String str) {
		if (!StringUtils.isEmpty(str)) {
			str = str.replaceAll(Constants.SPACE_STR, Constants.EMPTY_STR)
					.replaceAll(",", "");
				int lastDotIndex = str.lastIndexOf(".");
				if(lastDotIndex>=0){
					String precision = str.substring(0, lastDotIndex);
					String scale = str.substring(lastDotIndex, str.length());
					precision = precision.replaceAll("\\.", Constants.EMPTY_STR);
					str = new StringBuilder(precision).append(scale).toString();
				} else {
					Double.valueOf(str);
				}
				return Double.valueOf(str);
		} else
			return null;
	}
	
	public static Long asLong(String str) {
		if (!StringUtils.isEmpty(str)) {
			str = str.replaceAll(Constants.SPACE_STR, Constants.EMPTY_STR)
				.replaceAll("\\,", Constants.EMPTY_STR)
				.replaceAll("\\.", Constants.EMPTY_STR);
			return Long.valueOf(str);
		} else
			return null;
	}
	
	public static Integer asInteger(String str) {
		if (!StringUtils.isEmpty(str)) {
			str = str.replaceAll(Constants.SPACE_STR, Constants.EMPTY_STR)
				.replaceAll(",", Constants.EMPTY_STR)
				.replaceAll(".", Constants.EMPTY_STR);
			return Integer.valueOf(str);
		} else
			return null;
	}
	
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals(Constants.EMPTY_STR) || str.trim().equalsIgnoreCase(Constants.NULL_STR)) {
			return true;
		} else {
			return false;
		}
	}
		
	public static String nullWhenEmpty(String str) {
		if (isEmpty(str)) {
			return null;
		} else {
			return str;
		}
	}
	
	public static String emptyWhenNull(String str) {
		if (isEmpty(str)) {
			return Constants.EMPTY_STR;
		} else {
			return str;
		}
	}
	
	public static String spaceWhenNull(String str) {
		if (isEmpty(str)) {
			return Constants.HTML_SPACE_STR;
		} else {
			return str;
		}
	}
	
	public static String trimToLength(String value, int length) {
		if (!isEmpty(value)) {
			value = value.trim();
			if (value.length() > length) {
				value = value.substring(0,length);
			}
		}
		return value;
	}
	
	public static String trim(String value) {
		if (!isEmpty(value)) {
			return value.trim();
		} else {
			return value;
		}
	}
	
	public static String noQuotes(String value) {
		if (!isEmpty(value)) {
			return value.replaceAll("\"", Constants.EMPTY_STR);
		} else {
			return value;
		}
	}
	
	public static String arrToString(String[] arr) {
		StringBuffer strBuf = new StringBuffer("[");
		if (arr != null) {
			for (int i=0; i<arr.length; i++) {
				String str = arr[i];
				if (i==0) { //first item?
					strBuf.append(str);
				} else {
					strBuf.append(","+str);
				}
			}
			strBuf.append("]");
			return strBuf.toString();
		} else {
			return "[]";
		}
	}
	
	public static String listToString(List<String> list) {
		if (list != null) {
			return arrToString(list.toArray(new String[list.size()]));	
		} else {
			return "[]";
		}
	}
	
	public static String stripLineBreaks(String message) {
		String replaceWith = Constants.EMPTY_STR;
		int len = message.length();
		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			char c = message.charAt(i);
			// skip \n, \r, \r\n
			switch (c) {
				case '\n':
					buffer.append(replaceWith);
	                break;
				case '\t':
					buffer.append(replaceWith);
	                break;
				case '\r': // do lookahead
					if (i + 1 < len && message.charAt(i + 1) == '\n') {
						i++;
					}
	                buffer.append(replaceWith);
	                break;
				default:
					buffer.append(c);
			}
		}
		String bufferStr = buffer.toString();
		return bufferStr.trim();
	}
	
	public static String[] stringToArr(String str, String delim) {
		if (!isEmpty(str) && !isEmpty(delim)) {
			StringTokenizer tokenizer = new StringTokenizer(str, delim);
			List<String> elements = new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				String element = (String)tokenizer.nextElement();
				elements.add(element.trim());
			}
			return elements.toArray(new String[elements.size()]);
		}
		return null;
	}
	
	public static String stripCityNameFromCountry(String cityCountryComboStr) {
		if (!isEmpty(cityCountryComboStr)) {
			String specialChar = ",";
			int ixSpecialChar = cityCountryComboStr.indexOf(specialChar);
			if (ixSpecialChar > 0) {
				String cityName = cityCountryComboStr.substring(0, ixSpecialChar);
				return cityName.trim();
			} else {
				return cityCountryComboStr.trim();
			}
		} else {
			return emptyWhenNull(null);		
		}
	}
		
	public static int parseInt(String strValue) {
		int value = 0;
		try {
			if (!StringUtils.isEmpty(strValue)) {
				value = Integer.parseInt(strValue);
			}
		} catch (Exception e) {
			System.out.println("Warning: value [" + strValue + "] could not be parsed to an integer value.");
		}
		return value;
	}
	
	public static long parseLong(String strValue) {
		long value = 0L;
		try {
			if (!StringUtils.isEmpty(strValue)) {
				value = Long.parseLong(strValue);
			}
		} catch (Exception e) {
			System.out.println("Warning: value [" + strValue + "] could not be parsed to a long value.");
		}
		return value;
	}
	
	public static boolean parseBoolean(String strValue) {
		if (!StringUtils.isEmpty(strValue) && strValue.length() == 1) {
			if (strValue.equals("0")) {
				return false;
			} else if (strValue.equals("1")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static String upperAlphaNumericOnly(String str) {
		StringBuffer returnStrBuf = new StringBuffer();
		String[] alphaNumericArr = new String[] {
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"
		};
		List<String> alphaNumericList = Arrays.asList(alphaNumericArr);
		if (!isEmpty(str)) {
			str = str.toUpperCase();
			String charAt = null;
			for (int i=0; i<str.length(); i++) {
				charAt = str.substring(i,i+1);
				if (alphaNumericList.contains(charAt)) {
					returnStrBuf.append(charAt);
				}
			}
		}
		return returnStrBuf.toString();
	}
	
	public static String hashTableToString(Hashtable<String,String> values) {
		StringBuffer returnStrBuf = new StringBuffer();
		if (values != null) {
			Enumeration<String> keys = values.keys();
			int i=0;
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = values.get(key);
				if (i++ == 0) {
					returnStrBuf.append(key + "=" + value);	
				} else {
					returnStrBuf.append("," + key + "=" + value);
				}
			}
		}
		return returnStrBuf.toString();
	}
	
	public static Long defaultLongWhenNull(Long value) {
		if (value == null) {
			return Long.valueOf(-1L);
		} 
		return value;
	}
	
	public static String formatForCase(String str) {
		if (isEmpty(str)) {
			return emptyWhenNull(str);
		} else {
			StringBuilder wordBuilder = new StringBuilder();
			String firstChar = str.substring(0,1);
			if (!isEmpty(firstChar)) {
				firstChar = firstChar.toUpperCase();
				wordBuilder.append(firstChar);
				String lastChars = str.substring(1, str.length());
				if (!isEmpty(lastChars)) {
					lastChars = lastChars.toLowerCase();							
					wordBuilder.append(lastChars);
				}
			}
			return wordBuilder.toString();
		}
	}
	
	public static String initCaps(String str) throws IOException {
		
		StringReader in = new StringReader(str);
		boolean isNextCharSpace = true;
		StringBuffer proper = new StringBuffer(); 
		int i=0;
		while((i=in.read())!=-1) 
		{ 
			char c = (char)i; 
			if (c == ' ') {
				proper.append(c);
				isNextCharSpace = true;
			} 
			else {
				if (isNextCharSpace) { 
					if(i>=97 && i<=122) {
						i=i-32;
						proper.append((char)i);
					}
					else {
						proper.append(c); 
					}
				} 
				else { 
					if(i>=65 && i<=90)
					{
						i=i+32;
						proper.append((char)i);
					}
					else {
						proper.append(c); 
					}
				}

				isNextCharSpace = false;
			}
		}
		return proper.toString(); 
	}

	public static String spacelessLowerCase(String value) {
		String modifiedValue = null;
		if (!StringUtils.isEmpty(value))
			modifiedValue = value.replaceAll(Constants.SPACE_STR, Constants.EMPTY_STR).toLowerCase();
		return modifiedValue;
	}
	
	public static String abbreviateString(String input, int maxLength) {
	    if (input.length() <= maxLength) 
	        return input;
	    else 
	        return input.substring(0, maxLength-2) + "..";
	}
}