package com.bookProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DaoImpl.DaoImpl;
import model.MouseHandler;
import model.User;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.MouseInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookRating extends JFrame {

	private JPanel contentPane;
	SingleObject obj1;
	private static JTable table;
	static DaoImpl dao = new DaoImpl();
	private JSpinner spinner;
	private JTextField textField;
	int pageIndexBook = 0;
	public JButton btnListele;
	int count = dao.getCount("Book", 40) + 1;
	private JButton btnReadBook;
	private JTextField txtSearchTitle;
	int flag = 0;
	private JTextField txtSearchAuthor;
	SingleObject obj;

	public BookRating() {

		setTitle("BookRating");

		obj = SingleObject.getSingleObject();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		JSpinner spinner = new JSpinner();
//		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj1 = SingleObject.getSingleObject();
				obj1.getRating().setVisible(false);
				obj1.getLogin().setLocationRelativeTo(null);
				obj1.getLogin().setVisible(true);
			}
		});

		JScrollPane ListPane = new JScrollPane();
		ListPane.setBounds(82, 5, 622, 321);
		table = new JTable();
		ListPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ISBN", "Title", "Author", "Year Of Publication", "Publish", "Index", "YourPoint" }));
		table.getColumnModel().getColumn(3).setPreferredWidth(132);
		table.getColumnModel().getColumn(4).setPreferredWidth(109);
		table.setRowHeight(25);
		try {

			spinner = new JSpinner();
			spinner.setBounds(5, 5, 76, 25);
			spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int c1 = 0, c2 = 0;
					// int a = (int) MouseInfo.getPointerInfo().getLocation().getY();

					int a = (int) contentPane.getMousePosition().getY() - 12;
					while (a % 25 != 0) {
						a++;
						c1++;
					} /// Spinner konumu için mouse koordinatý belirlendi.
					int b = (int) contentPane.getMousePosition().getY() - 12;
					while (b % 25 != 0) {
						b--; // Ortalama deðere aþaðýdan ve yukarýdan yaklaþmanýn en kýsa olanýna bakýlýp
						c2++;
					}
					if (c1 < c2) {
						spinner.setBounds(0, a, 76, 22); // en yakýn olanýna göre spinner konumu
															// belirlendi.Böylelikle mouse nereye týklanýrsa týklansýn
					} else if (c2 < c1) {
						spinner.setBounds(0, b, 76, 22); // Table a göre orta konum ele alýndý ve spinner
															/// yerleþtirildi
					}

				}
			});

			JButton button = new JButton("Back");
			button.setBounds(252, 373, 59, 25);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageIndexBook > 0) {
						pageIndexBook--;

						try {
							dao.listBook(table, pageIndexBook);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText((pageIndexBook + 1) + "/" + count);
					}
				}
			});

			textField = new JTextField();
			textField.setBounds(329, 374, 59, 22);
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						pageIndexBook = Integer.parseInt(textField.getText()) - 1;
						if (count > pageIndexBook && pageIndexBook >= 0) {
							try {
								dao.listBook(table, pageIndexBook);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Lï¿½tfen geï¿½erli bir sayfa numarasï¿½ giriniz");
								textField.setText((pageIndexBook + 1) + "/" + count);
							}
						}
					}
				}
			});
			textField.setText("1");
			textField.setColumns(10);

			JButton button_1 = new JButton("Next");
			button_1.setBounds(400, 373, 57, 25);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageIndexBook + 1 < count) {
						pageIndexBook++;
						try {
							dao.listBook(table, pageIndexBook);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText((pageIndexBook + 1) + "/" + count);
					}

				}
			});

			btnListele = new JButton("refresh");
			btnListele.setBounds(5, 373, 83, 25);
			btnListele.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dao.listBook(table, pageIndexBook);
						textField.setText((pageIndexBook + 1) + "/" + count);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			JButton btnOyla = new JButton("oyla");
			btnOyla.setBounds(5, 339, 83, 25);
			btnOyla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int a = table.getSelectedRow();
					System.out.println(User.getUser().getUserName() + " " + a + " "
							+ table.getModel().getValueAt(a, 0).toString() + " " + User.getUser().getUserId());
					dao.setRating(User.getUser().getUserId(), table.getModel().getValueAt(a, 0).toString(),
							(int) spinner.getValue());
				}
			});

			btnReadBook = new JButton("Read Book");
			btnReadBook.setBounds(604, 339, 93, 25);
			btnReadBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int ratingCount = dao.getRatingCount(User.getUser().getUserId());
						if (ratingCount >= 10) {
							obj1 = SingleObject.getSingleObject();
							obj1.getRating().setVisible(false);
							obj1.getLoading().setLocationRelativeTo(null);
							obj1.getLoading().setVisible(true);
							dao.ratingComplete(User.getUser().getUserId());
							System.out.println("Gerekli kosulda kitap oyladýn " + User.getUser().getUserName() + " :)");
						} else {
							JOptionPane.showMessageDialog(null,
									ratingCount + " tane Kitap oyladýnýz Lutfen en az 10 farklý kitabý oylayýnýz!!");
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});

			txtSearchTitle = new JTextField();
			txtSearchTitle.setBounds(122, 374, 116, 22);
			txtSearchTitle.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!txtSearchTitle.equals("") && txtSearchTitle.getText().length() > 2) {
							try {
								dao.searchBookTitle(table, txtSearchTitle.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Arama yapmak için en az 3 karakter giriniz");
						}

					}
				}
			});
			txtSearchTitle.setText("SearchTitle");
			txtSearchTitle.setColumns(10);

			txtSearchAuthor = new JTextField();
			txtSearchAuthor.setBounds(122, 339, 116, 22);
			txtSearchAuthor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!txtSearchAuthor.equals("") && txtSearchAuthor.getText().length() > 2) {
							try {
								dao.searchBookAuthor(table, txtSearchAuthor.getText());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Arama yapmak için en az 3 karakter giriniz");
						}

					}
				}
			});
			txtSearchAuthor.setText("SearchAuthor");
			txtSearchAuthor.setColumns(10);
			MouseHandler.mouseListener(txtSearchAuthor);
			MouseHandler.mouseListener(txtSearchTitle);
			contentPane.setLayout(null);
			contentPane.add(spinner);
			contentPane.add(ListPane);
			contentPane.add(btnListele);
			contentPane.add(button);
			contentPane.add(textField);
			contentPane.add(button_1);
			contentPane.add(btnOyla);
			contentPane.add(txtSearchAuthor);
			contentPane.add(txtSearchTitle);
			contentPane.add(btnReadBook);

			JButton btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					obj.disposeBookRating();
					obj.getLogin().setVisible(true);
				}
			});
			btnExit.setBounds(604, 373, 93, 25);
			contentPane.add(btnExit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
