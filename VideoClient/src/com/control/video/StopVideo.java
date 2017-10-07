package com.control.video;

import com.control.Ctrl;
import com.model.captureVideo.CompileVideo;

public class StopVideo implements Ctrl{
	private CompileVideo cv;
	
	public StopVideo() {
		cv = CompileVideo.getInstance();
	}
	
	@Override
	public void execute() {
		cv.setRecording(false);
	}

}
