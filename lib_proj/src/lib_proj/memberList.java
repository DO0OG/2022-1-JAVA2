package lib_proj;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class memberList extends JFrame {

	private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTable tb;
    private JPanel panel;
    private JPanel panel_1;
    private JButton update;
    private JButton delete;
    private JButton back;
    DefaultTableModel model;

	HashMap<Integer, String> hm = new HashMap<Integer, String>(); // HashMap 선언
    public static Scanner in = new Scanner(System.in);

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
            
        return conn;
    }

    public static void update(Object id, Object passwd, Object name, Object phone, Object mail, Object sex) throws ClassNotFoundException, SQLException {
        //수정 기능
        Connection conn = getConnection();
        
        String sql = "update members set passwd = ?, name = ?, phone = ?, mail = ?, sex = ?  where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(6, id);
        pstmt.setObject(1, passwd);
        pstmt.setObject(2, name);
        pstmt.setObject(3, phone);
        pstmt.setObject(4, mail);
        pstmt.setObject(5, sex);
        
        int res = pstmt.executeUpdate();
        if(res > 0){
			JOptionPane.showMessageDialog(null,  "Updated");
        } else {
            System.out.println("Update Failed");
		}
    }
    
    public static void delete(Object id) throws ClassNotFoundException, SQLException {
        //삭제기능
    	HashMap<Integer, String> hm = new HashMap<Integer, String>(); // HashMap 선언
        Connection conn = getConnection();
        
        String sql = "delete from members where id =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, id);
        int res = pstmt.executeUpdate();
        
        if(res > 0){
			JOptionPane.showMessageDialog(null,  "Deleted");
        } else {
			JOptionPane.showMessageDialog(null,  "Delete Failed");
		}
    }
    
        
	public memberList() throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM members";
        ResultSet rs = null;
        Statement stmt = null;
        String id = null;
        String passwd = null;
        String name = null;
        String phone = null;
        String mail = null;
        String sex = null;
        Object items[][] = null;
        String input[] = new String[6];
		String header[] = {"ID", "Password", "Name", "Phone", "mail", "Gender"};
		model = new DefaultTableModel(items, header);
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        while(rs.next()) {
            input[0] = rs.getString("id");
            input[1] = rs.getString("passwd");
            input[2] = rs.getString("name");
            input[3] = rs.getString("phone");
            input[4] = rs.getString("mail");
            input[5] = rs.getString("sex");
        	model.addRow(input);
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("USER MANAGEMENT");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tb = new JTable(model);
		scrollPane.setViewportView(tb);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		
		update = new JButton("\uC218\uC815");
		panel_1.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==update) {
					try {
						update(tb.getValueAt(tb.getSelectedRow(), 0), tb.getValueAt(tb.getSelectedRow(), 1), tb.getValueAt(tb.getSelectedRow(), 2), tb.getValueAt(tb.getSelectedRow(), 3), tb.getValueAt(tb.getSelectedRow(), 4), tb.getValueAt(tb.getSelectedRow(), 5));
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		delete = new JButton("\uC0AD\uC81C");
		panel_1.add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==delete) {
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
		
		back = new JButton("\uB3CC\uC544\uAC00\uAE30");
		panel_1.add(back);
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
					memberList frame = new memberList();
					frame.setTitle("User Management");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
