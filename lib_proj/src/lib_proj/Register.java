package lib_proj;

import lib_proj.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Choice;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField u_id;
	private JPasswordField u_pw;
	private JTextField u_pwck;
	private JTextField u_name;
	private JTextField u_phone;
	private JTextField u_mail;

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }
    
    public static void insert(String id, String upw, String name, String phone, String mail, String sex) throws ClassNotFoundException, SQLException {
        //입력하는 메소드
        Connection conn = getConnection();
        String sql = "insert into members(id, passwd, name, phone, mail, sex) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, id);
        pstmt.setString(2, upw);
        pstmt.setString(3, name);
        pstmt.setString(4, phone);
        pstmt.setString(5, mail);
        pstmt.setString(6, sex);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
            System.out.println("처리 완료");
        }
        
        if(pstmt != null) 
			pstmt.close();
        if(conn != null) 
			conn.close();
    }

	public Register() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(208, 10, 72, 22);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 42, 464, 422);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel id = new JLabel("ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(100, 31, 57, 15);
		panel_1.add(id);
		
		u_id = new JTextField();
		u_id.setHorizontalAlignment(SwingConstants.CENTER);
		u_id.setBounds(281, 28, 116, 21);
		panel_1.add(u_id);
		u_id.setColumns(10);
		
		JLabel pwd = new JLabel("Password");
		pwd.setHorizontalAlignment(SwingConstants.CENTER);
		pwd.setBounds(87, 79, 85, 15);
		panel_1.add(pwd);
		
		u_pw = new JPasswordField();
		u_pw.setHorizontalAlignment(SwingConstants.CENTER);
		u_pw.setBounds(281, 76, 116, 21);
		panel_1.add(u_pw);
		
		JLabel pwd_ck = new JLabel("Password Check");
		pwd_ck.setHorizontalAlignment(SwingConstants.CENTER);
		pwd_ck.setBounds(72, 126, 116, 15);
		panel_1.add(pwd_ck);
		
		u_pwck = new JTextField();
		u_pwck.setHorizontalAlignment(SwingConstants.CENTER);
		u_pwck.setBounds(281, 123, 116, 21);
		panel_1.add(u_pwck);
		u_pwck.setColumns(10);
		
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(100, 173, 57, 15);
		panel_1.add(name);
		
		u_name = new JTextField();
		u_name.setHorizontalAlignment(SwingConstants.CENTER);
		u_name.setBounds(281, 170, 116, 21);
		panel_1.add(u_name);
		u_name.setColumns(10);
		
		JLabel phone = new JLabel("Phone");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setBounds(100, 224, 57, 15);
		panel_1.add(phone);
		
		u_phone = new JTextField();
		u_phone.setHorizontalAlignment(SwingConstants.CENTER);
		u_phone.setBounds(250, 221, 182, 21);
		panel_1.add(u_phone);
		u_phone.setColumns(10);
		
		JLabel mail = new JLabel("E-Mail");
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBounds(100, 271, 57, 15);
		panel_1.add(mail);
		
		u_mail = new JTextField();
		u_mail.setHorizontalAlignment(SwingConstants.CENTER);
		u_mail.setBounds(250, 268, 182, 21);
		panel_1.add(u_mail);
		u_mail.setColumns(10);
		
		Choice u_sex = new Choice();
		u_sex.setBounds(281, 314, 116, 21);
		u_sex.add("Male");
		u_sex.add("Female");
		u_sex.add("Other");
		panel_1.add(u_sex);
		
		JLabel lblNewLabel_1 = new JLabel("Gender");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(100, 320, 57, 15);
		panel_1.add(lblNewLabel_1);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(72, 374, 97, 23);
		panel_1.add(submit);
		submit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submit) {
					String uid = u_id.getText();
					String upw = u_pwck.getText();
					String uname = u_name.getText();
					String uphone = u_phone.getText();
					String umail = u_mail.getText();
					String usex = u_sex.getSelectedItem();
					
					try {
				        Connection conn = getConnection();
						String sql="SELECT COUNT(*) FROM members WHERE id=?";
				        PreparedStatement pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, uid);
				        ResultSet rs = pstmt.executeQuery();
				        rs.next();
				        int check =rs.getInt(1); //있으면 1, 없으면 0
				        rs.close();
				        
				        //중복확인 및 입력확인
				        if(check == 1) {
							JOptionPane.showMessageDialog(null,  "Duplicate accounts exist.");
				        }
				        else if(u_id.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter your ID");
						}
						else if(u_pw.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter your Password");
						}
						else if(!u_pw.getText().equals(u_pwck.getText())) {
							JOptionPane.showMessageDialog(null,  "Check Password");
						}
						else if(u_name.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter your Name");
						}
						else if(u_phone.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter your Phone");
						}
						else if(u_mail.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter your Mail");
						}
						else if(u_pw.getText().equals(u_pwck.getText())) {
							insert(uid, upw, uname, uphone, umail, usex);
							setVisible(false);
						}
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
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(289, 374, 97, 23);
		panel_1.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==cancel) {
					setVisible(false);
				}
			}
		});
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setTitle("Register");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
