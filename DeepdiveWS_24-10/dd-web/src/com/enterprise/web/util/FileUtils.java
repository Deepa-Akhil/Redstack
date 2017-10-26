package com.enterprise.web.util;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.common.util.GetProperty;

@Component
public class FileUtils {
	
	@Autowired
	ProcessFile processFile;
	
	@Autowired
	GetProperty getProperty;
	
	public static String DATA_DIRECTORY_KEY = "files.dropbox.directory";
	public static String SUCCESS_DIRECTORY_KEY = "files.dropbox.success";
	public static String ERROR_DIRECTORY_KEY = "files.dropbox.error";
	public static String IS_AUTO = "files.dropbox.enable";
	public static String SEPERATOR = File.separator;

	public static void main(String[] args) {
		FileUtils fileUtils = new FileUtils();
		while(true){
			fileUtils.doFileWatch();
		}
	}
	
	public void watchDropBoxFiles() {
		while("true".equalsIgnoreCase(getProperty.getProperty(IS_AUTO))){
			doFileWatch();
		}
	}
	
	public void doFileWatch(){
		try {
            cleanupDirectories();
            
			/*WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get(DATA_DIRECTORY);
			dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
			WatchKey key;
			try {
				key = watcher.take();// wait for a key to be available
			} catch (InterruptedException ex) {
				return;
			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();// get event type
				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path fileName = ev.context();
				System.out.println(kind.name() + ": " + fileName);
				if (kind == OVERFLOW) {
					continue;
				} else if (kind == ENTRY_CREATE || kind == ENTRY_MODIFY) {
					showFiles(new File(DATA_DIRECTORY+SEPERATOR+fileName).listFiles());
				}
			}*/
		} /*catch (IOException e) {
			e.printStackTrace();
		} */ catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showFiles(File[] files) throws IOException {
		for (File file : files) {
			if (file.isDirectory()) {
				showFiles(file.listFiles()); // Calls same method again.
			} else if(!file.isHidden()) {
				//FileInputStream inputStream = null;
				try {
					//inputStream = new FileInputStream(file);
					//call service
					System.out.println("-----   Calling Load Method for file   -----"+file.getAbsolutePath());
					String returnMsg = processFile.LoadFile(Long.valueOf(file.getAbsoluteFile().getParentFile().getName()), file, file.getName());
					//String returnMsg = "";
					//inputStream.close();
					String backupDirectory = getProperty.getProperty(ERROR_DIRECTORY_KEY);
					if(returnMsg!=null && returnMsg.equals("success")){
						backupDirectory = getProperty.getProperty(SUCCESS_DIRECTORY_KEY);
					}
					copyToBackup(file.getAbsoluteFile().getParentFile().getAbsolutePath(), 
							backupDirectory+SEPERATOR+file.getAbsoluteFile().getParentFile().getName()+SEPERATOR, file.getName());
				} catch (Exception e) {
					e.printStackTrace();
					/*if(inputStream!=null) {
						inputStream.close();
					}*/
					copyToBackup(file.getAbsoluteFile().getParentFile().getAbsolutePath(), 
							getProperty.getProperty(ERROR_DIRECTORY_KEY)+SEPERATOR+file.getAbsoluteFile().getParentFile().getName()+SEPERATOR, file.getName());
				} finally {
					/*if(inputStream!=null) {
						inputStream.close();
					}*/
				}
			}
		}
	}
	
	public boolean copyToBackup(String source, String dest, String fileName) {
		try {
			Path sourcePath = Paths.get(source+SEPERATOR+fileName);
			File destParentDir = new File(dest);
			destParentDir.mkdirs(); // make sure that the dest directory exists
			Path destPath = Paths.get(dest+SEPERATOR+fileName);
			Files.move(sourcePath, destPath, REPLACE_EXISTING);
			System.out.println("File moved from : "+sourcePath+" To : "+destPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void cleanupDirectories() throws IOException {
		showFiles(new File(getProperty.getProperty(DATA_DIRECTORY_KEY)).listFiles());
	}
}
