package user;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class Main {
	public static void main(String[] args) throws Exception {
		

		String driverName = "com.mysql.jdbc.Driver"; // �������ݿ�������
		String url = "jdbc:mysql://localhost:3306/document?useSSL=false"; // �������ݿ��URL
		String user = "root"; // ���ݿ��û�
		String password = "2073710110mm";// ���ݿ�����
		DataProcessing.connectToDatabase(driverName, url, user, password);
		DataProcessing.Init();
	 
		JFrame f1 = new JFrame("��¼");
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
		JLabel label_0 = new JLabel("��������ϵͳ");
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setFont(new Font("����", Font.BOLD, 30));
		label_0.setForeground(Color.lightGray);
		label_0.setBounds(90, 70, 300, 40);
		p.add(label_0);
		JLabel label = new JLabel("�û���");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("����", Font.BOLD, 20));
		label.setBounds(120, 142, 74, 25);
		label.setForeground(Color.lightGray);
		p.add(label);

		JLabel label_1 = new JLabel("����");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("����", Font.BOLD, 20));
		label_1.setForeground(Color.lightGray);
		label_1.setBounds(120, 213, 74, 25);
		p.add(label_1);

		JTextField textField = new JTextField();
		textField.setBounds(200, 142, 135, 25);
		textField.setFont(new Font(null, 0, 20));
		p.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("��¼");
		button.setBounds(130, 280, 90, 25);
		button.setFont(new Font("����", Font.BOLD, 20));
		p.add(button);
		
		JButton button_1 = new JButton("�˳�");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		button_1.setBounds(250, 280, 90, 25);
		button_1.setFont(new Font("����", Font.BOLD, 20));
		p.add(button_1);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(200, 213, 135, 25);
		p.add(passwordField);
		f1.setVisible(true);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				char[] c1 = passwordField.getPassword();
				String password = new String(c1);
				User use;
				try {
					use = DataProcessing.searchUser(name, password);
					if (use != null)

					{
						use.showMenu();
						f1.setVisible(false);
						
//						if(use.showMenu()==1)
//						{
//					    DataProcessing.disconnectFromDatabase();
//						Main aMain=new Main();
//						String[] a=new String[0];
//						main(a);
//						}
					} else
						new DealMessage("����������û����������");
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	protected void finalize()
	{
		DataProcessing.disconnectFromDatabase();
		try {
			DataProcessing.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
