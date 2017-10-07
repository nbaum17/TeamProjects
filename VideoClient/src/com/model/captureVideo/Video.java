package com.model.captureVideo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jcodec.api.awt.AWTSequenceEncoder;


public class Video {
	private AWTSequenceEncoder enc = null;
	private static Video video = null;
	
	
	/**
     * constructor for video object.
     *
     * @param frame
     */
    private Video() {
      try {
            enc = AWTSequenceEncoder.create30Fps(new File("./video/filename.mp4"));
            //enc = AWTSequenceEncoder.create2997Fps(new File("./video/filename.mp4"));
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * gets instance of video object
     * @return 
     */
    public static Video getInstance(){
        if(video == null){
        	video = new Video();
        }
        return video;
    }
    
    /**
     * creates the video
     */
    public void mkVideo(BufferedImage image) {
    	try {
            enc.encodeImage(image);
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * stops encoder.
     */
    public void stop() {
    	try {
            enc.finish();
            //enc = AWTSequenceEncoder.create30Fps(new File("./video/filename.mp4"));
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
