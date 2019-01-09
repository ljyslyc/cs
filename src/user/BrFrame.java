package user;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class BrFrame {
	boolean feg;
	public boolean returnfeg() {
		return  feg;
	}
	public BrFrame() {
		feg=false;
		JFrame f1= new JFrame("普通用户");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   BJPanel p=new BJPanel();
	   f1.setLocation(350,200);
	   f1.setSize(p.getWidth(), p.getHeight());
	   p.setBackground(Color.lightGray);
	   p.setBorder(new EmptyBorder(5, 5, 5, 5));
		 p.setLayout(new BorderLayout(0, 0));
		 f1.setContentPane(p);
		 p.setLayout(null);
		 
		 JButton button1 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		 button1.setFont(new Font("楷体", Font.BOLD, 25));
			button1.setOpaque(false);
			button1.setSize(160, 40);
			button1.setLocation(40, 280);
			button1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("退出...");
					try {
						DataProcessing.shutdown();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});
			p.add(button1);
			
			JButton button2 = new JButton("\u6CE8\u9500\u7528\u6237");
			button2.setFont(new Font("楷体", Font.BOLD, 25));
			button2.setOpaque(false);
			button2.setBounds(40, 200, 160, 40);
			button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					feg=true;
				}
			});
			p.add(button2);
		 
			JMenuBar menuBar = new JMenuBar();
			f1.setJMenuBar(menuBar);
			
			
			
			JMenu menu_1 = new JMenu("文件管理");
			menuBar.add(menu_1);
			
			JMenuItem menuItem_4 = new JMenuItem("下载文件");
			menuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DownLoadFrame();
				}
			});
			menu_1.add(menuItem_4);
			
			JMenu menu_2 = new JMenu("修改个人信息");
			menuBar.add(menu_2);
			
			JMenuItem menuItem_5 = new JMenuItem("修改密码");
			menuItem_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChangePassword();
				}
			});
			menu_2.add(menuItem_5);
		
		
		
		
		
		
		
		
		
		
		f1.setVisible(true);
				
	}
	public static void main(String args[])
	{
		new BrFrame();
	}

}
