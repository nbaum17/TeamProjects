package com.model.captureVideo;

import java.awt.image.BufferedImage;
import java.util.Date;

import com.model.webCam.Camera;

public class CompileVideo implements Runnable{
	private static CompileVideo compVid = null;
	private Video video;
	private Camera camera;
	private boolean recording = false;
	private Long date;
	
	private CompileVideo() {
		video = Video.getInstance();
		camera = Camera.getInstance();
		date = video.getDate();
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

    
    
	public Long getDate() {
		return date;
	}

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	@Override
	public void run() {
		while(recording) {
			BufferedImage image = camera.capture();
			video.mkVideo(image);
			
			// This may be needed to controll the frame rate. 
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		video.stop();
		camera.cameraOff();
		compVid = null;
	}

	
}
