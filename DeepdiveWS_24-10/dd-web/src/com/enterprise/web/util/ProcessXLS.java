package com.enterprise.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.enterprise.domain.entity.SubPackageDetail;

public class ProcessXLS {

	private ProcessXLS() {}
	
	public static void main(String[] args) throws IOException {
		File myFile = new File("C:\\Users\\ITPOINT\\Downloads\\" + "Copy of TransportActivityExtract 1708.xlsx");
		InputStream fis = new FileInputStream(myFile);
		//String fileContent = IOUtils.toString(fis, "UTF-8");
		//String textStr[] = fileContent.split("\\r?\\n");
		//System.out.println("----------"+textStr);
		SubPackageDetail detail = new SubPackageDetail();
		detail.setMinFillesCells(3);
		detail.setRowsToRemove(0);
		String[] retarr = ProcessXLS.processGeneralXLSFile(detail,12, fis);
		retarr = ProcessFile.checkForQuotes(retarr);
		System.out.println("       length "+retarr.length);
		for (String string : retarr) {
			System.out.println("====  >> "+string);
			System.out.println("          #### "+string.split("#!#!").length);
		}
		//ProcessXLS.CSVWrighter("/home/cynere/Documents/PROJECT_FILES/DEEPDIVE/csvfile.csv");
	}
	
