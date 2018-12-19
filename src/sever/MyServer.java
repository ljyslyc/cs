package sever;


import java.io.*;
import java.net.*;


public class MyServer {
	
	public static void main(String[] args) throws Exception {
	
		ServerSocket ss=new ServerSocket(12345);
		System.out.println("Waiting connection");
		String driverName = "com.mysql.jdbc.Driver"; // 加载数据库驱动类
		String url = "jdbc:mysql://localhost:3306/document?useSSL=false"; // 声明数据库的URL  当前版本的MySQL要求使用SSL，解决方法是在数据库连接字串里加上?useSSL=false4 
		String user = "root"; // 数据库用户
		String password = "2073710110mm";// 数据库密码
		DataProcessing.connectToDatabase(driverName, url, user, password);
		int i=0;
		//while(true) {	
			Socket socket=ss.accept();
	        i++;
			System.out.println("Accepting user"+i+" "+ "Connection....");
			ServerThread uServerThread=new ServerThread(socket);
			uServerThread.start();
	}
}

