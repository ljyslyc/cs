package user;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Fileload {
	public Fileload(String keyusername) {

		final long serialVersionUID = 1L;
		JTable table;
		String uppath = "E:\\uploadpath\\";
		String downpath = "E:\\downloadpath\\";
		JTextField textField;
		JTextField textField_1;
		JFrame f1 = new JFrame("档案管理员界面");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = f1.getContentPane();
		BJPanel p1 = new BJPanel();
		BJPanel p2 = new BJPanel();
		f1.setLocation(350,200);
		 f1.setSize(p1.getWidth(), p1.getHeight());
        c.add(p1);
        c.add(p2);
		FileDialog openDia = new FileDialog(f1, "打开文件", FileDialog.LOAD);
		FileDialog saveDia = new FileDialog(f1, "保存文件", FileDialog.SAVE);

		JTabbedPane doFile = new JTabbedPane();
		f1.setContentPane(doFile);

	
		 p1.setBackground(Color.lightGray);
		
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		doFile.addTab("文件下载", p1);
		p1.setLayout(null);

		String[] columnName = { "索引号", "上传者", "上传时间", "文件描述", "文件名" };
		String[][] tableValue = new String[50][5];
		try {
			Enumeration<Doc> e = DataProcessing.getAllDocs();
			Doc myDoc;
			for (int i = 0; e.hasMoreElements(); i++) {
				myDoc = e.nextElement();
				tableValue[i][0] = myDoc.getID();
				tableValue[i][1] = myDoc.getCreator();
				tableValue[i][2] = myDoc.getTimestamp().toString();
				tableValue[i][3] = myDoc.getDescription();
				tableValue[i][4] = myDoc.getFilename();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}

		table = new JTable(tableValue, columnName);
		table.setForeground(Color.lightGray);
		table.setBounds(50, 60, 500, 240);

		p1.add(table);

		// 下载
		
		JButton button = new JButton("下载");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DealMessage("您确定要下载该文件吗？");
				int index = table.getSelectedRow();
				String id = tableValue[index][0];
				try {
				
					DataProcessing.searchDoc(id);
					
					
				} catch (Exception e1) {
					new DealMessage("文件流操作失败");
				}
	
				new DealMessage("下载该文件成功");
			}

		});

		button.setBounds(180, 320, 60, 20);
		p1.add(button);

		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.setVisible(false);
			}
		});

		button_1.setBounds(320, 320, 60, 20);
		p1.add(button_1);

		// 下载文件描述

		JLabel lblNewLabel = new JLabel("档案号");
		lblNewLabel.setBounds(80, 40, 54, 15);
		lblNewLabel.setForeground(Color.white);
		p1.add(lblNewLabel);

		JLabel label_3 = new JLabel("上传者");
		
		label_3.setBounds(180, 40, 54, 15);
		label_3.setForeground(Color.white);
		p1.add(label_3);

		JLabel label_4 = new JLabel("上传时间");
		
		label_4.setBounds(270, 40, 54, 15);
		label_4.setForeground(Color.white);
		p1.add(label_4);

		JLabel label_5 = new JLabel("文件描述");
		
		label_5.setBounds(370, 40, 54, 15);
		label_5.setForeground(Color.white);
		p1.add(label_5);

		JLabel label_6 = new JLabel("文件名");
		
		label_6.setBounds(475, 40, 54, 15);
		label_6.setForeground(Color.white);
		p1.add(label_6);

		// 上传文件

		doFile.addTab("文件上传", p2);
		p2.setLayout(null);
		 p2.setBackground(Color.lightGray);
		JLabel label = new JLabel("请输入上传文件档案号");
		label.setForeground(Color.white);
		label.setBounds(80, 50, 150, 15);
		p2.add(label);

		textField = new JTextField();
		textField.setBounds(50, 80, 200, 21);
		p2.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("请输入对档案的描述");
		label_1.setForeground(Color.white);
		label_1.setBounds(390, 20, 120, 15);
		p2.add(label_1);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(350, 50, 200, 200);
		p2.add(textArea);

		JLabel label_2 = new JLabel("请确定上传文件所在地址");
		label_2.setForeground(Color.white);
		label_2.setBounds(80, 120, 150, 15);
		p2.add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(50, 150, 200, 21);
		p2.add(textField_1);
		textField_1.setColumns(10);

		JButton button_2 = new JButton("打开");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
				String dirpath = openDia.getDirectory();
				String filename = openDia.getFile();

				textField_1.setText(dirpath + filename);
			}
		});
		button_2.setBounds(252, 149, 60, 23);
		p2.add(button_2);

		JButton button_3 = new JButton("上传");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID = textField.getText();
				String desc = textArea.getText();
				String path = textField_1.getText();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				File f = new File(path);
				try {
					DataProcessing.insertDoc(path, keyusername, timestamp, desc, f.getName(),f.length());
				
				} catch (Exception e1) {
					e1.printStackTrace();
					new DealMessage("文件操作失败");
					f1.setVisible(false);
				}
//不论怎样都会文件上传成功
				new DealMessage("文件上传成功");
				f1.setVisible(false);
			}
		});
		button_3.setBounds(82, 220, 81, 23);
		p2.add(button_3);

		JButton button_4 = new JButton("取消");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.setVisible(false);
			}
		});
		button_4.setBounds(200, 220, 72, 23);
		p2.add(button_4);

		f1.setVisible(true);

	}
}
