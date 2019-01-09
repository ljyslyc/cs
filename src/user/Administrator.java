package user;
import java.util.*;
import java.sql.*;

public class Administrator extends User {
	Administrator(String name,String password,String role){
		super(name,password,role);
	}
	
	public boolean changeUserInfo(String name, String password, String role) throws Exception{		
		return (DataProcessing.updateUser(name, password, role));		
	}
	
	public boolean delUser(String name) throws Exception{
		return (DataProcessing.deleteUser(name));
			 
	}
	
	public boolean addUser(String name, String password, String role) throws Exception{
		return (DataProcessing.insertUser(name, password, role));			
	}
	
	public void listUser() throws SQLException{
		/*Enumeration<User> e  = DataProcessing.users.elements();
		User user;
		while( e.hasMoreElements() ){
			user=e.nextElement();
			System.out.println("Name:"+user.getName()+"\t Password:" +user.getPassword()+"\t Role:" +user.getRole());
		}*/
		Enumeration<User> e= DataProcessing.getAllUser();
		User user;
		while( e.hasMoreElements() ){
			user=e.nextElement();
			System.out.println("Name:"+user.getName()+"\t Password:" +user.getPassword()+"\t Role:" +user.getRole());
		}
	}
	
	public int showMenu(){
		AdFrame adFrame=new AdFrame();
//		while(adFrame.returnfeg()==false)
//		{
//			System.out.println(" ");
//		}
//		return 1;
		return 0;
	}
	
	public static void main(String[] args) {
		new AdFrame();
	}
}
