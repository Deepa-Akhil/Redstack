package com.enterprise.web.html;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.enterprise.common.enums.LinkItemTypes;
import com.enterprise.common.enums.RequestURITypes;
import com.enterprise.common.interfaces.IRecord;
import com.enterprise.common.pojo.SearchResultRecordBean;
import com.enterprise.common.util.IOUtils;
import com.enterprise.common.util.StringUtils;

@Component
public class HtmlFileReader {
	public void searchFilesForKeywords(final File folder, String relativePath, List<String> keywords, List<IRecord> results, String lang) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	String localRelativePath = relativePath + "/" + fileEntry.getName();
	        	searchFilesForKeywords(fileEntry, localRelativePath, keywords, results, lang);
	        } else {
	        	try {
	        		byte[] fileBytes = IOUtils.read(fileEntry);
	        		String fileContents = new String(fileBytes);
	        		String metaLang = readMetaName("lang", fileContents,fileContents.length(),0);
	        		if (lang.equalsIgnoreCase(metaLang)) {
	        			String metaDescription = readMetaName("description", fileContents,fileContents.length(),0);
		        		if (!StringUtils.isEmpty(metaDescription)) {
		        			boolean fileContainsKeywords = metaDescriptionContainsKeyword(metaDescription, keywords);
		        			if (fileContainsKeywords) {
		        				String title = readTitleText(fileContents);
		        				String fileName = fileEntry.getName();
		        				String fileRelativePath = relativePath + "/" + fileName;
		        				RequestURITypes requestUriType = RequestURITypes.valueOfByRequestURI(fileRelativePath);
		        				if (requestUriType != null) {
		        					String mapping = requestUriType.getRequestMapping();
		        					LinkItemTypes linkItemType = requestUriType.getMenuItemType();
		        					SearchResultRecordBean bean = new SearchResultRecordBean();
		        					bean.setMapping(mapping);
		        					bean.setTitle(title);
		        					bean.setDescription(metaDescription);
		        					bean.setMenuId(linkItemType.getMenuId());
		        					results.add(bean);
		        				}
		        			}
		        		}
	        		}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
	        }
	    }
	}
	
	private String readTitleText(String fileContents) {
		String fileContentsLCase = fileContents.toLowerCase();
		int titleStartIndex = fileContentsLCase.indexOf("title");
		if (titleStartIndex >= 0) {
			int titleEndIndex = fileContentsLCase.indexOf("</", titleStartIndex);
			if (titleEndIndex >= titleStartIndex) {
				String titleUnformatted = fileContents.substring(titleStartIndex, titleEndIndex);
				int gtIndex = titleUnformatted.indexOf(">");
				if (gtIndex >= 0) {
					int titleTextStartIndex = gtIndex + 1;
					return titleUnformatted.substring(titleTextStartIndex);
				}
			}
		}
		return null;
	}
	
	private String readMetaName(String name, String fileContents, int fileLength, int startIndex) {
		String fileContentsLCase = fileContents.toLowerCase();
		int metaStartIndex = fileContentsLCase.indexOf("meta", startIndex);
		if (metaStartIndex >= 0) {
			int metaEndIndex = fileContentsLCase.indexOf("<", metaStartIndex) - 1;
			if (metaEndIndex >= metaStartIndex) {
				String metaUnformatted = fileContents.substring(metaStartIndex, metaEndIndex);
				String metaName = readMetaTagAttribute(metaUnformatted, "name");
				if (name.equalsIgnoreCase(metaName)) {
					return readMetaTagAttribute(metaUnformatted, "content");
				} else {
					return readMetaName(name, fileContents, fileLength, metaEndIndex);
				}
			}
		}
		return null;
	}
	
	private String readMetaTagAttribute(String metaStr, String attr) {
		int nameIndex = metaStr.indexOf(attr);
		if (nameIndex >= 0) {
			int firstDQuoteIndex = metaStr.indexOf("\"", nameIndex);
			if (firstDQuoteIndex > nameIndex) {
				int attrValueStartIndex = firstDQuoteIndex + 1;
				int lastDQuoteIndex = metaStr.indexOf("\"", attrValueStartIndex);
				if (lastDQuoteIndex > attrValueStartIndex) {
					String attrValue = metaStr.substring(attrValueStartIndex, lastDQuoteIndex);
					return attrValue;
				}
			}
		}
		return null;
	}
	
	private boolean metaDescriptionContainsKeyword(String metaDescription, List<String> keywords) {
		String metaDescriptionLCase = metaDescription.toLowerCase();
		for (String keyword : keywords) {
			int keywordIndex = metaDescriptionLCase.indexOf(keyword.toLowerCase());
			if (keywordIndex >= 0) {
				return true;
			}
		}
		return false;
	}
}