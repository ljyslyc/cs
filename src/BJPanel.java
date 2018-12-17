
import java.awt.*; 
import javax.swing.*;
public class BJPanel  extends JPanel { 
	Image image;
	BJPanel()
	{ 
	ImageIcon images=new ImageIcon("text.jpg");
	image=Toolkit.getDefaultToolkit().getImage("text.jpg");//需要注意的是如果用相对路径载入图片,则图片文件必须放在类文件所在文件夹或项目的根文件夹中,否则必须用绝对路径。 
	this.setSize(images.getIconWidth(),images.getIconHeight());
	}
	BJPanel(String adr)
	{ 
		ImageIcon images=new ImageIcon(adr);
		image=Toolkit.getDefaultToolkit().getImage(adr);//需要注意的是如果用相对路径载入图片,则图片文件必须放在类文件所在文件夹或项目的根文件夹中,否则必须用绝对路径。 
		this.setSize(images.getIconWidth(),images.getIconHeight());
	}
	@Override
	public void paintComponent(Graphics g) 
	{
	super.paintComponent(g); 
	int imWidth=image.getWidth(this); 
	int imHeight=image.getHeight(this); //定义图片的宽度、高度 
	int FWidth=getWidth(); 
	int FHeight=getHeight();//定义窗口的宽度、高度 
	int x=(FWidth-imWidth)/2; 
	int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
	g.drawImage(image,x,y,null);//绘制图片 
	} 

}
