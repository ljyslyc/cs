import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JFrame;

import java.io.*;


public class Operator extends User{
//	String uploadpath="d:\\uploadfile\\";
//	String downloadpath="d:\\downloadfile\\";
	
	
	Operator(String name,String password,String role){
		super(name,password,role);
	}
	
	public boolean uploadFile(String sourcefile, String ID, String description) throws SQLException,IOException{
		
		//boolean result=false;
		byte[] buffer = new byte[1024];
		
		File tempFile =new File(sourcefile.trim());
		String filename = tempFile.getName();
		
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(uploadpath+filename)); 

		while (true) {
			int byteRead=infile.read(buffer); //���ļ������ݸ��ֽ�����
            if (byteRead==-1) //���ļ�β�������ݿɶ�
                break;  //�˳�ѭ��           
            targetfile.write(buffer,0,byteRead);  //������������д��Ŀ���ļ�
        }
		infile.close();
		targetfile.close();
        //System.out.println("copy success! ");
        
        String creator= this.getName();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //insert record into database
        //result=
		/*if (!DataProcessing.insertDoc(ID, creator,timestamp,description, filename, )){
			//System.out.println("�ϴ��ļ����ִ���");
			tempFile =new File(uploadpath+filename);
			tempFile.delete();
			return false;
		}else
			return true;
		*/
		return false;
	}	

	
	public int showMenu(){
		OpeFrame adFrame=new OpeFrame(this.getName());
//		while(adFrame.returnfeg()==false)
//		{
//			System.out.println(" ");
//		}
//		return 1;
		return 0;
        }	
	public static void main(String[] args) {
		new OpeFrame("text");
	}
	
}

