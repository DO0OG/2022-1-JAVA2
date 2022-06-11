package lib_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class managePage extends JFrame {

	private JPanel contentPane;

	public managePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton userManage = new JButton("\uC720\uC800 \uAD00\uB9AC");
		contentPane.add(userManage, BorderLayout.WEST);
		userManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==userManage) {
					memberList frame;
					try {
						frame = new memberList();
						frame.setTitle("MemberList");
						frame.setVisible(true);
				        setVisible(false);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton bookManage = new JButton("\uB3C4\uC11C \uAD00\uB9AC");
		contentPane.add(bookManage, BorderLayout.CENTER);
		bookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==bookManage) {
					try {
						bookManage frame = new bookManage();
						frame.setTitle("Book Manage");
						frame.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        setVisible(false);
				}
			}
		});
		
		JButton logout = new JButton("\uB85C\uADF8 \uC544\uC6C3");
		contentPane.add(logout, BorderLayout.EAST);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==logout) {
					try {
						mainPage frame = new mainPage();
						frame.setTitle("Main");
						frame.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        setVisible(false);
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managePage frame = new managePage();
					frame.setTitle("Manage Page");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
