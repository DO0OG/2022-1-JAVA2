package lib_proj;

import lib_proj.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class mainPage extends JFrame {
	private JPanel contentPane;
    
	public mainPage() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton Manager = new JButton("\uAD00\uB9AC\uC790 \uC811\uC18D\uD558\uAE30");
		contentPane.add(Manager, BorderLayout.WEST);
		Manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==Manager) {
					manageLogin frame = new manageLogin();
					frame.setTitle("Manager Login");
					frame.setVisible(true);
			        setVisible(false);
				}
			}
		});
		
		JButton login = new JButton("\uC0AC\uC6A9\uC790 \uC811\uC18D\uD558\uAE30");
		contentPane.add(login, BorderLayout.CENTER);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==login) {
					userLogin frame = new userLogin();
					frame.setTitle("User Login");
					frame.setVisible(true);
			        setVisible(false);
				}
			}
		});
		
		JButton Register = new JButton("\uC0AC\uC6A9\uC790 \uD68C\uC6D0\uAC00\uC785");
		contentPane.add(Register, BorderLayout.EAST);
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==Register) {
					try {
						Register frame = new Register();
						frame.setTitle("Register");
						frame.setVisible(true);
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
		
		JLabel lblNewLabel = new JLabel("Book Manager");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        String id = null;
        String pwd = null;
        String name = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPage frame = new mainPage();
					frame.setTitle("MAIN");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        System.out.println("DONE\n");
    }
}
