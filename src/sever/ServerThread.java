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
    private ObjectOutputStream objout;
    private DataInputStream	 in;
    private NetTransferworm netTransferin;
	public ServerThread(Socket s) {
		this.s = s;
//		try {
//			
////			in =new DataInputStream(s.getInputStream());
////			dos =new DataOutputStream(s.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public void downloadfile(int id) throws Exception {	
	  Doc doc=DataProcessing.searchDoc(id);
      File file =new File(resourcepath+doc.getFilename());
      fis =new FileInputStream(file);
      dos =new DataOutputStream(s.getOutputStream());
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
		   in =new DataInputStream(s.getInputStream());
		   FileOutputStream   fos =new FileOutputStream(new File(resourcepath +fileName));
		   NetTransferworm netTransferwormin;
		   
		   int transLen =0;
		   System.out.println("----��ʼ�����ļ�<" + fileName +">,�ļ���СΪ<" + filelength +">----");
		   int length=0;
		   byte[] sendbyte=new byte[1024];
		   while(true){
		       int read =0;
		       netTransferwormin=(NetTransferworm) objin.readObject();
		       sendbyte=netTransferwormin.sendb.getBytes();
		       read=sendbyte.length;
		       if(read <=0)
		           break;
		       System.out.println("�����ϴ������Ժ�....");
		       fos.write(sendbyte,0,read);
		       fos.flush();
		   }
		   System.out.println("----�����ļ�<" +fileName +">�ɹ�-------");
//		   if(in !=null)
//		      in.close();
		   if(fos !=null)
		       fos.close();
		   
		   DataProcessing.insertDoc("0003",creator, time,  desciption,fileName);

	}
   public void  userlogin() throws IOException 
   {
	 //  DataInputStream	 in =new DataInputStream(s.getInputStream());
	
	//=============�û���¼����ģ��
			
   }
   
	public void run() {
		 try {
			 objin=new ObjectInputStream(s.getInputStream());
			 objout =new ObjectOutputStream (s.getOutputStream());
		while(true) {
				    netTransferin=(NetTransferworm) objin.readObject();
				    
					if(netTransferin!=null)
					{
						System.out.println(netTransferin.action+"  "+netTransferin.creator);
						//������ȡworm���ӵ���Ϊ
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
					}
					//objin.close();///....................
		}
		
			System.out.println("���������ѹر�");
			s.close();
			objin.close();
			in.close();
			dos.close();
				} catch (Exception  e) {
					e.printStackTrace();
				}
				
//		try {
//			s.close();
//			objin.close();
//			in.close();
//			dos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		}
	
}
		
		


