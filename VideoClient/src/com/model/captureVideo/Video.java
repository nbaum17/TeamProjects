package com.model.captureVideo;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.model.Picture;


public class Video {
	private AWTSequenceEncoder enc = null;
	private static Video video = null;
	private Long date;
	
	/**
     * constructor for video object.
     *
     * @param frame
     */
    private Video() {
    	date = new Date().getTime();
      try {
    	  
    	  enc = AWTSequenceEncoder.create25Fps(new File("./video/"+ date +".mp4"));
    	  
            
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

	public Long getDate() {
		return date;
	}

	/**
     * creates the video
     */
    public void mkVideo(BufferedImage image) {
    	try {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		ImageIO.write(image, "jpg", baos );
    		baos.flush();
    		byte[] imageInByte = baos.toByteArray();
    		baos.close();
    		// if byte[] is to big.
    		
    		if(imageInByte.length < 15000) {
    			enc.encodeImage(image);
    		}else {
    			System.out.println(imageInByte.length);
    		}
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
