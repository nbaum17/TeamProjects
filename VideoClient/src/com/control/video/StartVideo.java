package com.control.video;

import com.control.Ctrl;
import com.model.captureVideo.CompileVideo;

public class StartVideo implements Ctrl{
	private CompileVideo cv;
	
	/**
	 * constructor 
	 */
	public StartVideo() {
		cv = CompileVideo.getInstance();
	}

	@Override
	public void execute() {
		Thread thread = new Thread(cv);
		cv.setRecording(true);
		thread.start();
	}

}
