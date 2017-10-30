package com.controllers.video;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.video.VideoDAO;
import com.database.Video;
import com.util.DatabaseConnection;

public class GetAllVideos{
	private ArrayList<Video> videoList = null;

	/**
	 * no args constructor. maybe not required but oh well. I dont really care
	 */
	public GetAllVideos() {
		
	}
	
	/**
	 * this method executes all steps in getting all of the video data from the database. 
	 * @throws SQLException if there is something wrong with the database. 
	 */
	public void execute() throws SQLException {
		Connection con = null;
		VideoDAO video = new VideoDAO();
		try {
			con = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		videoList = video.getAll(con);
		con.close();
	}
	
	/**
	 * getter for the video list. could maybe convert to json obj but it seems to work 
	 * while being handled by the service itself. 
	 * @return ArrayList<Video> 
	 */
	public ArrayList<Video> getVideoList(){
		return videoList;
	}

}
