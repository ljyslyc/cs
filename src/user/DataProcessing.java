package user;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JFrame;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DataProcessing {

	private static Socket socket;
	private static ObjectInputStream objin;
	private static ObjectOutputStream objout;
	private static FileOutputStream fos;
	private static String downpath = "E:\\downloadpath\\";
	private static DataOutputStream dos;

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	@SuppressWarnings("unused")
	private static ResultSetMetaData metaData;
	@SuppressWarnings("unused")
	private static int numberOfRows;

	private static boolean connectedToDatabase = false;

	public static void connectToDatabase(String driverName, String url, String user, String password) throws Exception {
		Class.forName(driverName); // 加载数据库驱动类

		connection = DriverManager.getConnection(url, user, password); // 建立数据库连接
		connectedToDatabase = true;
	}

	public static void disconnectFromDatabase() {
		if (connectedToDatabase) {
			// close Statement and Connection
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				connectedToDatabase = false;
			}
		}
	}

	public static void Init() throws IOException {

		socket = new Socket("localhost", 12345);
		objout = new ObjectOutputStream(socket.getOutputStream());
		objout.flush();
		objin = new ObjectInputStream(socket.getInputStream());

	}

	public static Enumeration<Doc> getAllDocs() throws SQLException, IllegalStateException {
		Vector<Doc> docs = new Vector<Doc>();
		Doc temp = null;
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "select * from doc_info";
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String ID = resultSet.getString("id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			temp = new Doc(ID, creator, timestamp, description, filename);
			docs.addElement(temp);

		}

		return docs.elements();

	}

	public static User searchUser(String name, String pwd) throws Exception {
		User temp = null;
		String role;
		objout.writeObject("dealuser");
		objout.flush();
		objout.writeObject("searchUser");
		objout.flush();
		objout.writeObject(name);
		objout.flush();
		objout.writeObject(pwd);
		objout.flush();
		Boolean suc = (Boolean) objin.readObject();
		if (suc) {
			role = (String) objin.readObject();
			if (role.equals("administrator")) {
				temp = new Administrator(name, pwd, role);
			} else if (role.equals("operator")) {
				temp = new Operator(name, pwd, role);
			} else
				temp = new Browser(name, pwd, role);
		}
		return temp;
	}

	public static Enumeration<User> getAllUser() throws SQLException, IllegalStateException {
		Vector<User> users = new Vector<User>();
		User temp = null;
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "select * from user_info";
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");

			if (role.equals("administrator")) {
				temp = new Administrator(username, password, role);
			} else if (role.equals("operator")) {
				temp = new Operator(username, password, role);
			} else
				temp = new Browser(username, password, role);

			users.addElement(temp);
		}

		return users.elements();
	}

	public static boolean updateUser(String name, String password, String role) throws Exception {
		objout.writeObject("dealuser");
		objout.flush();
		objout.writeObject("updateUser");
		objout.flush();
		objout.writeObject(name);
		objout.flush();
		objout.writeObject(password);
		objout.flush();
		objout.writeObject(role);
		objout.flush();
		Boolean suc = (Boolean) objin.readObject();
		return suc.booleanValue();
	}

	public static boolean insertUser(String name, String password, String role) throws Exception {
		objout.writeObject("dealuser");
		objout.flush();
		objout.writeObject("insertUser");
		objout.flush();
		objout.writeObject(name);
		objout.flush();
		objout.writeObject(password);
		objout.flush();
		objout.writeObject(role);
		objout.flush();
		Boolean suc = (Boolean) objin.readObject();
		return suc.booleanValue();
	}

	public static boolean deleteUser(String name) throws Exception {
		objout.writeObject("dealuser");
		objout.flush();
		objout.writeObject("deleteUser");
		objout.flush();
		objout.writeObject(name);
		objout.flush();
		Boolean suc = (Boolean) objin.readObject();
		return suc.booleanValue();
	}

	public static void searchDoc(String id) throws Exception {
		NetTransferworm ntOut = new NetTransferworm();
		ntOut.action = "download";
		objout.writeObject(ntOut.action);
		int a = Integer.parseInt(id);
		ntOut.id = a;
		objout.writeObject(a);
		String fileName = (String) objin.readObject();
		long fileLength = (long) objin.readObject();
		fos = new FileOutputStream(new File(downpath + fileName));
		byte[] sendBytes = new byte[1024];
		int transLen = 0;
		System.out.println("----开始接收文件<" + fileName + ">,文件大小为<" + fileLength + ">----");
		while (true) {
			int read = 0;
			sendBytes = (byte[]) objin.readObject();
			read = sendBytes.length;
			transLen += read;
			if (transLen >= fileLength - 1)
				break;
			System.out.println("正在下载，请稍候....");
			fos.write(sendBytes, 0, read);
			fos.flush();
		}
		System.out.println("----接收文件<" + fileName + ">成功-------");
		if (fos != null)
			fos.close();

	}

	public static void insertDoc(String path, String creator, Timestamp timestamp, String description, String filename,
			long filelength) throws Exception {

		NetTransferworm netTransferout = new NetTransferworm();
		netTransferout.action = "upload";
		objout.writeObject(netTransferout.action);
		objout.flush();
		netTransferout.creator = creator;
		objout.writeObject(netTransferout.creator);
		objout.flush();
		netTransferout.description = description;
		objout.writeObject(netTransferout.description);
		objout.flush();
		netTransferout.fileName = filename;
		objout.writeObject(netTransferout.fileName);
		objout.flush();
		netTransferout.time = timestamp;
		objout.writeObject(netTransferout.time);
		objout.flush();
		netTransferout.filelength = filelength;
		objout.writeObject(netTransferout.filelength);
		objout.flush();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		byte[] sendbyte = new byte[1024];
		try {
			while ((len = fis.read(sendbyte)) != -1) {
				objout.writeObject(sendbyte);
				objout.flush();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();

		}
	}

	public static int shutdown() throws Exception {

		NetTransferworm netTransferout = new NetTransferworm();
		netTransferout.action = "shutdown";
		objout.writeObject(netTransferout.action);

		return JFrame.EXIT_ON_CLOSE;
	}

	protected void finalize() {
		try {
			DataProcessing.shutdown();
			socket.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}