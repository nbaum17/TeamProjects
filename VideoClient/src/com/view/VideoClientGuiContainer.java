package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Window.Type;

public class VideoClientGuiContainer {

	public static JFrame guiContainer;

	/**
	 * Create the application.
	 */
	public VideoClientGuiContainer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		guiContainer = new JFrame();
		guiContainer.setFont(new Font("Dialog", Font.BOLD, 14));
		guiContainer.setTitle("UAS Operator Recording Tool");
		guiContainer.setBounds(100, 100, 455, 319);
		guiContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiContainer.getContentPane().add(new VideoClientGui());
	}

}
