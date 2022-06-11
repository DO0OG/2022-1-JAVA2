package lib_proj;

import lib_proj.*;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class manageLogin extends JFrame {

	private JPanel contentPane;
	private JTextField m_id;
    private JPasswordField m_pw;

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
        
        System.out.println("Connected");
            
        return conn;
    }

	public manageLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 410, 37);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 57, 410, 194);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 410, 30);
		panel.add(lblNewLabel_1);
		
		m_id = new JTextField();
		m_id.setHorizontalAlignment(SwingConstants.CENTER);
		m_id.setBounds(147, 50, 116, 21);
		panel.add(m_id);
		m_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 83, 410, 30);
		panel.add(lblNewLabel_2);
		
		m_pw = new JPasswordField();
		m_pw.setHorizontalAlignment(SwingConstants.CENTER);
		m_pw.setBounds(147, 123, 116, 21);
		panel.add(m_pw);
		
		JButton login = new JButton("Login");
		login.setBounds(74, 161, 97, 23);
		panel.add(login);
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==login) {
					String uid = m_id.getText();
					String upw = m_pw.getText();
			        
			        //입력확인
			        if(m_id.getText().equals("")) {
						JOptionPane.showMessageDialog(null,  "Enter your ID");
					}
					else if(m_pw.getText().equals("")) {
						JOptionPane.showMessageDialog(null,  "Enter your Password");
					}
					else if(!m_id.getText().equals("space")) {
						JOptionPane.showMessageDialog(null,  "Wrong ID");
					}
					else if(!m_pw.getText().equals("123456")) {
						JOptionPane.showMessageDialog(null,  "Wrong Password");
					}
					else if(m_id.getText().equals("space") & m_pw.getText().equals("123456")){
						managePage frame = new managePage();
						frame.setTitle("Manage");
						frame.setVisible(true);
				        setVisible(false);
					}
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(234, 161, 97, 23);
		panel.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==cancel) {
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
					manageLogin frame = new manageLogin();
					frame.setTitle("Manager Login");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
