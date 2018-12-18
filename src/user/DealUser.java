package user;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class DealUser {
	public	DealUser() {
		 JFrame f1=new JFrame("用户管理");
		 
		 f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //Container c =f1.getContentPane();
		 BJPanel p=new BJPanel();
		 BJPanel p1=new BJPanel();
		 BJPanel p2=new BJPanel();
		 f1.setBounds(350,200,p.getWidth(), p.getHeight());
		 f1.add(p);
		 f1.add(p1);
		 f1.add(p2);
		 p.setBackground(Color.lightGray);
		 p1.setBackground(Color.lightGray);
		 p2.setBackground(Color.lightGray);
		 
			p.setBorder(new EmptyBorder(5, 5, 5, 5));
			p.setLayout(new BorderLayout(0, 0));
			f1.setContentPane(p);
			p.setLayout(null);
			
			
			/*  添加用户  */
			JTabbedPane pane = new JTabbedPane();
			p = new BJPanel();
			p.setBorder(new EmptyBorder(5, 5, 5, 5));
			 p.setBackground(Color.lightGray);

			
			
			pane.addTab("添加用户", p);
			p.setLayout(null);
			
			JLabel label = new JLabel("用户名");
			label.setForeground(Color.white);
			label.setBounds(170, 40, 54, 15);
			p.add(label);
			
			JTextField textField = new JTextField();
			textField.setBounds(230, 40, 120, 21);
			p.add(textField);
			textField.setColumns(10);
			
			JLabel label_1 = new JLabel("初始密码");
			label_1.setForeground(Color.white);
			label_1.setBounds(170, 100, 60, 15);
			p.add(label_1);
			
			JTextField textField_1 = new JTextField();
			textField_1.setBounds(230, 100, 120, 21);
			p.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel label_2 = new JLabel("用户身份");
			label_2.setForeground(Color.white);
			label_2.setBounds(170, 162, 60, 15);
			p.add(label_2);
			

			JRadioButton rdbtn_1 = new JRadioButton("administrator");
			//rdbtn_1.setBackground(Color.gray);
			rdbtn_1.setBounds(230, 150, 120, 30);
			p.add(rdbtn_1);
			
			JRadioButton rdbtn_2 = new JRadioButton("operator");
			rdbtn_2.setBounds(230, 180, 120, 30);
			p.add(rdbtn_2);
			
			JRadioButton rdbtn_3 = new JRadioButton("browser");
			rdbtn_3.setBounds(230, 210, 120, 30);
			p.add(rdbtn_3);
			
			JButton	button = new JButton("添加");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField.getText();
					String password = textField_1.getText();
					String role=null;
					if(rdbtn_1.isSelected())
					role=rdbtn_1.getText();	
					if(rdbtn_2.isSelected())
						role=rdbtn_2.getText();	
					if(rdbtn_3.isSelected())
							role=rdbtn_3.getText();	
					try
					{
						if(true==DataProcessing.insertUser(name, password, role))
						{
							new DealMessage("角色添加成功");
							f1.setVisible(false);
						}
						
						else
							new DealMessage("角色添加失败");
					}
					catch(SQLException e1)
					{
						new DealMessage("角色添加出现异常");
					}
					
				}
			});
			button.setBounds(180, 250, 60, 20);
			p.add(button);
			
			JButton button_1 = new JButton("取消");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_1.setBounds(320, 250, 60, 20);
			p.add(button_1);
			
			
			/*  删除用户  */
			
			pane.addTab("删除用户", p1);
			p1.setLayout(null);
			String[] str = {"名字","口令","角色"};
			String[][] s = new String[50][3];
			
			try
			{
				User use;
				Enumeration<User> e  = DataProcessing.getAllUser();
				
				for(int i=0;e.hasMoreElements();i++)
				{
					use = e.nextElement();
					s[i][0] = use.getName();
					s[i][1] = use.getPassword();
					s[i][2] = use.getRole();
				}
			}
			catch(SQLException e)
			{
				new  DealMessage("用户加载失败");
			}
			
			JTable table = new JTable(s,str);
			table.setForeground(Color.lightGray);
			table.setBounds(120, 60, 300, 150);

			p1.add(table);
			
			JButton button_2 = new JButton("删除");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = table.getSelectedRow();
					String name = s[index][0];
					
					try
					{
						if(true==DataProcessing.deleteUser(name))
							{
								new  DealMessage("删除用户成功");
								f1.setVisible(false);
							}
						else
							new  DealMessage("删除用户失败");		
						}
						catch(SQLException e1)
						{
							new  DealMessage("数据库操作出现问题");
						}
				}
			});
			button_2.setBounds(180, 250, 60, 20);
			p1.add(button_2);
			
			 JButton button_3 = new JButton("取消");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_3.setBounds(320, 250, 60, 20);
			p1.add(button_3);

			/*  修改用户信息  */
			
			pane.addTab("修改用户信息", p2);
			p2.setLayout(null);
			
			JLabel label_3 = new JLabel("用户名");
			label_3.setForeground(Color.white);
			label_3.setBounds(175, 22, 54, 15);
			p2.add(label_3);
			
			JTextField textField_3 = new JTextField();
			textField_3.setForeground(Color.lightGray);
			textField_3.setBounds(230, 20, 120, 21);
			p2.add(textField_3);
			textField_3.setColumns(10);
			
			 JLabel label_4 = new JLabel("原密码");
			 label_4.setForeground(Color.white);
			label_4.setBounds(175, 72, 54, 15);
			p2.add(label_4);
			
			JTextField textField_4 = new JTextField();
			textField_4.setForeground(Color.lightGray);
			textField_4.setBounds(230, 70, 120, 21);
			p2.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel label_5 = new JLabel("用户身份");
			label_5.setForeground(Color.white);
			label_5.setBounds(175, 132, 54, 15);
			p2.add(label_5);
			
			JTextField textField_5 = new JTextField();
			textField_5.setBounds(230, 130, 120, 21);
			p2.add(textField_5);
			textField_5.setColumns(10);
			
			JLabel label_6 = new JLabel("新密码");
			label_6.setForeground(Color.white);
			label_6.setBounds(175, 182, 54, 15);
			p2.add(label_6);
			
			JTextField textField_6 = new JTextField();
			textField_6.setBounds(230, 180, 120, 21);
			p2.add(textField_6);
			textField_6.setColumns(10);
			
			JButton button_4 = new JButton("确认");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField_3.getText();
					String password = textField_4.getText();
					String role = textField_5.getText();
					String newPass = textField_6.getText();
					
					@SuppressWarnings("unused")
					User user;
					try
					{
						if((user=DataProcessing.searchUser(name, password))!=null)
						{
							DataProcessing.updateUser(name, newPass, role);
							new DealMessage("修改用户成功");
							f1.setVisible(false);
						}
						else
							new DealMessage("未找到该用户，请您再次输入");
					}
					catch(SQLException e1)
					{
						new DealMessage("数据库操作出现问题");
					}
				}
			});
			button_4.setBounds(180, 250, 60, 20);
			p2.add(button_4);
			
			JButton button_5 = new JButton("取消");
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_5.setBounds(320, 250, 60, 20);
			p2.add(button_5);
			
			
			f1.setContentPane(pane);
			f1.setVisible(true); 
		
	}

}
