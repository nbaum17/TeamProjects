package com.model.captureVideo;

import java.awt.image.BufferedImage;

import com.model.webCam.Camera;

public class CompileVideo implements Runnable{
	private static CompileVideo compVid = null;
	private Video video;
	private Camera camera;
	private boolean recording = false;
	
	private CompileVideo() {
		video = Video.getInstance();
		camera = Camera.getInstance();
	}
	
	/**
     * gets instance of the video compiler. 
     * @return 
     */
    public static CompileVideo getInstance(){
        if(compVid == null){
        	compVid = new CompileVideo();
        }
        return compVid;
    }

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	@Override
	public void run() {
		while(recording) {
			BufferedImage image = camera.capture();
			video.mkVideo(image);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		video.stop();
		camera.cameraOff();
	}

	
}
