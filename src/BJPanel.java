
import java.awt.*; 
import javax.swing.*;
public class BJPanel  extends JPanel { 
	Image image;
	BJPanel()
	{ 
	ImageIcon images=new ImageIcon("text.jpg");
	image=Toolkit.getDefaultToolkit().getImage("text.jpg");//��Ҫע�������������·������ͼƬ,��ͼƬ�ļ�����������ļ������ļ��л���Ŀ�ĸ��ļ�����,��������þ���·���� 
	this.setSize(images.getIconWidth(),images.getIconHeight());
	}
	BJPanel(String adr)
	{ 
		ImageIcon images=new ImageIcon(adr);
		image=Toolkit.getDefaultToolkit().getImage(adr);//��Ҫע�������������·������ͼƬ,��ͼƬ�ļ�����������ļ������ļ��л���Ŀ�ĸ��ļ�����,��������þ���·���� 
		this.setSize(images.getIconWidth(),images.getIconHeight());
	}
	@Override
	public void paintComponent(Graphics g) 
	{
	super.paintComponent(g); 
	int imWidth=image.getWidth(this); 
	int imHeight=image.getHeight(this); //����ͼƬ�Ŀ�ȡ��߶� 
	int FWidth=getWidth(); 
	int FHeight=getHeight();//���崰�ڵĿ�ȡ��߶� 
	int x=(FWidth-imWidth)/2; 
	int y=(FHeight-imHeight)/2;//����ͼƬ������,ʹͼƬ��ʾ�ڴ������м� 
	g.drawImage(image,x,y,null);//����ͼƬ 
	} 

}
