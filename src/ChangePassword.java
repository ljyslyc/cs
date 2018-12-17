import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


public class ChangePassword {
	public ChangePassword() {
		
		 JFrame f1=new JFrame("修改密码");
		 
		 f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container c=f1.getContentPane();
		
		 BJPanel p=new BJPanel();
		 f1.setLocation(350,200);
		 f1.setSize(p.getWidth(), p.getHeight());
		 c.add(p);
		 p.setBackground(Color.lightGray);
		 p.setBorder(new EmptyBorder(5, 5, 5, 5));
		 p.setLayout(new BorderLayout(0, 0));
		 f1.setContentPane(p);
		 p.setLayout(null);
		 
		 

			JLabel label = new JLabel("\u539F\u59CB\u5BC6\u7801");
			label.setForeground(Color.white);
			label.setBounds(175, 22, 54, 15);
			p.add(label);
			JPasswordField passwordField = new JPasswordField();
			passwordField.setBounds(230, 20, 120, 21);
			p.add(passwordField);
			
			JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801");
			label_1.setForeground(Color.white);
			label_1.setBounds(175, 72, 54, 15);
			p.add(label_1);
			
			JPasswordField passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(230, 70, 120, 21);
			p.add(passwordField_1);
			
			JLabel label_2 = new JLabel("\u518D\u6B21\u786E\u8BA4\u5BC6\u7801");
			label_2.setForeground(Color.white);
			label_2.setBounds(175, 132, 54, 15);
			p.add(label_2);
			
			JPasswordField passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(230, 130, 120, 21);
			p.add(passwordField_2);
			
			JLabel label_3 = new JLabel("\u7528\u6237\u540D");
			label_3.setForeground(Color.white);
			label_3.setBounds(175, 182, 54, 15);
			p.add(label_3);
			
			JTextArea textArea = new JTextArea();
			textArea.setBounds(230, 180, 120, 21);
			p.add(textArea);
			//textArea.setText(this.toString());
			
			JButton button = new JButton("\u786E\u8BA4");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textArea.getText();
					char[] p = passwordField.getPassword();
					String password = new String(p);
					char[] p1 = passwordField_1.getPassword();
					String password1 = new String(p1);
					char[] p2 = passwordField_2.getPassword();
					String password2 = new String(p2);
					if(!password1.equals(password2))
					{
						new DealMessage("您两次输入的信息不一样，请重新输入");
						//wm.setVisible(true);
					}
					else
					{
						
						User use;
						try
						{
							use = DataProcessing.searchUser(name, password);
							if(use!=null)
							{
								if(true==use.changeUserInfo(password1))
								{
									 new DealMessage("修改密码成功");
									 f1.setVisible(false);
								}
								
							}
						}
						catch(SQLException e1)
						{
							System.out.println(e1.getMessage());
						}
					}
				}
			});
		
			button.setBounds(180, 250, 60, 20);
			p.add(button);
			
			JButton button_1 = new JButton("\u53D6\u6D88");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_1.setBounds(320, 250, 60, 20);
			p.add(button_1);
			//textArea.setText(this.getName());
			f1.setVisible(true);
		}
		 
		 		 
		 
		 
	}
	
	

