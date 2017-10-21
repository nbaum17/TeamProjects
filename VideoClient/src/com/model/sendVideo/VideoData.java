package com.model.sendVideo;

import java.util.Date;

public class VideoData {	
	private Long date; 
	private String sport;
	private String teamName;
	private String tile; 
	
	public VideoData() {
		
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}
	
	
}
