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

import com.Utils;
import com.control.video.SendVideo;
import com.control.video.StartVideo;
import com.control.video.StopVideo;
import com.model.sendVideo.VideoData;
import javax.swing.SwingConstants;

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
	private JTextField host;
	private JTextField port;
	
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
		
		host = new JTextField();
		host.setText(Utils.hostName);
		host.setColumns(10);
		
		port = new JTextField();
		port.setText(Utils.port);
		port.setColumns(10);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator_1 = new JSeparator();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(btnSendVideo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(sport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(lblSport)
						.addComponent(teamName, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(lblTeamName)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(host, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
									.addGap(12))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblHost)
									.addGap(192))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnStartRecording, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPort)
								.addComponent(port, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnStopRecording, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))))
						.addComponent(videoTitle, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(lblTitle)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHost)
						.addComponent(lblPort))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(host, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStartRecording)
						.addComponent(btnStopRecording))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(videoTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTeamName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(teamName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSport)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSendVideo)
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
				Utils.hostName = host.getText();
				Utils.port = port.getText();
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