	public static String[] processXLSFile(String path,int romovedRows) throws IOException{
		File myFile = new File(path);
		String content[] = new String[1000];
		List<String> contentList = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Workbook myWorkBook = null;
        try {
        	/*File alreadyExists = new File(filePath+"Rate"+userId+".csv");
        	if(alreadyExists.exists()){
        		alreadyExists.delete();
        	}*/
        	
        	InputStream fis = new FileInputStream(myFile);
        	myWorkBook = WorkbookFactory.create(fis);
        	org.apache.poi.ss.usermodel.Sheet mySheet = myWorkBook.getSheetAt(0);
        	Iterator<Row> rowIterator = mySheet.iterator();
        	for (int i = 1; i < romovedRows-1; i++) {
        		rowIterator.next();
			}
        	//CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath+"Rate"+userId+".csv", true), ',');
        	while (rowIterator.hasNext()) {
        		Row row = rowIterator.next();
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int previousCell = -1;
        		int currentCell = 0;
        		StringBuffer rowBuff = new StringBuffer();
        		while (cellIterator.hasNext()) {
        			Cell cell = cellIterator.next();
        			currentCell = cell.getColumnIndex();
        	        if (previousCell != currentCell-1) {
        	        	int diff = currentCell-previousCell-1;
        	        	for (int i = 0; i < diff; i++) {
        	        		rowBuff.append("#!#!");
						}
        	        }
        	        previousCell = currentCell;
	        	    switch (cell.getCellType()) {
	        	        case Cell.CELL_TYPE_STRING:
	        	        	//csvOutput.write(cell.getStringCellValue());
	        	        	rowBuff.append(cell.getStringCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_NUMERIC:
	        	        	//csvOutput.write(cell.getNumericCellValue()+"");
	        	        	rowBuff.append(cell.getNumericCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BOOLEAN:
	        	        	//csvOutput.write(cell.getBooleanCellValue()+"");
	        	        	rowBuff.append(cell.getBooleanCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BLANK:
	        	        	//csvOutput.write("");
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	        default :
	        	    }
        		}
        		String rowStr = rowBuff.toString();
        		if(rowStr.endsWith("#!#!")) {
        			rowStr= rowStr.substring(0, rowStr.length() - 4);
        		}
        		contentList.add(rowStr);
        	}
        	contentList.toArray(content);
        } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(myWorkBook!=null)
				myWorkBook.close();
		}
        return clean(content);
	}
	
	public static String[] processContainerXLSFile(int romovedRows, long userId, InputStream fis) throws IOException{
		//File myFile = new File(filePath+"Rate"+userId+".xlsx");
		String content[] = new String[fis.available()];
		List<String> contentList = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Workbook myWorkBook = null;
        try {
        	/*File alreadyExists = new File(filePath+"Rate"+userId+".csv");
        	if(alreadyExists.exists()){
        		alreadyExists.delete();
        	}*/
        	
        	//fis = new FileInputStream(myFile);
        	myWorkBook = WorkbookFactory.create(fis);
        	org.apache.poi.ss.usermodel.Sheet mySheet = myWorkBook.getSheetAt(0);
        	Iterator<Row> rowIterator = mySheet.iterator();
        	for (int i = 1; i < romovedRows-1; i++) {
        		rowIterator.next();
			}
        	//CsvWriter csvOutput = new CsvWriter(new FileWriter(filePath+"Rate"+userId+".csv", true), ',');
        	while (rowIterator.hasNext()) {
        		Row row = rowIterator.next();
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int previousCell = -1;
        		int currentCell = 0;
        		StringBuffer rowBuff = new StringBuffer();
        		while (cellIterator.hasNext()) {
        			Cell cell = cellIterator.next();
        			currentCell = cell.getColumnIndex();
        	        if (previousCell != currentCell-1) {
        	        	//System.out.print("\t");
        	        	//csvOutput.write(",");
        	        	int diff = currentCell-previousCell-1;
        	        	for (int i = 0; i < diff; i++) {
        	        		rowBuff.append("#!#!");
						}
        	        }
        	        previousCell = currentCell;
	        	    switch (cell.getCellType()) {
	        	        case Cell.CELL_TYPE_STRING:
	        	        	//csvOutput.write(cell.getStringCellValue());
	        	        	rowBuff.append(cell.getStringCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_NUMERIC:
	        	        	//csvOutput.write(cell.getNumericCellValue()+"");
	        	        	//rowBuff.append(cell.getNumericCellValue()+"#!#!");
	        	        	 if (HSSFDateUtil.isCellDateFormatted(cell)) {
	     	        	    	Date date = cell.getDateCellValue();
	     	        	    	String strValue = new CellDateFormatter("d/mm/yyyy").format(date);
	     	        	    	rowBuff.append(strValue+"#!#!");
	     	        	    } else {
	     	        	    	rowBuff.append(cell.getNumericCellValue()+"#!#!");
	     	        	    }
	        	        	break;
	        	        case Cell.CELL_TYPE_BOOLEAN:
	        	        	//csvOutput.write(cell.getBooleanCellValue()+"");
	        	        	rowBuff.append(cell.getBooleanCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BLANK:
	        	        	//csvOutput.write("");
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	        default :
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	    }
        		}
        		String rowStr = rowBuff.toString();
        		if(rowStr.endsWith("#!#!")) {
        			rowStr= rowStr.substring(0, rowStr.length() - 4);
        		}
        		contentList.add(rowStr);
        		//csvOutput.endRecord();
        		
        		//System.out.println("");
        	}
        	//csvOutput.close();
        	//fis.close();
        	contentList.toArray(content);
        } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(myWorkBook!=null)
				myWorkBook.close();
		}
        return clean(content);
	}
	
	public static String[] processXLSFile(SubPackageDetail subPackageDetail, long userId, InputStream fis) throws IOException{
		int rowsToRemove = subPackageDetail.getRowsToRemove();
		int columnsToRemove = subPackageDetail.getColumnsToRemove();
		String content[] = new String[fis.available()];
		List<String> contentList = new ArrayList<String>();
		XSSFWorkbook myWorkBook = null;
        try {
        	myWorkBook = new XSSFWorkbook (fis);
        	XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        	Iterator<Row> rowIterator = mySheet.iterator();
        	for (int i = 1; i < rowsToRemove-1; i++) {
        		rowIterator.next();
			}
        	while (rowIterator.hasNext()) {
        		Row row = rowIterator.next();
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int previousCell = columnsToRemove - 1;
        		int currentCell = 0;
        		StringBuffer rowBuff = new StringBuffer();
        		while (cellIterator.hasNext()) {
        			Cell cell = cellIterator.next();
        			currentCell = cell.getColumnIndex();
        			if (previousCell != currentCell-1 && currentCell>columnsToRemove) {
        	        	int diff = currentCell-previousCell-1;
        	        	for (int i = 0; i < diff; i++) {
        	        		rowBuff.append("#!#!");
						}
        	        }
        	        previousCell = currentCell;
	        	    switch (cell.getCellType()) {
	        	        case Cell.CELL_TYPE_STRING:
	        	        	rowBuff.append(cell.getStringCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_NUMERIC:
	        	        	 if (HSSFDateUtil.isCellDateFormatted(cell)) {
	     	        	    	Date date = cell.getDateCellValue();
	     	        	    	String dateFmt = cell.getCellStyle().getDataFormatString();
	     	        	    	if(dateFmt.equals("[$-409]d\\-mmm\\-yy;@") || dateFmt.equals("[$-409]dd\\-mmm\\-yy;@")){
	     	        	    		dateFmt = "d-mmm-yyyy";
	     	        	    	}
	     	        	    	String strValue = new CellDateFormatter(dateFmt).format(date);
	     	        	    	rowBuff.append(strValue+"#!#!");
	     	        	    } else {
	     	        	    	rowBuff.append(cell.getNumericCellValue()+"#!#!");
	     	        	    }
	        	        	break;
	        	        case Cell.CELL_TYPE_BOOLEAN:
	        	        	rowBuff.append(cell.getBooleanCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BLANK:
	        	        	if(currentCell>columnsToRemove-1)
	        	        		rowBuff.append("#!#!");
	        	        	break;
	        	        default :
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	    }
        		}
        		String rowStr = rowBuff.toString();
        		if(rowStr.endsWith("#!#!")) {
        			rowStr= rowStr.substring(0, rowStr.length() - 4);
        		}
        		if(rowStr.split("#!#!").length > subPackageDetail.getMinFillesCells()){
        			if(contentList.size()==0){
	        			while (rowStr.startsWith("#!#!")) {
	        				rowStr = rowStr.replaceFirst("#!#!", "");
	        				columnsToRemove++;
	        			}
        			}
        			contentList.add(rowStr);
        		}
        	}
        	contentList.toArray(content);
        } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(myWorkBook!=null)
				myWorkBook.close();
		}
        return clean(content);
	}
	
	public static String[] processGeneralXLSFile(SubPackageDetail subPackageDetail, long userId, InputStream fis) throws IOException{
		int rowsToRemove = subPackageDetail.getRowsToRemove();
		int columnsToRemove = subPackageDetail.getColumnsToRemove();
		String content[] = new String[fis.available()];
		List<String> contentList = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Workbook myWorkBook = null;
        try {
        	myWorkBook = WorkbookFactory.create(fis);
        	org.apache.poi.ss.usermodel.Sheet mySheet = myWorkBook.getSheetAt(0);
        	Iterator<Row> rowIterator = mySheet.iterator();
        	for (int i = 1; i < rowsToRemove-1; i++) {
        		rowIterator.next();
			}
        	while (rowIterator.hasNext()) {
        		Row row = rowIterator.next();
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int previousCell = columnsToRemove - 1;
        		int currentCell = 0;
        		StringBuffer rowBuff = new StringBuffer();
        		while (cellIterator.hasNext()) {
        			Cell cell = cellIterator.next();
        			currentCell = cell.getColumnIndex();
        			if (previousCell != currentCell-1 && currentCell>columnsToRemove) {
        	        	int diff = currentCell-previousCell-1;
        	        	for (int i = 0; i < diff; i++) {
        	        		rowBuff.append("#!#!");
						}
        	        }
        	        previousCell = currentCell;
	        	    switch (cell.getCellType()) {
	        	        case Cell.CELL_TYPE_STRING:
	        	        	rowBuff.append(cell.getStringCellValue().trim()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_NUMERIC:
	        	        	 if (HSSFDateUtil.isCellDateFormatted(cell)) {
	     	        	    	Date date = cell.getDateCellValue();
	     	        	    	String dateFmt = cell.getCellStyle().getDataFormatString();
	     	        	    	if(dateFmt.equals("[$-409]d\\-mmm\\-yy;@") || dateFmt.equals("[$-409]dd\\-mmm\\-yy;@")){
	     	        	    		dateFmt = "d-mmm-yyyy";
	     	        	    	}
	     	        	    	String strValue = new CellDateFormatter(dateFmt).format(date);
	     	        	    	rowBuff.append(strValue+"#!#!");
	     	        	    } else {
	     	        	    	rowBuff.append(cell.getNumericCellValue()+"#!#!");
	     	        	    }
	        	        	break;
	        	        case Cell.CELL_TYPE_BOOLEAN:
	        	        	rowBuff.append(cell.getBooleanCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BLANK:
	        	        	if(currentCell>columnsToRemove-1)
	        	        		rowBuff.append("#!#!");
	        	        	break;
	        	        default :
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	    }
        		}
        		String rowStr = rowBuff.toString();
        		if(rowStr.endsWith("#!#!")) {
        			rowStr= rowStr.substring(0, rowStr.length() - 4);
        		}
        		if(rowStr.split("#!#!").length > subPackageDetail.getMinFillesCells()){
        			if(contentList.size()==0){
	        			while (rowStr.startsWith("#!#!")) {
	        				rowStr = rowStr.replaceFirst("#!#!", "");
	        				columnsToRemove++;
	        			}
        			}
        			contentList.add(rowStr);
        		}
        	}
        	contentList.toArray(content);
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(myWorkBook!=null)
				myWorkBook.close();
			if(fis!=null) {
				fis.close();
			}
		}
        return clean(content);
	}/*
	public static String[] processGeneralXLSFile1111(SubPackageDetail subPackageDetail, long userId, InputStream fis) throws IOException{
		int rowsToRemove = subPackageDetail.getRowsToRemove();
		int columnsToRemove = subPackageDetail.getColumnsToRemove();
		String content[] = new String[fis.available()];
		List<String> contentList = new ArrayList<String>();
		org.apache.poi.ss.usermodel.Workbook myWorkBook = null;
        try {
        	myWorkBook = StreamingReader.builder()
        	        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
        	        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
        	        .open(fis);            // InputStream or File for XLSX file (required)
        	
        	//myWorkBook = WorkbookFactory.create(fis);
        	org.apache.poi.ss.usermodel.Sheet mySheet = myWorkBook.getSheetAt(0);
        	Iterator<Row> rowIterator = mySheet.iterator();
        	for (int i = 1; i < rowsToRemove-1; i++) {
        		rowIterator.next();
			}
        	while (rowIterator.hasNext()) {
        		Row row = rowIterator.next();
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int previousCell = columnsToRemove - 1;
        		int currentCell = 0;
        		StringBuffer rowBuff = new StringBuffer();
        		while (cellIterator.hasNext()) {
        			Cell cell = cellIterator.next();
        			currentCell = cell.getColumnIndex();
        			if (previousCell != currentCell-1 && currentCell>columnsToRemove) {
        	        	int diff = currentCell-previousCell-1;
        	        	for (int i = 0; i < diff; i++) {
        	        		rowBuff.append("#!#!");
						}
        	        }
        	        previousCell = currentCell;
	        	    switch (cell.getCellType()) {
	        	        case Cell.CELL_TYPE_STRING:
	        	        	rowBuff.append(cell.getStringCellValue().trim()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_NUMERIC:
	        	        	 if (HSSFDateUtil.isCellDateFormatted(cell)) {
	     	        	    	Date date = cell.getDateCellValue();
	     	        	    	String dateFmt = cell.getCellStyle().getDataFormatString();
	     	        	    	if(dateFmt.equals("[$-409]d\\-mmm\\-yy;@") || dateFmt.equals("[$-409]dd\\-mmm\\-yy;@")){
	     	        	    		dateFmt = "d-mmm-yyyy";
	     	        	    	}
	     	        	    	String strValue = new CellDateFormatter(dateFmt).format(date);
	     	        	    	rowBuff.append(strValue+"#!#!");
	     	        	    } else {
	     	        	    	rowBuff.append(cell.getNumericCellValue()+"#!#!");
	     	        	    }
	        	        	break;
	        	        case Cell.CELL_TYPE_BOOLEAN:
	        	        	rowBuff.append(cell.getBooleanCellValue()+"#!#!");
	        	        	break;
	        	        case Cell.CELL_TYPE_BLANK:
	        	        	if(currentCell>columnsToRemove-1)
	        	        		rowBuff.append("#!#!");
	        	        	break;
	        	        default :
	        	        	rowBuff.append("#!#!");
	        	        	break;
	        	    }
        		}
        		String rowStr = rowBuff.toString();
        		if(rowStr.endsWith("#!#!")) {
        			rowStr= rowStr.substring(0, rowStr.length() - 4);
        		}
        		if(rowStr.split("#!#!").length > subPackageDetail.getMinFillesCells()){
        			if(contentList.size()==0){
	        			while (rowStr.startsWith("#!#!")) {
	        				rowStr = rowStr.replaceFirst("#!#!", "");
	        				columnsToRemove++;
	        			}
        			}
        			contentList.add(rowStr);
        		}
        	}
        	contentList.toArray(content);
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(myWorkBook!=null)
				myWorkBook.close();
			if(fis!=null) {
				fis.close();
			}
		}
        return clean(content);
	}*/
	
	public static String[] clean(final String[] v) {
	    List<String> list = new ArrayList<String>(Arrays.asList(v));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray(new String[list.size()]);
	}
	
	/*public static void main(String[] args) {
		String oldStr = "#!#!Consignee: SHOPRITE CHECKERS (PTY) LTD (6406483416)#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#! #!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!";
		String oldStr1 = "#!#!Filters: ETA From: 01-Jun-17 To:  #!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#! #!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!";
		
		int i = 0;
		while (oldStr.startsWith("#!#!")) {
			oldStr = oldStr.replaceFirst("#!#!", "");
			System.out.println("-----33-----"+oldStr);
			i++;
		}
		//System.out.println("----------"+i);
		System.out.println("-----44-----"+oldStr.split("#!#!").length);
		System.out.println("-----44-----"+oldStr1.split("#!#!").length);
	}*/
}
