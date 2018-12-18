package user;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DownLoadFrame {
 DownLoadFrame(){
	 
	// final long serialVersionUID = 1L;
		JTable table;
		String uppath = "E:\\upLoadfile\\";
		String downpath = "E:\\downloadfile\\";
		JTextField textField;
		JTextField textField_1;
		JFrame f1 = new JFrame("普通用户界面");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f1.getContentPane();
		BJPanel p1 = new BJPanel();
		BJPanel p2 = new BJPanel();
		f1.setLocation(350,200);
		f1.setSize(p1.getWidth(), p1.getHeight());
		
		 p1.setBackground(Color.lightGray);
		 p2.setBackground(Color.lightGray);

		FileDialog openDia = new FileDialog(f1, "打开文件", FileDialog.LOAD);
		FileDialog saveDia = new FileDialog(f1, "保存文件", FileDialog.SAVE);

		JTabbedPane doFile = new JTabbedPane();

		f1.setContentPane(doFile);

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



		f1.setVisible(true);

	 
	 
	 
 }
 public static void main(String args[])
	{
		new DownLoadFrame();
	}

}
