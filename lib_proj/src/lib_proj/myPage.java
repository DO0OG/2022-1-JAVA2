package lib_proj;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class myPage extends JFrame {

	private JPanel contentPane;
	private JTextField uid;
    private JTextField upw;
    private JTextField uname;
    private JTextField uphone;
    private JTextField umail;
    public static String idck;

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }

    public static void update(String id, String passwd, String name, String phone, String mail) throws ClassNotFoundException, SQLException {
        //수정 기능
        Connection conn = getConnection();
        
        String sql = "update members set passwd = ?, name = ?, phone = ?, mail = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(5, id);
        pstmt.setString(1, passwd);
        pstmt.setString(2, name);
        pstmt.setString(3, phone);
        pstmt.setString(4, mail);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
			JOptionPane.showMessageDialog(null, "Updated");
        } else {
			JOptionPane.showMessageDialog(null, "Update Failed");
		}
    }

	public myPage(String idck) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM members where id = " + "'" + idck + "'";
        ResultSet rs = null;
        Statement stmt = null;
    	String pw = null;
    	String name = null;
    	String phone = null;
    	String mail = null;
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        if(rs.next()) {
        	pw = rs.getString("passwd");
        	name = rs.getString("name");
        	phone = rs.getString("phone");
        	mail = rs.getString("mail");
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Page");
		lblNewLabel.setBounds(12, 10, 560, 24);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 44, 560, 357);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(250, 25, 57, 15);
		panel.add(lblNewLabel_1);
		
		uid = new JTextField(idck);
		uid.setEditable(false);
		uid.setHorizontalAlignment(SwingConstants.CENTER);
		uid.setBounds(220, 55, 116, 21);
		panel.add(uid);
		uid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(220, 86, 116, 15);
		panel.add(lblNewLabel_2);
		
		upw = new JTextField(pw);
		upw.setHorizontalAlignment(SwingConstants.CENTER);
		upw.setBounds(220, 111, 116, 21);
		panel.add(upw);
		upw.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(250, 142, 57, 15);
		panel.add(lblNewLabel_3);
		
		uname = new JTextField(name);
		uname.setHorizontalAlignment(SwingConstants.CENTER);
		uname.setBounds(220, 167, 116, 21);
		panel.add(uname);
		uname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(250, 198, 57, 15);
		panel.add(lblNewLabel_4);
		
		uphone = new JTextField(phone);
		uphone.setHorizontalAlignment(SwingConstants.CENTER);
		uphone.setBounds(166, 223, 224, 21);
		panel.add(uphone);
		uphone.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("E-Mail");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(250, 269, 57, 15);
		panel.add(lblNewLabel_5);
		
		umail = new JTextField(mail);
		umail.setHorizontalAlignment(SwingConstants.CENTER);
		umail.setBounds(166, 294, 224, 21);
		panel.add(umail);
		umail.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 411, 560, 40);
		contentPane.add(panel_1);
		
		JButton submit = new JButton("Submit");
		panel_1.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submit) {
					try {
						update(idck, upw.getText(), uname.getText(), uphone.getText(), umail.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		panel_1.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==cancel) {
					try {
						userPage frame = new userPage(idck);
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
					myPage frame = new myPage(idck);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
