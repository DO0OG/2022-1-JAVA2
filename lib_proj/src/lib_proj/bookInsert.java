package lib_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class bookInsert extends JFrame {

	private JPanel contentPane;
	private JTextField btitle;
	private JTextField bauthor;
	
    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }
    
    public static void insert(String title, String author) throws ClassNotFoundException, SQLException {
        //입력하는 메소드
        Connection conn = getConnection();
        String sql = "insert into books(title, author) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, title);
        pstmt.setString(2, author);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
            System.out.println("처리 완료");
        }
        
        if(pstmt != null) 
			pstmt.close();
        if(conn != null) 
			conn.close();
    }
	
	public bookInsert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(185, 47, 57, 15);
		panel.add(lblNewLabel_1);
		
		btitle = new JTextField();
		btitle.setHorizontalAlignment(SwingConstants.CENTER);
		btitle.setBounds(155, 72, 116, 21);
		panel.add(btitle);
		btitle.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(185, 103, 57, 15);
		panel.add(lblNewLabel_2);
		
		bauthor = new JTextField();
		bauthor.setHorizontalAlignment(SwingConstants.CENTER);
		bauthor.setBounds(155, 128, 116, 21);
		panel.add(bauthor);
		bauthor.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(206, 194, 97, 23);
		panel.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submit) {
					String title = btitle.getText();
					String author = bauthor.getText();
					
					try {
				        Connection conn = getConnection();
						String sql="SELECT COUNT(*) FROM members WHERE id=?";
				        PreparedStatement pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, title);
				        ResultSet rs = pstmt.executeQuery();
				        rs.next();
				        int check =rs.getInt(1); //있으면 1, 없으면 0
				        rs.close();
				        
				        //중복확인 및 입력확인
				        if(check == 1) {
							JOptionPane.showMessageDialog(null,  "Duplicate Book exist.");
				        }
				        else if(btitle.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter Book Title");
						}
						else if(bauthor.getText().equals("")) {
							JOptionPane.showMessageDialog(null,  "Enter Book Author");
						}
						else if(!btitle.getText().equals("") & !bauthor.getText().equals("")) {
							insert(title, author);
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
		cancel.setBounds(315, 194, 97, 23);
		panel.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==cancel) {
					setVisible(false);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("ADD BOOK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookInsert frame = new bookInsert();
					frame.setTitle("Add Book");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
