package com.model.sendVideo;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import com.Utils;

public class SendOverHttp {
	private VideoData videoData;
	
	public SendOverHttp(VideoData vd) {
		videoData = vd;
		sendData();
	}

	private void sendData() {
		File file = new File ("./video/" + videoData.getDate() + ".mp4");
		if(sendVideo(file)){
			StringBuilder videoDataString = new StringBuilder();
			videoDataString.append(videoData.getDate()+";");
			videoDataString.append(videoData.getTeamName()+";");
			videoDataString.append(videoData.getTile()+";");
			videoDataString.append(videoData.getSport()+";");
			sendVideoData(videoDataString.toString());
		}
	}

	private void sendVideoData(String string) {
		String url = "http://"+Utils.hostName+":"+Utils.port+"/TeamProjectsServer/rest/video/uploadVideoData";
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost (url) ;
        StringEntity properties = null;
        try {
			properties = new StringEntity(string);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        try {
        	postRequest.setEntity(properties);
			HttpResponse response = client.execute(postRequest);
			System.out.println(response.getStatusLine().getStatusCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		
	}

	@SuppressWarnings("deprecation")
	private boolean sendVideo(File file) {
		String url = "http://"+Utils.hostName+":"+Utils.port+"/TeamProjectsServer/rest/video/uploadVideo";
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost (url) ;
        HttpEntity entity; 
        try{
            //Set various attributes 
            MultipartEntityBuilder multiPartEntity = MultipartEntityBuilder.create();
            FileBody fileBody = new FileBody(file, "multipart/form-data") ;
            //Prepare payload
            multiPartEntity.addPart("attachment", fileBody);
            entity = multiPartEntity.build();
            //Set to request body
            postRequest.setEntity(entity) ;
            //Send request
            HttpResponse response = client.execute(postRequest) ;
            //Verify response if any
            if (response != null)
            {
                System.out.println(response.getStatusLine().getStatusCode());
            }
            if(response.getStatusLine().getStatusCode() == 200) {
            	return true;
            }
        }
        catch (Exception ex){
            ex.printStackTrace() ;
        }
        return false;
	}
}
