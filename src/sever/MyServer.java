package sever;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import user.DataProcessing;

public class MyServer {

	/**
	 * 服务器线程
	 */
	private static InetAddress inet = null;
    private static String serverIP = null;
    private static String serverPort = null;
    private static ServerSocket serverSocket = null;
    private static Thread serverThread= null;
	public static List<ServerThread> clients = Collections.synchronizedList(new ArrayList<ServerThread> ());
	public static List<ServerThread> getClents () {
        return clients;
    }
	
	public static void main(String[] args)  {
try{
	
		ServerSocket ss = new ServerSocket(12345);
		System.out.println("Waiting connection");
		String driverName = "com.mysql.cj.jdbc.Driver"; // 加载数据库驱动类
		String url = "jdbc:mysql://localhost:3306/document?useSSL=false&serverTimezone=GMT"; // 声明数据库的URL
		String user = "roots"; // 数据库用户
		String password = "12345678";// 数据库密码
		
		sever.DataProcessing.connectToDatabase(driverName, url, user, password);
		
		
		
		
		int i = 0;
		while (true) {
			Socket socket = ss.accept();
			i++;
			System.out.println("Accepting user" + i + " " + "Connection....");
			ServerThread uServerThread = new ServerThread(socket);
			clients.add(uServerThread);
            uServerThread.start();
		}
}catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
	}
	protected void finalize() {
		sever.DataProcessing.disconnectFromDatabase();
	}
}
