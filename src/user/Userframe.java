package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Userframe extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userframe frame = new Userframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Userframe() {
		setTitle("\u7528\u6237\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 32, 436, 231);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u4FEE\u6539\u7528\u6237", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(47, 21, 68, 24);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u53E3\u4EE4");
		label.setBounds(47, 73, 68, 24);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u89D2\u8272");
		label_1.setBounds(47, 123, 68, 24);
		panel.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 22, 193, 24);
		String[] code = new String[100];
		int i = 0, j = 0;
		Enumeration<User> users = null;
		try {
			users = DataProcessing.getAllUser();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while(users.hasMoreElements()) {
			User user = users.nextElement();
			code[i] = user.getName();
			i++;
		}
		while(code[j] != null) {
			comboBox.addItem(code[j]);
			j++;
		}

		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(111, 123, 193, 24);
		comboBox_1.addItem("Administrator");
		comboBox_1.addItem("Operator");
		comboBox_1.addItem("Browser");
		panel.add(comboBox_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 74, 193, 24);
		panel.add(passwordField);
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox.getSelectedItem().toString();
				String psw = String.valueOf(passwordField.getPassword());
				String role = comboBox_1.getSelectedItem().toString();
//				try {
//					DataProcessing.updateUser(name, psw, role);
					String message = " CLIENT_USER_MOD\n" + name;
					//MyClient.sendMessage(message);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				//MyClient.sendMessage(name, psw, role);
			}
		});
		button.setBounds(98, 160, 97, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(193, 160, 97, 23);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u5220\u9664\u7528\u6237", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 411, 149);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u53E3\u4EE4", "\u89D2\u8272"
			}
		));
		
		Enumeration<User> e = null;
		try {
			e = DataProcessing.getAllUser();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int row = 0;
		while(e.hasMoreElements()) {
			User user = e.nextElement();
			table.setValueAt(user.getName(), row, 0);
			table.setValueAt(user.getPassword(), row, 1);
			table.setValueAt(user.getRole(), row, 2);
			row++;
		}
		
		scrollPane.setViewportView(table);
		
		JButton button_4 = new JButton("\u5220\u9664");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				String ID = table.getValueAt(index,0).toString();
//				try {
//					DataProcessing.deleteUser(ID);
					String message =  " CLIENT_DELETE_USER\n" + ID;
					//MyClient.sendMessage(message);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
					//MyClient.sendMessage(ID, "NULL");
			}
		});
		button_4.setBounds(97, 169, 97, 23);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("\u53D6\u6D88");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_5.setBounds(192, 169, 97, 23);
		panel_1.add(button_5);
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u65B0\u589E\u7528\u6237", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D");
		label_2.setBounds(47, 22, 68, 24);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("\u53E3\u4EE4");
		label_3.setBounds(47, 73, 68, 24);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("\u89D2\u8272");
		label_4.setBounds(47, 127, 68, 24);
		panel_2.add(label_4);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(137, 75, 193, 24);
		panel_2.add(passwordField_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(137, 127, 193, 24);
		comboBox_2.addItem("Administrator");
		comboBox_2.addItem("Operator");
		comboBox_2.addItem("Browser");
		panel_2.add(comboBox_2);
		
		textField = new JTextField();
		textField.setBounds(137, 24, 193, 24);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton button_2 = new JButton("\u6DFB\u52A0");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String psw = String.valueOf(passwordField_1.getPassword());
				String role = comboBox_2.getSelectedItem().toString();
//				try {
//					DataProcessing.insertUser(name, psw, role);
					String message = " CLIENT_ADD_USER\n" + name;
					//MyClient.sendMessage(message);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				//MyClient.sendMessage(name, psw, role);
			}
		});
		button_2.setBounds(97, 169, 97, 23);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(192, 169, 97, 23);
		panel_2.add(button_3);
		
		
	}
}
