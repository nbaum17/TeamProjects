package com.controllers.video;


import java.sql.Connection;
import java.sql.SQLException;

import com.dao.video.VideoDAO;
import com.database.Video;
import com.util.DatabaseConnection;

public class AddVideo {
	private String data;
	Video videoD;
	
	/**
	 * constructor
	 * @param data
	 */
	public AddVideo(String data) {
		this.data = data;
	}

	/**
	 * adds video data to the database.
	 * @throws SQLException
	 */
	public void execute() throws SQLException {
		
		makeVideoObj();
		Connection con = null;
		VideoDAO video = new VideoDAO();
		try {
			con = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		video.addVideo(con, videoD);
		con.close();
	}

	/**
	 * makes a video object for ease of access when generating the prepaired statement. 
	 */
	private void makeVideoObj() {
		videoD = new Video();
		String[] videoData = data.split(";");
		videoD.setDate(Long.valueOf(videoData[0]));
		videoD.setTeam(videoData[1]);
		videoD.setTitle(videoData[2]);
		videoD.setSport(videoData[3]);
		videoD.setPath("D:\\SERVER\\wildfly-9.0.0.Final\\standalone\\deployments\\videos\\"+videoData[0]+".mp4");
	}
}
