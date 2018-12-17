import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Test{
	public static void main(String[] args) throws Exception {
		

		JFrame f1 = new JFrame("系统登录界面");
		BJPanel p = new BJPanel();
		p.setSize(new Dimension(719, 450));
		f1.setLocation(350,200);
		 f1.setSize(p.getWidth(), p.getHeight());
		 f1.setSize(719,450);
		 
		 p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		f1.setContentPane(p);
		p.setLayout(null);
		p.setBackground(Color.lightGray);
		
		p.setLocation(350,200);
		JLabel label_0 = new JLabel("档案管理系统");
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setFont(new Font("楷体", Font.BOLD, 30));
		label_0.setForeground(Color.lightGray);
		label_0.setBounds(90, 70, 300, 40);
		p.add(label_0);
		JLabel label = new JLabel("用户名");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("楷体", Font.BOLD, 20));
		label.setBounds(120, 142, 74, 25);
		label.setForeground(Color.lightGray);
		p.add(label);

		JLabel label_1 = new JLabel("密码");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("楷体", Font.BOLD, 20));
		label_1.setForeground(Color.lightGray);
		label_1.setBounds(120, 213, 74, 25);
		p.add(label_1);

		JTextField textField = new JTextField();
		textField.setBounds(200, 142, 135, 25);
		textField.setFont(new Font(null, 0, 20));
		p.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("登录");
		button.setBounds(130, 280, 90, 25);
		button.setFont(new Font("楷体", Font.BOLD, 20));
		p.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(250, 280, 90, 25);
		button_1.setFont(new Font("楷体", Font.BOLD, 20));
		p.add(button_1);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(200, 213, 135, 25);
		p.add(passwordField);
		f1.setVisible(true);
	}
	
}
