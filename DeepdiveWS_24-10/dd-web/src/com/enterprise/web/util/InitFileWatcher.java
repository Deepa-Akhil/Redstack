package com.enterprise.web.util;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

public class InitFileWatcher extends TimerTask {
	
	@Autowired
	FileUtils fileUtils;
	
	@Override
	public void run() {
		fileUtils.watchDropBoxFiles();
	}
}
