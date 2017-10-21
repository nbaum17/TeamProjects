package com.model.sendVideo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send {
	private VideoData videoData;
	private int PORT = 1;
	private String HOST = "localhost";
	
	FileInputStream fileInputStream = null;
	BufferedInputStream bufferedInputStream = null;
	OutputStream outputStream = null;
	Socket socket = null;
	
	public Send(VideoData vd) {
		videoData = vd;
		sendData();
	}

	private void sendData() {
		try {
			socket = new Socket(HOST,PORT);
			// create video data string.
			StringBuilder videoDataString = new StringBuilder();
			videoDataString.append(videoData.getDate()+"; ");
			videoDataString.append(videoData.getTeamName()+"; ");
			videoDataString.append(videoData.getTile()+"; ");
			videoDataString.append(videoData.getSport()+"; ");
			byte [] stringBytes = videoDataString.toString().getBytes();
			
			// create stream for video sending. 
			File file = new File ("./video/" + videoData.getDate() + ".mp4");
			byte [] byteArray  = new byte [(int)file.length()];
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			bufferedInputStream.read(byteArray,0,byteArray.length);
			
			// send the data string and video.
			outputStream = socket.getOutputStream();
			outputStream.write(stringBytes,0,stringBytes.length);
			outputStream.flush();
			outputStream.write(byteArray,0,byteArray.length);
			outputStream.flush();
			if (bufferedInputStream != null) bufferedInputStream.close();
			if (outputStream != null) bufferedInputStream.close();
			if (socket!=null) socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
