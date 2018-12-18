package user;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Browser extends User {
	Browser(String name,String password,String role){
		super(name,password,role);
	}
	
	public int showMenu(){
		BrFrame adFrame=new BrFrame();
//		while(adFrame.returnfeg()==false)
//		{
//			System.out.println(" ");
//		}return 1;
		return 0;
		
	}
}
