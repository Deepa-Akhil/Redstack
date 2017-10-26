package com.enterprise.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;

import com.enterprise.common.exception.FileTooBigException;

public final class IOUtils {
	public static String clobToString(Clob data) {
		StringBuilder sb = new StringBuilder();
		if (data != null) {
		    try {
		        Reader reader = data.getCharacterStream();
		        BufferedReader br = new BufferedReader(reader);
		        String line;
		        while(null != (line = br.readLine())) {
		            sb.append(line);
		        }
		        br.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	    return sb.toString();
	}
	
	public static byte[] read(File file) throws IOException, FileTooBigException {
	    if ( file.length() > Constants.MAX_FILE_SIZE ) {
	        throw new FileTooBigException(file);
	    }
		byte []buffer = new byte[(int) file.length()];
		InputStream ios = null;
		try {
		    ios = new FileInputStream(file);
		    if ( ios.read(buffer) == -1 ) {
		        throw new IOException("EOF reached while trying to read the whole file");
		    }        
		} finally { 
		    try {
		         if (ios != null) {
		              ios.close();
		         }
		    } catch ( IOException e) {/* no implementation*/}
		}
	    return buffer;
	}
}