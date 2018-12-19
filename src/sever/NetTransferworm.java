package sever;

import java.io.*;
import java.sql.*;

/**
 * @author 刘大哥
 *
 */
public class NetTransferworm implements Serializable {

	public static final long serialVersionUID=1L;
	/**
	 * 信息处理
	 */
	public String action;
	public String creator="jack";
	public Integer id;
	public String description;
	public String fileName;
	public Timestamp time;
    public Long filelength;
	public String paras;

	public Boolean ifRun;

	public NetTransferworm() {
		// TODO Auto-generated constructor stub
	}
	public NetTransferworm(String action, String creator, int id, String description, String fileName, Timestamp time,
			long filelength, String paras, boolean ifRun) {
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Long getFilelength() {
		return filelength;
	}
	public void setFilelength(Long filelength) {
		this.filelength = filelength;
	}
	public String getParas() {
		return paras;
	}
	public void setParas(String paras) {
		this.paras = paras;
	}
	public Boolean getIfRun() {
		return ifRun;
	}
	public void setIfRun(Boolean ifRun) {
		this.ifRun = ifRun;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
