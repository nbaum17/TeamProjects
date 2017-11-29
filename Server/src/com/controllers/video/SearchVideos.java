package com.controllers.video;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.video.VideoDAO;
import com.database.Video;
import com.util.DatabaseConnection;

public class SearchVideos {
	private String data;
	Video videoD;
	private ArrayList<Video> videoList = null;
	
	public SearchVideos(String data) {
		this.data = data;
	}

	public void execute() throws SQLException {
		Connection con = null;
		VideoDAO video = new VideoDAO();
		try {
			con = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Video> allVideos = video.getAll(con);
		search(allVideos);
		con.close();
	}
	
	/**
	 * searches through all of the videos to find matches. 
	 * @param allVideos
	 */
	private void search(ArrayList<Video> allVideos) {
		String[] d = data.split(";");
		videoD = new Video();
		videoD.setSport(d[0]);
		videoD.setTeam(d[1]);
		videoD.setTitle(d[2]);
		boolean add1 = false;
		boolean add2 = false; 
		boolean add3 = false;
		for(Video v : allVideos) {
			
			//if the sport field is null or contains the string set add1 true
			if(videoD.getSport() == "null" || v.getSport().contains(d[0])) {
				add1 = true;
			}
			//if the team field is null or contains the string set add2 to true
			if(videoD.getTeam() == "null" || v.getTeam().contains(d[1])) {
				add2 = true;
			}
			//if the title field is null or contains the string set add3 to true
			if(videoD.getTitle() != "null" || v.getTitle().contains(d[2])) {
				add3 = true;
			}
			//if all three adds are true add this video to the list.
			if(add1 && add2 && add3) {
				videoList.add(v);
			}
		}
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
