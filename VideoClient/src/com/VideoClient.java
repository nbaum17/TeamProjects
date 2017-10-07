package com;

import java.awt.EventQueue;

import com.view.VideoClientGuiContainer;

public class VideoClient {

	public static void main(String[] args) {
		startGui();
	}

	/**
	 * kicks off the gui.
	 */
	private static void startGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoClientGuiContainer window = new VideoClientGuiContainer();
					window.guiContainer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
