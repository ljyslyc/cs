package user;
import java.io.*;
import java.sql.*;

public class NetTransferworm implements Serializable {

	public static final long serialVersionUID=1L;
	/**
	 * 信息处理
	 */
	public String action;
	public String creator="jack";
	public int id;
	public String description;
	public String fileName;
	public Timestamp time;
    public long filelength;
	public String paras;

	public boolean ifRun;
	public NetTransferworm() {
		// TODO Auto-generated constructor stub
	}
	public NetTransferworm(String action, String creator, int id, String description, String fileName, Timestamp time,
			long filelength, String paras, boolean ifRun) {
		super();
		this.action = action;
		this.creator = creator;
		this.id = id;
		this.description = description;
		this.fileName = fileName;
		this.time = time;
		this.filelength = filelength;
		this.paras = paras;
		this.ifRun = ifRun;
	}
}
