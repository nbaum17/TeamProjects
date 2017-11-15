package com.dao.video;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Video;

public class VideoDAO {
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	/**
	 * no args constructor. 
	 */
	public VideoDAO() {
		
	}

	/**
	 * gets all rows from the video_data table. 
	 * @param con The database connection object. 
	 * @return ArrayList<Video> 
	 * @throws SQLException if read fails 
	 */
	public ArrayList<Video> getAll(Connection con) throws SQLException {
		statement = con.createStatement();
		resultSet = statement.executeQuery("select * from video_table");
		ArrayList<Video> videoList = resultSetToArrayList(resultSet);
		return videoList;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	//Add other methods for querying the database here.
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * converts the result set to an ArrayList<Video> should be used by all other query methods. 
	 * @param rst the result set returned from the database query 
	 * @return ArrayList<Video>
	 * @throws SQLException
	 */
	private static ArrayList<Video> resultSetToArrayList(ResultSet rst) throws SQLException {
		ArrayList<Video> videoList = new ArrayList<Video>();
		Video video;
		while (rst.next()) {
			video = new Video();
			video.setDate(resultSet.getLong("date"));
			video.setId(resultSet.getInt("id"));
			video.setSport(resultSet.getString("sport"));
			video.setTeam(resultSet.getString("team"));
			video.setTitle(resultSet.getString("title"));
			video.setPath(resultSet.getString("file_location"));
			videoList.add(video);
		}
		return videoList;
	}
	
	
}
