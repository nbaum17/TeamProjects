package com.model.webCam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

public class Camera {
	private Webcam webcam;
	private static Camera camera = null;
	
	/**
     * constructor for webcam object.
     */
    private Camera(){
        webcam = Webcam.getDefault();
        //webcam.setViewSize(new Dimension(320,240));
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        cameraOn();
    }
	
    /**
     * gets instance of WebCamera
     * @return 
     */
    public static Camera getInstance(){
        if(camera == null){
        	camera = new Camera();
        }
        return camera;
    }
    
    /**
     * turns on the webcam
     * @param fileName 
     */
    private void cameraOn(){
        // get default webcam and open it
        webcam.open(true);
    }
    
    /**
     * stops looking at the camera feed.
     */
    public void cameraOff() {
    	webcam.close();
    	camera = null;
    }
    
    /**
     * takes picture
     * @return 
     */
    public BufferedImage capture(){
        BufferedImage image = webcam.getImage();
        return image;
    }
}
