package user;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OpeFrame {
	boolean feg;
	public boolean returnfeg() {
		return  feg;
	}
	public OpeFrame(String keyusername) {
				JFrame f1= new JFrame("档案管理员界面");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   BJPanel p=new BJPanel();
	   f1.setLocation(350,200);
		 f1.setSize(p.getWidth(), p.getHeight());
	   p.setBackground(Color.lightGray);
		f1.setBackground(Color.lightGray);
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
					System.out.println("退出");
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
			JMenu menu = new JMenu("文档管理");
			menuBar.add(menu);
			
			JMenuItem menuItem= new JMenuItem("上传");
			
			menu.add(menuItem);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Fileload(keyusername);
				}
			});
			
			JMenuItem menuItem_1 = new JMenuItem("下载");
			menuItem_1.setEnabled(true);
			menu.add(menuItem_1);
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Fileload(keyusername);
				}
			});
		
			
			
			
			JMenu menu_1= new JMenu("个人信息管理");
			menuBar.add(menu_1);
			
			JMenuItem menuItem_3 = new JMenuItem("修改密码");
			menuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChangePassword();
				}
			});
			menu_1.add(menuItem_3);
			
			
			
			
			

			f1.setVisible(true);
			feg=false;
			
	}

}
