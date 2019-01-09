package sever;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JFrame;
import java.io.*;
import java.net.*;
import java.sql.*;
import sever.*;
public class DataProcessing {
	private static String downpath = "E:\\downloadpath\\";
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	@SuppressWarnings("unused")
	private static ResultSetMetaData metaData;
	@SuppressWarnings("unused")
	private static int numberOfRows;
	private static boolean connectedToDatabase = false;
	public static void connectToDatabase(String driverName, String url, String user, String password) throws Exception
	  {
		Class.forName(driverName);		// 加载数据库驱动类
		connection=DriverManager.getConnection(url, user, password);   // 建立数据库连接
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
	
	public static void Init() {
		
	}

	public static Doc searchDoc(int DID) throws SQLException, IllegalStateException {
		Doc temp = null;
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String sql = "select * from doc_info where id='" + DID + "'";

		resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			String ID = resultSet.getString("id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");

			temp = new Doc(ID, creator, timestamp, description, filename);
		}

		return temp;
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

	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename)
			throws SQLException, IllegalStateException {
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		String sql = "INSERT INTO doc_info (creator,timestamp,description,filename) VALUES(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, creator);
		preparedStatement.setTimestamp(2, timestamp);
		preparedStatement.setString(3, description);
		preparedStatement.setString(4, filename);
		int temp = preparedStatement.executeUpdate();

		if (temp != 0)
			return true;
		else
			return false;

	}

	public static String searchUser(String name, String pwd) throws SQLException, IllegalStateException {
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "select * from user_info where username='" + name + "' and password='" + pwd + "'";

		resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			String username = resultSet.getString("username");
			String password1 = resultSet.getString("password");
			String role = resultSet.getString("role");
			return role;
		}
		return null;
	}

	public static boolean updateUser(String name, String password, String role)
			throws SQLException, IllegalStateException {
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		String sql = "UPDATE user_info SET password=?, role=? where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, role);
		preparedStatement.setString(3, name);

		int temp = preparedStatement.executeUpdate();

		if (temp != 0)
			return true;
		else
			return false;
	}

	public static boolean insertUser(String name, String password, String role)
			throws SQLException, IllegalStateException {
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		String sql = "INSERT INTO user_info (username,password,role) VALUES(?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, role);
		int temp = preparedStatement.executeUpdate();

		if (temp != 0)
			return true;
		else
			return false;
	}

	public static boolean deleteUser(String name) throws SQLException, IllegalStateException {
		if (!connectedToDatabase)
			throw new IllegalStateException("Not Connected to Database");

		String sql = "DELETE FROM  user_info where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);

		int temp = preparedStatement.executeUpdate();

		if (temp != 0)
			return true;
		else
			return false;
	}
}