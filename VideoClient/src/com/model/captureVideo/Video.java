package com.model.captureVideo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jcodec.api.awt.AWTSequenceEncoder;


public class Video {
	private AWTSequenceEncoder enc = null;
	private static Video video = null;
	private Date date;
	
	/**
     * constructor for video object.
     *
     * @param frame
     */
    private Video() {
    	date = new Date();
      try {
            enc = AWTSequenceEncoder.create25Fps(new File("./video/"+ date +".mp4"));
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

	public Date getDate() {
		return date;
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
            video = null;     
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
