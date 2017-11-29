package com.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONArray;

import com.controllers.video.AddVideo;
import com.controllers.video.GetAllVideos;
import com.controllers.video.SearchVideos;


@Path("/video")
public class VideoService {

	/**
	 * gets all the video data from the database.
	 * @return
	 */
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
	
	/**
	 * service that utilizes the search functionality. 
	 * @param data
	 * @return
	 */
	@GET
	@Path("/search")
	public String search(String data) {
		SearchVideos sv = new SearchVideos(data);
		JSONArray json;
		try {
			sv.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sv.getVideoList().size() == 0) {
			return "No Videos Found";
		}
		json = new JSONArray(sv.getVideoList().toArray());
		return json.toString();
	}
	
	/**
	 * downloads the video from the server
	 * @param name
	 * @return
	 */
	@GET
	@Path("/download/{name}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("name") String name) {
		File file = new File("D:\\SERVER\\wildfly-9.0.0.Final\\standalone\\deployments\\videos\\"+name);
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
			      .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) 
			      .build();
	}
	
	/**
	 * saves the video data to the database. 
	 * @param data
	 * @return
	 */
	@POST
	@Path("/uploadVideoData")
	public Response fileData(String data) {
		AddVideo av = new AddVideo(data);
		try {
			av.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
	
	/**
	 * saves video to the server file system 
	 * @param input
	 * @return response succeed or fail 
	 */
	@POST
	@Path("/uploadVideo")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		// Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path
                fileName = "D:\\SERVER\\wildfly-9.0.0.Final\\standalone\\deployments\\videos\\" + fileName;
                writeFile(bytes, fileName);
                return Response.status(200).entity("Uploaded file name : " + fileName)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.status(500).entity("Failed to upload video").build();
	}
	
	/**
	 * helper for upload service.
	 * @param content
	 * @param fileName
	 * @throws IOException
	 */
	private void writeFile(byte[] content, String fileName) throws IOException {
		File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("not exist> " + file.getAbsolutePath());
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
	}

	/**
	 * gets the file name of the file being transfered. 
	 * @param header
	 * @return
	 */
	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		 
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
 
                String[] name = filename.split("=");
 
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
	}

	/**
	 * gets the path in case we need it.
	 * @return
	 */
	@GET
	@Path("/getpath")
	public String getpath() {
		return Paths.get(".").toAbsolutePath().normalize().toString();
	}
}
