import java.sql.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Scanner;
import java.util.HashMap;
 
public class lib_main implements ActionListener{

	HashMap<Integer, String> hm = new HashMap<Integer, String>(); // HashMap 선언
    public static Scanner in = new Scanner(System.in);

    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "aa9509481";
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("Connect\n");
            
        return conn;
    }
    //SELECT 처리용
    public static void selectAll() throws ClassNotFoundException, SQLException {    
        Connection conn = getConnection();
        String sql = "SELECT * FROM books";
        ResultSet rs = null;
        Statement stmt = null;
    	int count = 1;
        
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        while(rs.next()) {
    		HashMap<Integer, String> hm = new HashMap<Integer, String>(); // HashMap 선언
            String id = rs.getString(1);
            String passwd = rs.getString(2);
            String name = rs.getString(3);
    		hm.put(count, id);
            
            System.out.println(hm.keySet() + ", " + id  + ", "+ passwd + ", "+ name +", ");
            count++;
        }
        
        if(rs != null) 
			rs.close();
        if(stmt != null) 
			stmt.close();
        if(conn != null) 
			conn.close();
    }
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        String id = null;
        String pwd = null;
        String name = null;
        selectAll();
        System.out.println("\nDONE\n");
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}