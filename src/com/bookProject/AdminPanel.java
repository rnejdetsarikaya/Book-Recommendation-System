package com.bookProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DaoImpl.DaoImpl;
import model.MouseHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static DaoImpl dao = new DaoImpl();
	private JTable UserTable;
	// static AdminPanel adminPanel;
	int pageIndexBook = 0;
	int pageIndexUser = 0;
	private JTextField textFieldBook;
	private JTextField textFieldUser;
	static SingleObject obj = SingleObject.getSingleObject();
	private JTextField txtSearchBookTitle;
	private JTextField txtSearchUser;
	private JTextField txtSearchBookAuthor;

	public AdminPanel() {
		int pageCountBook = dao.getCount("Book", 40) + 1;
		int pageCountUser = dao.getCount("User", 50) + 1;
		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Books", null, panel_1, null);
		panel_1.setLayout(null);

		JButton button = new JButton("Exit");
		button.setBounds(731, 411, 100, 36);
		panel_1.add(button);

		JScrollPane ListPane = new JScrollPane();
		ListPane.setBounds(0, 0, 831, 242);
		panel_1.add(ListPane);

		table = new JTable();
		ListPane.setViewportView(table);
		ListPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ISBN", "Title", "Author",
				"Year Of Publication", "Publish", "ImageURLS", "ImageURLM", "ImageURLL", "Index" }));
		table.getColumnModel().getColumn(3).setPreferredWidth(132);
		table.getColumnModel().getColumn(4).setPreferredWidth(109);
		table.setRowHeight(25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj = SingleObject.getSingleObject();
				setVisible(false);
				obj.disposeAdminPanel();
				obj.getLogin().setLocationRelativeTo(null);
				obj.getLogin().setVisible(true);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Users", null, panel, null);
		panel.setLayout(null);

		JScrollPane UserPane = new JScrollPane();
		UserPane.setBounds(12, 13, 807, 317);
		panel.add(UserPane);

		UserTable = new JTable();
		UserPane.setViewportView(UserTable);
		UserTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "UserID", "UserName", "Location", "Age", "Role", "Rating" }));

		try {
			dao.listUser(UserTable, pageIndexUser);
			JButton button_1 = new JButton("Back");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageIndexUser > 0) {
						pageIndexUser--;

						try {
							dao.listUser(UserTable, pageIndexUser);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textFieldUser.setText((pageIndexUser + 1) + "/" + pageCountUser);
					}
				}
			});
			button_1.setBounds(243, 375, 97, 25);
			panel.add(button_1);

			textFieldUser = new JTextField();
			textFieldUser.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						pageIndexUser = Integer.parseInt(textFieldUser.getText()) - 1;
						if (pageCountUser > pageIndexUser && pageIndexUser >= 0) {
							try {
								dao.listUser(UserTable, pageIndexUser);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Lï¿½tfen geï¿½erli bir sayfa numarasï¿½ giriniz");
								textFieldUser.setText((pageIndexUser + 1) + "/" + pageCountUser);
							}
						}
					}
				}
			});
			textFieldUser.setText("1");
			textFieldUser.setColumns(10);
			textFieldUser.setBounds(347, 376, 116, 22);
			panel.add(textFieldUser);

			JButton button_2 = new JButton("Next");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageCountUser > pageIndexUser + 1) {
						pageIndexUser++;
						try {
							dao.listUser(UserTable, pageIndexUser);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textFieldUser.setText((pageIndexUser + 1) + "/" + pageCountUser);
					}
				}
			});
			button_2.setBounds(475, 375, 97, 25);
			panel.add(button_2);

			txtSearchUser = new JTextField();
			txtSearchUser.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!txtSearchUser.equals("") && txtSearchUser.getText().length() > 2) {
							try {
								dao.searchUser(UserTable, txtSearchUser.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Arama yapmak için en az 3 karakter giriniz");
						}
					}
				}
			});
			txtSearchUser.setText("SearchUserName");
			txtSearchUser.setBounds(347, 353, 116, 22);
			panel.add(txtSearchUser);
			txtSearchUser.setColumns(10);
		} catch (Exception e) {
			// TODO: handle exception
		}

		UserTable.getColumnModel().getColumn(3).setPreferredWidth(132);
		UserTable.getColumnModel().getColumn(4).setPreferredWidth(109);

		contentPane.setLayout(gl_contentPane);
		try {

			dao.listBook(table, pageIndexBook);
			System.out.println("yenilendi");
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int chose = table.getSelectedRow();
					System.out.println("admin panel chose: " + chose);

					dao.updateBook(model.getValueAt(chose, 0).toString(), model.getValueAt(chose, 1).toString(),
							model.getValueAt(chose, 2).toString(),
							Integer.parseInt(model.getValueAt(chose, 3).toString()),
							model.getValueAt(chose, 4).toString(), model.getValueAt(chose, 5).toString(),
							model.getValueAt(chose, 6).toString(), model.getValueAt(chose, 7).toString(),
							Integer.parseInt(model.getValueAt(chose, 8).toString()), chose);
					System.out.println("Update is success");
				}
			});
			btnUpdate.setBounds(66, 292, 97, 25);
			panel_1.add(btnUpdate);

			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int chose = table.getSelectedRow();
					System.out.println("delete:" + chose);
					System.out.println(model.getValueAt(chose, 8));
					dao.deleteBook((int) model.getValueAt(chose, 8));
					System.out.println("Delete is success");
				}
			});
			btnDelete.setBounds(266, 333, 97, 25);
			panel_1.add(btnDelete);

			JButton btnRefresh = new JButton("Refresh");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dao.listBook(table, pageIndexBook);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnRefresh.setBounds(266, 292, 97, 25);
			panel_1.add(btnRefresh);

			JButton btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageCountBook > pageIndexBook + 1) {
						pageIndexBook++;
						try {
							dao.listBook(table, pageIndexBook);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textFieldBook.setText((pageIndexBook + 1) + "/" + pageCountBook);
					}
				}
			});
			btnNext.setBounds(622, 333, 97, 25);
			panel_1.add(btnNext);

			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageIndexBook > 0) {
						pageIndexBook--;
						try {
							dao.listBook(table, pageIndexBook);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textFieldBook.setText((pageIndexBook + 1) + "/" + pageCountBook);
					}
				}
			});
			btnBack.setBounds(390, 333, 97, 25);
			panel_1.add(btnBack);

			textFieldBook = new JTextField();
			textFieldBook.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						pageIndexBook = Integer.parseInt(textFieldBook.getText()) - 1;
						if (pageCountBook > pageIndexBook && pageIndexBook >= 0) {
							try {
								dao.listBook(table, pageIndexBook);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Lï¿½tfen geï¿½erli bir sayfa numarasï¿½ giriniz");
								textFieldBook.setText((pageIndexBook + 1) + "/" + pageCountBook);
							}
						}
					}
				}
			});
			textFieldBook.setText("1");
			textFieldBook.setBounds(494, 334, 116, 22);
			panel_1.add(textFieldBook);
			textFieldBook.setColumns(10);

			txtSearchBookTitle = new JTextField();
			txtSearchBookTitle.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!txtSearchBookTitle.equals("") && txtSearchBookTitle.getText().length() > 2) {
							try {
								dao.searchBookTitle(table, txtSearchBookTitle.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Arama yapmak için en az 3 karakter giriniz");
						}

					}
				}
			});
			txtSearchBookTitle.setText("SearchTitle");
			txtSearchBookTitle.setBounds(494, 314, 116, 22);
			panel_1.add(txtSearchBookTitle);
			txtSearchBookTitle.setColumns(10);

			txtSearchBookAuthor = new JTextField();
			txtSearchBookAuthor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!txtSearchBookAuthor.equals("") && txtSearchBookAuthor.getText().length() > 2) {
							try {
								dao.searchBookAuthor(table, txtSearchBookAuthor.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Arama yapmak için en az 3 karakter giriniz");
						}

					}
				}
			});
			txtSearchBookAuthor.setText("SearchAuthor");
			txtSearchBookAuthor.setBounds(494, 293, 116, 22);
			panel_1.add(txtSearchBookAuthor);
			txtSearchBookAuthor.setColumns(10);
			MouseHandler.mouseListener(txtSearchBookAuthor);
			MouseHandler.mouseListener(txtSearchBookTitle);
			MouseHandler.mouseListener(txtSearchUser);

			JButton userBtnUpdate = new JButton("Update");
			userBtnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
					int chose = UserTable.getSelectedRow();
					dao.updateUser(Integer.parseInt(model.getValueAt(chose, 0).toString()), // userID
							model.getValueAt(chose, 1).toString(), // UserName
							model.getValueAt(chose, 2).toString(), // Location
							model.getValueAt(chose, 3).toString(), // Age
							Integer.parseInt(model.getValueAt(chose, 4).toString()), // Role
							Integer.parseInt(model.getValueAt(chose, 5).toString())); // Flag
					System.out.println("Update is success");
				}
			});
			userBtnUpdate.setBounds(12, 343, 97, 25);
			panel.add(userBtnUpdate);

			JButton userBtnRefresh = new JButton("Refresh");
			userBtnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dao.listUser(UserTable, pageIndexUser);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			userBtnRefresh.setBounds(12, 375, 97, 25);
			panel.add(userBtnRefresh);

			JButton userBtnDelete = new JButton("Delete");
			userBtnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
					int chose = UserTable.getSelectedRow();
					System.out.println("delete:" + chose);
					dao.deleteUser(Integer.parseInt(model.getValueAt(chose, 0).toString()));
					System.out.println("Delete is success");
				}
			});
			userBtnDelete.setBounds(734, 375, 97, 25);
			panel.add(userBtnDelete);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
