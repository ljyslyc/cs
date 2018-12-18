package user;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

public class textframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textframe frame = new textframe();
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
	public textframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnAdminstor = new JRadioButton("administrator");
		rdbtnAdminstor.setBounds(120, 100, 157, 30);
		contentPane.add(rdbtnAdminstor);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("operator");
		rdbtnNewRadioButton.setBounds(120, 130, 157, 30);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("browser");
		rdbtnNewRadioButton_1.setBounds(120, 160, 157, 30);
		contentPane.add(rdbtnNewRadioButton_1);
	}
}
