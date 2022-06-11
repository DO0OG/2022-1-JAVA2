package lib_proj;

import java.sql.*;
import java.util.HashMap;
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

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class bookManage extends JFrame {

	private JPanel contentPane;
    private JScrollPane scrollPane;
	private JTable tb;
    static DefaultTableModel model;

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }
    
    public static void delete(Object id) throws ClassNotFoundException, SQLException {
        //삭제기능
    	HashMap<Integer, String> hm = new HashMap<Integer, String>(); // HashMap 선언
        Connection conn = getConnection();
        
        String sql = "delete from books where title =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, id);
        int res = pstmt.executeUpdate();
        
        if(res > 0){
			JOptionPane.showMessageDialog(null,  "Deleted");
        } else {
			JOptionPane.showMessageDialog(null,  "Delete Failed");
		}
    }

    public static void update(Object title, Object author, Object borrow) throws ClassNotFoundException, SQLException {
        //수정 기능
        Connection conn = getConnection();
        
        String sql = "update books set author = ?, borrow = ?  where title = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(3, title);
        pstmt.setObject(1, author);
        pstmt.setObject(2, borrow);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
			JOptionPane.showMessageDialog(null, "Updated");
        } else {
            System.out.println("Update Failed");
		}
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
            input[2] = rs.getString("borrow");
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
    }

	public bookManage() throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM books";
        ResultSet rs = null;
        Statement stmt = null;
        Object items[][] = null;
        String input[] = new String[3];
		String header[] = {"Title", "Author", "Person"};
		model = new DefaultTableModel(items, header);
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        while(rs.next()) {
            input[0] = rs.getString("title");
            input[1] = rs.getString("author");
            input[2] = rs.getString("borrow");
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("BOOK MANAGEMENT");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tb = new JTable(model);
		scrollPane.setViewportView(tb);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton addbook = new JButton("\uB3C4\uC11C \uCD94\uAC00");
		panel.add(addbook);
		addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==addbook) {
					bookInsert frame = new bookInsert();
					frame.setTitle("Add Book");
					frame.setVisible(true);
				}
			}
		});
		
		JButton updatebook = new JButton("\uC815\uBCF4 \uC218\uC815");
		panel.add(updatebook);
		updatebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==updatebook) {
					try {
						update(tb.getValueAt(tb.getSelectedRow(), 0), tb.getValueAt(tb.getSelectedRow(), 1), tb.getValueAt(tb.getSelectedRow(), 2));
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton delbook = new JButton("\uB3C4\uC11C \uC0AD\uC81C");
		panel.add(delbook);
		delbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==delbook) {
					try {
						delete(tb.getValueAt(tb.getSelectedRow(), 0));
						model.removeRow(tb.getSelectedRow());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton ref = new JButton("\uBAA9\uB85D \uC0C8\uB85C\uACE0\uCE68");
		panel.add(ref);
		ref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==ref) {
					try {
						model.setNumRows(0);
						refresh();
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
		
		JButton back = new JButton("\uB3CC\uC544\uAC00\uAE30");
		panel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==back) {
					managePage frame = new managePage();
					frame.setTitle("Manage Page");
					frame.setVisible(true);
			        setVisible(false);
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookManage frame = new bookManage();
					frame.setTitle("Book Manage");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
