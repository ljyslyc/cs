package sever;

import java.io.*;
import java.net.*;
import java.sql.*;

public class ServerThread extends Thread {
	private Socket s;
    private String resourcepath = "E:\\uploadpath\\";
    private FileInputStream fis;
    private DataOutputStream dos;
    private ObjectInputStream objin;
    private DataInputStream	 in ;
    private NetTransferworm netTransferin;
    
	public ServerThread(Socket s) {
		this.s = s;
		try {
			dos =new DataOutputStream(s.getOutputStream());
			in =new DataInputStream(s.getInputStream());
			objin=new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void downloadfile(int id) throws Exception {	
	  Doc doc=DataProcessing.searchDoc(id);
      File file =new File(resourcepath+doc.getFilename());
      fis =new FileInputStream(file);
      dos.writeUTF(file.getName());
   
      dos.flush();
      dos.writeLong(file.length());
      dos.flush();
      byte[] sendBytes =new byte[1024];
      int length =0;
      try {
			while((length = fis.read(sendBytes)) !=-1){
			    dos.write(sendBytes,0, length);
			    dos.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      finally{
          if(fis !=null)
              fis.close();
//          if(dos !=null)
//              dos.close(); 
      }
	}
	public void uploadfile(String creator,String fileName,String desciption,Timestamp time, long filelength) throws Exception {
		
			String ID=null;
		
		   FileOutputStream   fos =new FileOutputStream(new File(resourcepath +fileName));
		   byte[] sendb =new byte[1024];
		   int transLen =0;
		   System.out.println("----开始接收文件<" + fileName +">,文件大小为<" + filelength +">----");
		   int length=0;
		   while((length = fis.read(sendb)) != -1){
//		       int read =0;
//		       read = in.read(sendBytes);
//		       if(read <=0)
//		           break;
		       transLen += length;
		       System.out.println("正在上传，请稍候....");
		       fos.write(sendb,0, length);
		       fos.flush();
		   }
		   System.out.println("----接收文件<" +fileName +">成功-------");
//		   if(in !=null)
//		      in.close();
		   if(fos !=null)
		       fos.close();
		   
		   DataProcessing.insertDoc("0003",creator, time,  desciption,fileName);

	}
   public void  userlogin() throws IOException {
	 //  DataInputStream	 in =new DataInputStream(s.getInputStream());
	
	//=============用户登录连接模块
			
   }
	
	
	public void run() {
	
		while(true) {
			try {
					netTransferin=(NetTransferworm) objin.readObject();
					System.out.println(netTransferin.action+"  "+netTransferin.creator);
//					//监听获取worm连接的行为
					if(netTransferin.action.equals("download"))
					{  
						downloadfile(netTransferin.id); 
					}
					if(netTransferin.action.equals("upload"))
					{  
						uploadfile(netTransferin.creator,netTransferin.fileName,netTransferin.description,netTransferin.time,netTransferin.filelength);
					}
					
					if(netTransferin.action.equals("shutdown"))
					{  
						break;
					}		
					
					//objin.close();///....................
				
				} catch (Exception  e) {
					e.printStackTrace();
				}
				
				}
		try {
			s.close();
			objin.close();
			in.close();
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
}
		
		


