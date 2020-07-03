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
import model.User;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 897432469301717078L;
	private JPanel LoginPanel;
	static SingleObject obj = SingleObject.getSingleObject();
	JButton btnLogin;
	// static private LoginFrame loginFrame;
//	private Register register;
//	private BookRating rating;
//	private AdminPanel adminPanel;
//	private ListBook listBook;

	static DaoImpl dao = new DaoImpl();
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		obj.getLogin().setLocationRelativeTo(null);
		obj.getLogin().setVisible(true);

//		dao.DropTable();
//		dao.CreateTable();
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		obj = SingleObject.getSingleObject();
		setTitle("Sign In");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 283);
		LoginPanel = new JPanel();
		LoginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPanel);
		LoginPanel.setLayout(null);

		JTextArea UserName = new JTextArea();
		UserName.setToolTipText("Insert User Name!!");
		UserName.setText("User Name");
		UserName.setBounds(56, 60, 178, 24);
		LoginPanel.add(UserName);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u;
				String userName = UserName.getText();
				String pass = pwdPassword.getText();
				try {
					obj = SingleObject.getSingleObject();
					if (dao.isLogin(userName, pass)) {
						System.out.println("Sign In Success");
						if (dao.isAdmin(userName)) {
							setVisible(false);
							obj.getAdminPanel().setLocationRelativeTo(null);
							u = User.getUser();
							obj.getAdminPanel().setTitle("Admin Page--Hoþ Geldin: " + u.getUserName());
							obj.getAdminPanel().setVisible(true);
							UserName.setText("Kullanýcý adý");
							pwdPassword.setText("Þifre");
						} else if (dao.isRated(userName)) {
							setVisible(false);
							obj = SingleObject.getSingleObject();
							obj.getLoading().setLocationRelativeTo(null);
							obj.getLoading().setVisible(true);
							UserName.setText("User Name");
							pwdPassword.setText("Password");
						} else {
							setVisible(false);
							obj.getRating().setLocationRelativeTo(null);

							u = User.getUser();
							obj.getRating().setTitle("BookRating--Hoþ Geldin: " + u.getUserName());
							obj.getRating().setVisible(true);
							obj.getRating().btnListele.getActionListeners()[0].actionPerformed(null);// listeleme
																										// butonuna
																										// týklama
																										// gönder
							dispose();
							UserName.setText("User Name");
							pwdPassword.setText("Password");
						}
					} else {
						JOptionPane.showMessageDialog(null, "User Name or Password Wrong!!");
						System.out.println("Signing in failed");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// loginFrame.dispose();
		});
		btnLogin.setBounds(89, 144, 97, 25);
		LoginPanel.add(btnLogin);

		JButton btnRegister = new JButton("Register");

		MouseHandler.mouseListener(UserName);

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj.getLogin().setVisible(false);
				obj.getRegister().setLocationRelativeTo(null);
				obj.getRegister().setVisible(true);
			}
		});
		btnRegister.setBounds(89, 182, 97, 25);
		LoginPanel.add(btnRegister);

		pwdPassword = new JPasswordField();
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					obj.getLogin().btnLogin.getActionListeners()[0].actionPerformed(null);
				}

			}
		});
		pwdPassword.setText("Password");
		pwdPassword.setBounds(56, 97, 178, 24);
		LoginPanel.add(pwdPassword);
		MouseHandler.mouseListener(pwdPassword);

	}
}
