package presentation;

import business.ConnectionFactory;
import business.DeliveryService;
import business.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInPres extends JFrame {

	private JPanel contentPane;
	private JTextField utilizator;
	private JPasswordField passwordField;
	private int idUser;
	//static int idBill;

	public LogInPres(DeliveryService ds) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		utilizator = new JTextField();
		utilizator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		utilizator.setBounds(183, 107, 252, 36);
		contentPane.add(utilizator);
		utilizator.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Utilizator");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(49, 107, 124, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParola.setBounds(49, 162, 124, 36);
		contentPane.add(lblParola);
		
		JButton signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = ConnectionFactory.getConnection();
				String sql="Select * from user where  nume='"+utilizator.getText()+"' and parola='"+passwordField.getText().toString()+"'";
				PreparedStatement s=null;
				ResultSet rs=null;
				try {
					s=con.prepareStatement(sql);
					rs=s.executeQuery();
					if(rs.next()) {
						if (rs.getInt("tip") == 0) {
							idUser = rs.getInt("tip");
							ClientPres i = new ClientPres(idUser, ds);
							i.show();
							dispose();
						} else if (rs.getInt("tip") == 1) {
							AdministratorPres i = new AdministratorPres(ds);
							i.show();
							dispose();
						} }else
							JOptionPane.showMessageDialog(null, "Date incorecte");
					}catch(Exception e1) {System.out.print(e1);}
				finally {
					ConnectionFactory.close(rs);
					ConnectionFactory.close(s);
					ConnectionFactory.close(con);
				}
			}
		});
		signIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		signIn.setBounds(156, 218, 124, 36);
		contentPane.add(signIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nume=utilizator.getText();
				String parola=passwordField.getText().toString();
				int i=insert(new User(nume,parola,0));
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegister.setBounds(156, 264, 124, 36);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel_1 = new JLabel("Bine ati venit!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(131, 36, 191, 42);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(183, 158, 252, 36);
		contentPane.add(passwordField);


	}

	public int insert(User user){
		Connection dbConnection = ConnectionFactory.getConnection();
		String insert = "INSERT INTO user (nume,parola,tip)" + " VALUES (?,?,?)";
		PreparedStatement s=null;
		int insertID=-1;
		try{
			s=dbConnection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1,user.getNume());
			s.setString(2, user.getParola());
			s.setInt(3,user.getTip());
			s.executeUpdate();

			ResultSet rs=s.getGeneratedKeys();
			if (rs.next()) {
				insertID=rs.getInt(1);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			ConnectionFactory.close(s);
			ConnectionFactory.close(dbConnection);
		}
		return insertID;}


}
