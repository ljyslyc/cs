package sever;


import java.io.*;
import java.net.*;


public class MyServer {
	
	public static void main(String[] args) throws Exception {
	
		ServerSocket ss=new ServerSocket(12345);
		System.out.println("Waiting connection");
		String driverName = "com.mysql.jdbc.Driver"; // �������ݿ�������
		String url = "jdbc:mysql://localhost:3306/document?useSSL=false"; // �������ݿ��URL  ��ǰ�汾��MySQLҪ��ʹ��SSL����������������ݿ������ִ������?useSSL=false4 
		String user = "root"; // ���ݿ��û�
		String password = "2073710110mm";// ���ݿ�����
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

