package com.model.sendVideo;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class SendOverHttp {
	private VideoData videoData;
	//private static final String HOST = "192.160.0.109";
	private static final String HOST = "localhost";
	
	public SendOverHttp(VideoData vd) {
		videoData = vd;
		sendData();
	}

	private void sendData() {
		File file = new File ("./video/" + videoData.getDate() + ".mp4");
		sendVideo(file);
		
		
		
		StringBuilder videoDataString = new StringBuilder();
		//videoDataString.append((int)file.length()+ ";");
		videoDataString.append(videoData.getDate()+";");
		videoDataString.append(videoData.getTeamName()+";");
		videoDataString.append(videoData.getTile()+";");
		videoDataString.append(videoData.getSport()+";");
		
		
	}

	@SuppressWarnings("deprecation")
	private void sendVideo(File file) {
		String url = "http://"+HOST+":8080/TeamProjectsServer/rest/video/upload";
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
        }
        catch (Exception ex){
            ex.printStackTrace() ;
        }
	}
}
