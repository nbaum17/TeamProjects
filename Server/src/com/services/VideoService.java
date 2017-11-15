package com.services;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import com.controllers.video.GetAllVideos;


@Path("/video")
public class VideoService {

	@GET
	@Path("/getAllVideos")
	public String getAllVideos() {
		GetAllVideos gav = new GetAllVideos();
		JSONArray json;
		try {
			gav.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		json = new JSONArray(gav.getVideoList().toArray());
		return json.toString();
	}
	
	@GET
	@Path("/download/{name}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("name") String name) {
		File file = new File("D:\\SERVER\\wildfly-9.0.0.Final\\standalone\\deployments\\videos\\"+name);
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
			      .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) 
			      .build();
	}

	@GET
	@Path("/getpath")
	public String getpath() {
		return Paths.get(".").toAbsolutePath().normalize().toString();
	}
}
