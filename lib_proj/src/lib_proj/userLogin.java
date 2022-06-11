package lib_proj;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class userLogin extends JFrame {

	private JPanel contentPane;
	private JTextField u_id;
	private JPasswordField u_pw;
	public String idck;

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }
    
    public static int login(String id, String passwd) throws ClassNotFoundException, SQLException {
        //검색기능
        Connection con = getConnection();
        String sql = "select * from members where id = ? and passwd = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, passwd);
        ResultSet res = pstmt.executeQuery();
        int tf = 0;
		
        if(res.next()) {
        	tf = 1;
        }
        else {
        	tf = 0;
        }
        return tf;
    }

	public userLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uB85C\uADF8\uC778");
		lblNewLabel.setBounds(5, 5, 424, 40);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 55, 417, 196);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(180, 20, 57, 15);
		panel.add(lblNewLabel_1);
		
		u_id = new JTextField();
		u_id.setHorizontalAlignment(SwingConstants.CENTER);
		u_id.setBounds(151, 45, 116, 21);
		panel.add(u_id);
		u_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(151, 90, 116, 15);
		panel.add(lblNewLabel_2);
		
		JButton login = new JButton("Login");
		login.setBounds(75, 163, 97, 23);
		panel.add(login);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==login) {
					String uid = u_id.getText();
					String upw = u_pw.getText();
					try {
						if(login(uid, upw) == 1) {
							idck = u_id.getText();
							userPage frame = new userPage(idck);
							frame.setTitle("User Page");
							frame.setVisible(true);
					        setVisible(false);
						}
						else if(login(uid, upw) == 0) {
							JOptionPane.showMessageDialog(null,  "Check your ID or Password.");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(245, 163, 97, 23);
		panel.add(cancel);
		
		u_pw = new JPasswordField();
		u_pw.setHorizontalAlignment(SwingConstants.CENTER);
		u_pw.setBounds(153, 122, 117, 21);
		panel.add(u_pw);
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
					userLogin frame = new userLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
