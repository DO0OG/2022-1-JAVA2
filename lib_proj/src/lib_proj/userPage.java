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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class userPage extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTable tb;
    static DefaultTableModel model;
    public static String idck;
    String check[][];

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }
    
    public static void search(String title) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM books where title like " + "'%" + title + "%'";
        ResultSet rs = null;
        Statement stmt = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        String input[] = new String[3];
        
        
        while(rs.next()) {
            input[0] = rs.getString("title");
            input[1] = rs.getString("author");
            if(rs.getString("borrow") == null) {
            	input[2] = "대여 가능";
            }
            else if(rs.getString("borrow") != null) {
            	input[2] = "대여 불가능";
            }
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
    }
    
    public static void refresh() throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM books";
        ResultSet rs = null;
        Statement stmt = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        String input[] = new String[3];
        
        while(rs.next()) {
            input[0] = rs.getString("title");
            input[1] = rs.getString("author");
            if(rs.getString("borrow") == null) {
            	input[2] = "대여 가능";
            }
            else if(rs.getString("borrow") != null) {
            	input[2] = "대여 불가능";
            }
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
    }

    public static void borrow(Object title, Object author, Object borrow) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        
        String sql = "update books set author = ?, borrow = ? where title = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(3, title);
        pstmt.setObject(1, author);
        pstmt.setObject(2, borrow);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
			model.setNumRows(0);
        	refresh();
			JOptionPane.showMessageDialog(null, "대여됨");
        } else {
			JOptionPane.showMessageDialog(null, "Error");
		}
    }

    public static void rebook(Object title, Object author, Object borrow) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        
        String sql = "update books set author = ?, borrow = ? where title = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(3, title);
        pstmt.setObject(1, author);
        pstmt.setObject(2, null);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
			model.setNumRows(0);
        	refresh();
			JOptionPane.showMessageDialog(null, "반납됨");
        } else {
			JOptionPane.showMessageDialog(null, "Error");
		}
    }

	public userPage(String idck) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM books";
        ResultSet rs = null;
        Statement stmt = null;
        int count = 0;
        Object items[][] = null;
        String input[] = new String[3];
        check = new String[500][3];
		String header[] = {"Title", "Author", "Rental Status"};
		model = new DefaultTableModel(items, header);
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        while(rs.next()) {
        	check[count][0] = rs.getString("title");
            input[0] = rs.getString("title");
        	check[count][1] = rs.getString("author");
            input[1] = rs.getString("author");
        	check[count][2] = rs.getString("borrow");
            if(rs.getString("borrow") == null) {
            	input[2] = "대여 가능";
            }
            else if(rs.getString("borrow") != null) {
            	input[2] = "대여 불가능";
            }
            count++;
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Main System");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		panel_1.add(lblNewLabel_1);
		
		title = new JTextField();
		panel_1.add(title);
		title.setColumns(10);
		
		JButton search = new JButton("\uAC80\uC0C9");
		panel_1.add(search);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==search) {
					try {
						String st = title.getText();
						model.setNumRows(0);
						search(st);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton borrow = new JButton("\uB300\uC5EC");
		panel_1.add(borrow);
		borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==borrow) {
					try {
						if(tb.getValueAt(tb.getSelectedRow(), 2).equals("대여 불가능") == false & tb.getValueAt(tb.getSelectedRow(), 2).equals("대여 가능") == true) {
							borrow(tb.getValueAt(tb.getSelectedRow(), 0), tb.getValueAt(tb.getSelectedRow(), 1), idck);
						}
						else if(tb.getValueAt(tb.getSelectedRow(), 2).equals("대여 불가능") == true & tb.getValueAt(tb.getSelectedRow(), 2).equals("대여 가능") == false) {
							JOptionPane.showMessageDialog(null, "이미 대여된 도서입니다.");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton returnbook = new JButton("\uBC18\uB0A9");
		panel_1.add(returnbook);
		returnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==returnbook) {
					try {
						if(check[tb.getSelectedRow()][2] != null) {
							if(check[tb.getSelectedRow()][2].equals(idck)) {
								rebook(tb.getValueAt(tb.getSelectedRow(), 0), tb.getValueAt(tb.getSelectedRow(), 1), idck);
							}
							else if(!check[tb.getSelectedRow()][2].equals(idck) || check[tb.getSelectedRow()][2] == null) {
								JOptionPane.showMessageDialog(null, "사용자가 대여한 도서가 아닙니다.");
							}
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton mypage = new JButton("\uB9C8\uC774\uD398\uC774\uC9C0");
		panel_1.add(mypage);
		mypage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==mypage) {
					myPage frame;
					try {
						frame = new myPage(idck);
						frame.setTitle("My Page");
						frame.setVisible(true);
				        setVisible(false);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		panel_1.add(logout);
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tb = new JTable(model);
		scrollPane.setViewportView(tb);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userPage frame = new userPage(idck);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
