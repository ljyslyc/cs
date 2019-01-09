package sever;

import java.awt.Desktop.Action;
import java.io.*;
import java.net.*;
import java.sql.*;

public class ServerThread extends Thread {
	private static Socket s;
	private static String resourcepath = "E:\\uploadpath\\";
	private static FileInputStream fis;
	private static DataOutputStream dos;
	private static ObjectInputStream objin;
	private static ObjectOutputStream objout;
	private static DataInputStream in;
	private static NetTransferworm netTransferin;
    private static String username;
    /**
     * action信息
     * @param s
     */
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String ALLUSER = "ALLUSER";
    public static final String ADDUSER = "ADDUSER";
    public static final String CHANGEUSER = "CHANGEUSER";
    public static final String UPLOADFILE = "UPLOADFILE";
    public static final String DOWNLOADFILE = "DOWNLOADFILE";
    public static final String	ALLFILE = "ALLFILE";
    public static final String HASPRINCE = "HASPRINCE";
    public static final String	NOPRINCE = "NOPRINCE";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String UPLOAD = "upload";
    public static final String DELUSER="deleteUser";
    public static final String INSUSER="insertUser";
    public static final String UPDUSER="updateUser";
    public static final String SEARCHUSER="searchUser";
    public static final String DOWNLOAD="download";
    public static final String DEALUSER="dealuser";
    /**
     * 
     * @param s
     */
	public ServerThread(Socket s) {
		DataProcessing.Init();
		this.s = s;
		try {
			objin = new ObjectInputStream(s.getInputStream());
			objout = new ObjectOutputStream(s.getOutputStream());
			netTransferin = new NetTransferworm();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public boolean isConnected () {
        if (s != null && s.isConnected() && objin != null && objout != null)
            return true;
        else return false;
    }
	synchronized public void downloadfile(int id) throws Exception {
		Doc doc = DataProcessing.searchDoc(id);
		File file = new File(resourcepath + doc.getFilename());
		fis = new FileInputStream(file);
		objout.writeObject(file.getName());
		objout.flush();
		objout.writeObject(file.length());
		objout.flush();

		byte[] sendBytes = new byte[1024];
		int length = 0;
		try {
			while ((length = fis.read(sendBytes)) != -1) {
				objout.writeObject(sendBytes);
				objout.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
	}

	synchronized public void uploadfile(String creator, String fileName, String desciption, Timestamp time, long filelength)
			throws Exception {

		String ID = null;
		FileOutputStream fos = new FileOutputStream(new File(resourcepath + fileName));
		NetTransferworm netTransferwormin;
		int transLen = 0;
		System.out.println("----开始接收文件<" + fileName + ">,文件大小为<" + filelength + ">----");
		long length = 0;
		byte[] sendbyte = new byte[1024];
		while (true) {
			int read = 0;
			sendbyte = (byte[]) objin.readObject();
			read = sendbyte.length;
			length += read;
			if (length >= filelength - 1)
				break;
			System.out.println("正在上传，请稍候....");
			fos.write(sendbyte, 0, read);
			fos.flush();
		}
		System.out.println("----接收文件<" + fileName + ">成功-------");
		if (fos != null)
			fos.close();

		DataProcessing.insertDoc("0003", creator, time, desciption, fileName);

	}

	synchronized public void dealuser() throws Exception {
		String action, name, password, role;
		boolean suc;
		// =============用户登录连接模块
		action = (String) objin.readObject();
		switch (action) {
		case DELUSER:
			name = (String) objin.readObject();
			suc = DataProcessing.deleteUser(name);
			objout.writeObject(suc);
			objout.flush();
			break;
		case INSUSER:
			name = (String) objin.readObject();
			password = (String) objin.readObject();
			role = (String) objin.readObject();
			suc = DataProcessing.insertUser(name, password, role);
			objout.writeObject(suc);
			objout.flush();
			break;
		case UPDUSER:
			name = (String) objin.readObject();
			password = (String) objin.readObject();
			role = (String) objin.readObject();
			suc = DataProcessing.updateUser(name, password, role);
			objout.writeObject(suc);
			objout.flush();
			break;
		case SEARCHUSER:
			username = (String) objin.readObject();
			password = (String) objin.readObject();
			role = DataProcessing.searchUser(username, password);
			if (role == null) {
				objout.writeObject(false);
				objout.flush();
			} else {
				objout.writeObject(true);
				objout.flush();
				objout.writeObject(role);
				objout.flush();
			}
			break;
		}

	}

	public void getconnection() throws Exception {
		netTransferin.action = (String) objin.readObject();
		switch (netTransferin.action) {
		case UPLOAD: {
			netTransferin.creator = (String) objin.readObject();
			netTransferin.description = (String) objin.readObject();
			netTransferin.fileName = (String) objin.readObject();
			netTransferin.time = (Timestamp) objin.readObject();
			netTransferin.filelength = (Long) objin.readObject();
		}
			break;

		case DOWNLOAD: {
			netTransferin.id = (int) objin.readObject();
		}
			break;
		case DEALUSER:

			break;

		}

	}

	public void run() {
		try {

			while (isConnected()) {
				getconnection();
				if (netTransferin != null) {
					System.out.println(netTransferin.action + "  " + "action");
					// 监听获取worm连接的行为

					if (netTransferin.action.equals("download")) {
						downloadfile(netTransferin.id);
					}

					if (netTransferin.action.equals("upload")) {
						uploadfile(netTransferin.creator, netTransferin.fileName, netTransferin.description,
								netTransferin.time, netTransferin.filelength);
					}
					if (netTransferin.action.equals("dealuser"))
						dealuser();
					if (netTransferin.action.equals("shutdown")) {
						break;
					}
				}

			}

			System.out.println("用户: "+username+": 本次连接已关闭");
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
