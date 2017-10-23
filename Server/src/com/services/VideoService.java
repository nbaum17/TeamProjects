package com.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/video")
public class VideoService {

	@GET
	@Path("/hello")
	public String hello() {
		return "<h1>Hello World</h1>";
	}
	
}
