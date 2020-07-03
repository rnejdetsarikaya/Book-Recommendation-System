package com.bookProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import DaoImpl.DaoImpl;
import model.MouseHandler;

public class Register extends JFrame {

	private static final long serialVersionUID = -3726890727293967022L;
	//LoginFrame loginFrame;
	static SingleObject obj;
	private JPanel RegisterPanel;
	/**
	 * Launch the application.
	 */
	public Register() {
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		RegisterPanel = new JPanel();
		RegisterPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(RegisterPanel);
		RegisterPanel.setLayout(null);

		JTextArea Location = new JTextArea();
		Location.setToolTipText("Location!!");
		Location.setText("Location");
		Location.setBounds(137, 100, 178, 24);
		RegisterPanel.add(Location);

		JTextArea Age = new JTextArea();
		Age.setToolTipText("User Name");
		Age.setText("Age");
		Age.setBounds(137, 137, 178, 24);
		RegisterPanel.add(Age);

		JTextArea Password = new JTextArea();
		Password.setToolTipText("Password!!");
		Password.setText("Password");
		Password.setBounds(137, 63, 178, 24);
		RegisterPanel.add(Password);

		JTextArea UserName = new JTextArea();
		UserName.setToolTipText("User Name!!");
		UserName.setText("User Name");
		UserName.setBounds(137, 26, 178, 24);
		RegisterPanel.add(UserName);

			
		MouseHandler.mouseListener(UserName);  ///
		MouseHandler.mouseListener(Password);
		MouseHandler.mouseListener(Location);  //
		MouseHandler.mouseListener(Age);       ///
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = UserName.getText();
				String pass = Password.getText();
				String loc = Location.getText();
				int age = Integer.parseInt(Age.getText());// String girilmesi kontrol edilecek
				DaoImpl dao = new DaoImpl();
				try {
					dao.register(pass, userName, loc, age);
				} catch (Exception e1) {
					System.err.println(e1);
				}
				JOptionPane.showMessageDialog(null, "Sign Up Success!!");
				obj = SingleObject.getSingleObject();
				obj.getRegister().setVisible(false);
				obj.getLogin().setLocationRelativeTo(null);
				obj.getLogin().setVisible(true);
				UserName.setText("User Name");
				Password.setText("Password");
				Age.setText("Age");
				Location.setText("Location");				
			}
		});
		button.setBounds(178, 184, 97, 25);
		RegisterPanel.add(button);

		JButton back = new JButton("Go Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj = SingleObject.getSingleObject();
				obj.getRegister().setVisible(false);
				obj.getLogin().setLocationRelativeTo(null);
				obj.getLogin().setVisible(true);
			}
		});
		back.setBounds(178, 222, 97, 25);
		RegisterPanel.add(back);

	}
}
