package com.util;

import java.net.*;
import java.io.*;

public class ServerSocket {

	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ServerSocket() {
		
	}
	
	private void openSocket(String hostName, int port) {
		try{
			socket = new Socket(hostName,port);		
			output = new PrintWriter(socket.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void listen(String hostName, int port) {
		
		openSocket(hostName,port);
		
		String data;
		
		do {
			data = readData();
		}while(data != null);
		
		closeSocket();
		
	}

	public String readData() {
		try{
			String data = input.readLine();
			return data;
		}catch(Exception e) {e.printStackTrace(); return null;}
	}
	
	public int writeData(String write) {
		try{
			output.println(write);
			return 1;
		}catch(Exception e) {e.printStackTrace(); return 0;}
	}
	
	public int closeSocket() {
		try{
			output.close();
			input.close();
			socket.close();
			return 1;
		}catch(Exception e) {e.printStackTrace(); return 0;}
		
		
		
	}

}
