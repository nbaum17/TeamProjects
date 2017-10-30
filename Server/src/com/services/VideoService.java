package com.services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.json.JSONArray;

import com.controllers.video.GetAllVideos;


@Path("/video")
public class VideoService {

	@GET
	@Path("/getAllVideos")
	public String hello() {
		GetAllVideos gav = new GetAllVideos();
		JSONArray json;
		try {
			gav.execute();
		} catch (SQLException e) {
			
		}
		json = new JSONArray(gav.getVideoList().toArray());
		return json.toString();
	}
	
}
