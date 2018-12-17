import java.io.*;
import java.sql.*;

public class NetTransfer implements Serializable {

	public String action;
	public String creator="jack";
	public int id;
	public String description;
	public String fileName;
	public Timestamp time;
    public long filelength;
	public String paras;

	public boolean ifRun;

}
