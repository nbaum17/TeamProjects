package com.control.video;

import com.control.Ctrl;
import com.model.sendVideo.Send;
import com.model.sendVideo.VideoData;

public class SendVideo implements Ctrl{
	private Send send;
	private VideoData vd;
	
	public SendVideo(VideoData videoData) {
		vd = videoData;
	}
	
	@Override
	public void execute() {
		send = new Send(vd);
	}

}
