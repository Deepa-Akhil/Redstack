package com.enterprise.common.util;

import java.util.Arrays;
import java.util.List;

import com.enterprise.common.Logger;

public class CSVFileContentUtils {
	
	private String[] csvFileContents;
	
	public CSVFileContentUtils(String[] csvFileContents) {
		this.csvFileContents = csvFileContents;
	}
		
	public String getNextLineWithValues(int fromIndex) {
		try {
			String nextLineFromIndex = csvFileContents[fromIndex + 1];
			if (!StringUtils.isEmpty(nextLineFromIndex)) {
				List<String> lineValuesCollection = Arrays.asList(CSVParser.getInstance().parse(nextLineFromIndex));
				if (!CollectionUtils.isEmpty(lineValuesCollection))
					return nextLineFromIndex;
			}
		} catch (IndexOutOfBoundsException e) {
			Logger.debug("No such index [] available under file contents ...", this.getClass());
		}
		return null;
	}
}