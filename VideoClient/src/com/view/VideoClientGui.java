package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.control.video.SendVideo;
import com.control.video.StartVideo;
import com.control.video.StopVideo;
import com.model.sendVideo.VideoData;

@SuppressWarnings("serial")
public class VideoClientGui extends JPanel{
	private JTextField sport;
	private JTextField teamName;
	private JTextField videoTitle;
	private JButton btnSendVideo;
	private JSeparator separator;
	private JButton btnStartRecording;
	private JButton btnStopRecording;
	private VideoData videoData; 
	
	/**
	 * Create the panel.
	 */
	public VideoClientGui() {
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblTitle = new JLabel("Video Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblSport = new JLabel("Sport");
		lblSport.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTeamName = new JLabel("Team Name");
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		sport = new JTextField();
		sport.setColumns(10);	
		teamName = new JTextField();
		teamName.setColumns(10);	
		videoTitle = new JTextField();
		videoTitle.setColumns(10);
		
		separator = new JSeparator();
		
		btnSendVideo = new JButton("Send Video");
		btnSendVideo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSendVideo.setEnabled(false);
		
		btnStartRecording = new JButton("Start Recording");
		btnStartRecording.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnStopRecording = new JButton("Stop Recording");
		btnStopRecording.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStopRecording.setEnabled(false);
		
		JProgressBar progressBar = new JProgressBar();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnStartRecording, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStopRecording, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(lblTitle, Alignment.LEADING)
						.addComponent(videoTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(lblTeamName, Alignment.LEADING)
						.addComponent(teamName, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(lblSport, Alignment.LEADING)
						.addComponent(sport, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(btnSendVideo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartRecording)
						.addComponent(btnStopRecording))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(videoTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTeamName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(teamName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSport)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSendVideo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		/**
		 * listens to the send button and either sends the video or doesn't depending on the 
		 * input in the text fields.
		 */
		btnSendVideo.addActionListener(e -> {
			sport.setBackground(Color.WHITE);
			teamName.setBackground(Color.WHITE);
			videoTitle.setBackground(Color.WHITE);
			if(sport.getText().trim().length() == 0 || teamName.getText().trim().length() == 0 || videoTitle.getText().trim().length() == 0) {
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Unable to send video due to missing information! ");
				if(sport.getText().trim().length() == 0) {
					sport.setBackground(Color.RED);
				}
				if(teamName.getText().trim().length() == 0) {
					teamName.setBackground(Color.RED);
				}
				if(videoTitle.getText().trim().length() == 0) {
					videoTitle.setBackground(Color.RED);
				}
				JOptionPane.showMessageDialog(this,
					    errorMessage,
					    "Missing Input",
					    JOptionPane.ERROR_MESSAGE);
			}else {
				videoData.setSport(sport.getText());
				videoData.setTeamName(teamName.getText());
				videoData.setTile(videoTitle.getText());
				SendVideo send = new SendVideo(videoData);
				send.execute();
			}
		});
		
		/**
		 * listens to the start recording button and starts recording if a recording has not already started. 
		 */
		btnStartRecording.addActionListener(e -> {
			videoData = new VideoData();
			StartVideo startVideo = new StartVideo();
			startVideo.execute();
			videoData.setDate(startVideo.getDate());
			btnStopRecording.setEnabled(true);
			btnStartRecording.setEnabled(false);
			btnSendVideo.setEnabled(false);
		});
		
		/**
		 * listens to the stop recording button and stops the recording if one is in progress. 
		 */
		btnStopRecording.addActionListener(e -> {
			StopVideo stopVideo = new StopVideo();
			stopVideo.execute();
			btnStopRecording.setEnabled(false);
			btnStartRecording.setEnabled(true);
			btnSendVideo.setEnabled(true);
		});
	}
}
