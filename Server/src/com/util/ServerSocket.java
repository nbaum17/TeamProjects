package com.util;

import java.net.*;
import java.io.*;

public class ServerSocket {

	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ServerSocket(String hostName, int port) {
		try{
			socket = new Socket(hostName,port);		
			output = new PrintWriter(socket.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public int readData() {
		return 1;
	}
	
	public int writeData() {
		return 1;
	}
}
