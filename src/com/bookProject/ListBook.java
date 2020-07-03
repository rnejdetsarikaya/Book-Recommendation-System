package com.bookProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DaoImpl.DaoImpl;
import model.Book;
import model.MouseHandler;
import model.PDFReader;
import model.User;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListBook extends JFrame {

	private static final long serialVersionUID = 465494660627488277L;
	private JPanel contentPane;
	static SingleObject obj = SingleObject.getSingleObject();
	static DaoImpl dao;
	PDFReader reader;
	List<JTextField> titleList;
	List<JTextField> authorList;
	List<JTextField> yearList;
	List<JTextField> publisherList;
	List<JTextField> isbnList;
	List<JButton> buttonList;
	int pageIndex = 0;
	int count = 6;
	List<Book> listBooks;
	private JTextField title_1;
	private JTextField author_1;
	private JTextField publisher_1;
	private JTextField year_1;
	private JTextField isbn_1;
	private JButton button_1;
	private JPanel panel_1;
	private JButton button_2;
	private JTextField title_2;
	private JTextField author_2;
	private JTextField publisher_2;
	private JTextField year_2;
	private JTextField isbn_2;
	private JPanel panel_3;
	private JButton button_4;
	private JTextField title_4;
	private JTextField author_4;
	private JTextField publisher_4;
	private JTextField year_4;
	private JTextField isbn_4;
	private JPanel panel_5;
	private JButton button_6;
	private JTextField title_6;
	private JTextField author_6;
	private JTextField publisher_6;
	private JTextField year_6;
	private JTextField isbn_6;
	private JPanel panel_4;
	private JButton button_5;
	private JTextField title_5;
	private JTextField author_5;
	private JTextField publisher_5;
	private JTextField year_5;
	private JTextField isbn_5;
	private JPanel panel_2;
	private JButton button_3;
	private JTextField title_3;
	private JTextField author_3;
	private JTextField publisher_3;
	private JTextField year_3;
	private JTextField isbn_3;
	private JTextField textFieldPageIndex;
	private JPanel panel_Son5;
	private JTextField topTitle1;
	private JTextField topAuthor1;
	private JTextField topRating1;
	private JTextField topTitle6;
	private JTextField topAuthor6;
	private JTextField topRating6;
	private JTextField topTitle2;
	private JTextField topAuthor2;
	private JTextField topRating2;
	private JTextField topTitle3;
	private JTextField topAuthor3;
	private JTextField topRating3;
	private JTextField topTitle4;
	private JTextField topAuthor4;
	private JTextField topRating4;
	private JTextField topTitle5;
	private JTextField topAuthor5;
	private JTextField topRating5;
	private JTextField topTitle7;
	private JTextField topAuthor7;
	private JTextField topRating7;
	private JTextField topTitle8;
	private JTextField topAuthor8;
	private JTextField topRating8;
	private JTextField topTitle9;
	private JTextField topAuthor9;
	private JTextField topRating9;
	private JTextField topTitle10;
	private JTextField topAuthor10;
	private JTextField topRating10;
	private JTextArea txtrChamp;
	private JTextField lastTitle1;
	private JTextField lastAuthor1;
	private JTextField lastRating1;
	private JTextField lastTitle2;
	private JTextField lastAuthor2;
	private JTextField lastRating2;
	private JTextField lastTitle4;
	private JTextField lastAuthor4;
	private JTextField lastRating4;
	private JTextField lastTitle5;
	private JTextField lastAuthor5;
	private JTextField lastRating5;
	private JTextField lastTitle3;
	private JTextField lastAuthor3;
	private JTextField lastRating3;
	private JPanel panel_22;
	private JButton popButton1;
	private JTextField popTitle1;
	private JTextField popAuthor1;
	private JTextField popRating1;
	private JPanel panel_23;
	private JButton popButton2;
	private JTextField popTitle2;
	private JTextField popAuthor2;
	private JTextField popRating2;
	private JPanel panel_24;
	private JButton popButton3;
	private JTextField popTitle3;
	private JTextField popAuthor3;
	private JTextField popRating3;
	private JPanel panel_25;
	private JButton popButton4;
	private JTextField popTitle4;
	private JTextField popAuthor4;
	private JTextField popRating4;
	private JPanel panel_26;
	private JButton popButton5;
	private JTextField popTitle5;
	private JTextField popAuthor5;
	private JTextField popRating5;
	private JPanel panel_27;
	private JButton popButton6;
	private JTextField popTitle6;
	private JTextField popAuthor6;
	private JTextField popRating6;
	private JPanel panel_28;
	private JButton popButton7;
	private JTextField popTitle7;
	private JTextField popAuthor7;
	private JTextField popRating7;
	private JPanel panel_29;
	private JButton popButton8;
	private JTextField popTitle8;
	private JTextField popAuthor8;
	private JTextField popRating8;
	private JPanel panel_30;
	private JTextField popTitle9;
	private JTextField popAuthor9;
	private JTextField popRating9;
	private JPanel panel_31;
	private JButton popButton10;
	private JTextField popTitle10;
	private JTextField popAuthor10;
	private JTextField popRating10;
	private JButton popButton9;
	private JButton btnNewButton;

	public void setTitle(JTextField title, int i, int pageIndex) throws Exception {
		// dao = new DaoImpl();
		// listBooks = dao.getBooks(pageIndex);
		count = listBooks.size();
		title.setEditable(false);
		title.setColumns(10);
		title.setBackground(new Color(240, 240, 240, 240));
		title.setAutoscrolls(false);
		title.setBounds(142, 0, 359, 22);
		title.setText(listBooks.get(i).getTitle());
	}

	public void setAuthor(JTextField author, int i, int pageIndex) throws Exception {
		author.setEditable(false);
		author.setColumns(10);
		author.setBackground(new Color(240, 240, 240, 240));
		author.setAutoscrolls(false);
		author.setBounds(142, 26, 359, 22);
		author.setText(listBooks.get(i).getAuthor());
	}

	public void setYear(JTextField year, int i, int pageIndex) throws Exception {
		year.setEditable(false);
		year.setColumns(10);
		year.setBackground(new Color(240, 240, 240, 240));
		year.setAutoscrolls(false);
		year.setBounds(142, 76, 360, 22);
		year.setText(listBooks.get(i).getYear() + "");
	}

	public void setPublisher(JTextField publisher, int i, int pageIndex) throws Exception {
		publisher.setEditable(false);
		publisher.setColumns(10);
		publisher.setBackground(new Color(240, 240, 240, 240));
		publisher.setAutoscrolls(false);
		publisher.setBounds(142, 51, 360, 22);
		publisher.setText(listBooks.get(i).getPublisher() + "");
	}

	private void setISBN(JTextField isbn, int i, int pageIndex) throws Exception {
		isbn.setEditable(false);
		isbn.setColumns(10);
		isbn.setBackground(new Color(240, 240, 240, 240));
		isbn.setAutoscrolls(false);
		isbn.setBounds(142, 100, 360, 22);
		isbn.setText(listBooks.get(i).getISBN() + "");
	}

	public void setButon(JButton button, int i, int pageindex) throws Exception {
		ImageIcon icon;
		button.setBorderPainted(false);
		button.setBounds(0, 0, 130, 144);
//		icon = new ImageIcon("null.png");
		icon = listBooks.get(i).getIcon();
		button.setIcon(icon);
	}

	public void getData(int pageIndex) {
		int i = 0;
		try {
			for (i = 0; i < count; i++) {
				setTitle(titleList.get(i), i, pageIndex);
				setAuthor(authorList.get(i), i, pageIndex);
				setPublisher(publisherList.get(i), i, pageIndex);
				setYear(yearList.get(i), i, pageIndex);
				setISBN(isbnList.get(i), i, pageIndex);
				setButon(buttonList.get(i), i, pageIndex);
			}
			// System.out.println(count);
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public ListBook() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				obj.getLoading().setVisible(false);
				obj.disposeLoadingScreen();
			}
		});
		ImageIcon icon = new ImageIcon("null.png");
		dao = new DaoImpl();
		int pageCount = dao.getCount("Book", 6) + 1;
		setTitle("List Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1101, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			JPanel panel_0 = new JPanel();
			panel_1 = new JPanel();
			panel_2 = new JPanel();
			panel_3 = new JPanel();
			panel_4 = new JPanel();
			panel_5 = new JPanel();

			title_1 = new JTextField();
			title_2 = new JTextField();
			title_3 = new JTextField();
			title_4 = new JTextField();
			title_5 = new JTextField();
			title_6 = new JTextField();
			titleList = new ArrayList<>();
			titleList.add(title_1);
			titleList.add(title_2);
			titleList.add(title_3);
			titleList.add(title_4);
			titleList.add(title_5);
			titleList.add(title_6);

			author_1 = new JTextField();
			author_2 = new JTextField();
			author_3 = new JTextField();
			author_4 = new JTextField();
			author_5 = new JTextField();
			author_6 = new JTextField();
			authorList = new ArrayList<>();
			authorList.add(author_1);
			authorList.add(author_2);
			authorList.add(author_3);
			authorList.add(author_4);
			authorList.add(author_5);
			authorList.add(author_6);

			year_1 = new JTextField();
			year_2 = new JTextField();
			year_3 = new JTextField();
			year_4 = new JTextField();
			year_5 = new JTextField();
			year_6 = new JTextField();
			yearList = new ArrayList<>();
			yearList.add(year_1);
			yearList.add(year_2);
			yearList.add(year_3);
			yearList.add(year_4);
			yearList.add(year_5);
			yearList.add(year_6);

			publisher_1 = new JTextField();
			publisher_2 = new JTextField();
			publisher_3 = new JTextField();
			publisher_4 = new JTextField();
			publisher_5 = new JTextField();
			publisher_6 = new JTextField();

			publisherList = new ArrayList<>();
			publisherList.add(publisher_1);
			publisherList.add(publisher_2);
			publisherList.add(publisher_3);
			publisherList.add(publisher_4);
			publisherList.add(publisher_5);
			publisherList.add(publisher_6);

			isbn_1 = new JTextField();
			isbn_2 = new JTextField();
			isbn_3 = new JTextField();
			isbn_4 = new JTextField();
			isbn_5 = new JTextField();
			isbn_6 = new JTextField();
			isbnList = new ArrayList<>();
			isbnList.add(isbn_1);
			isbnList.add(isbn_2);
			isbnList.add(isbn_3);
			isbnList.add(isbn_4);
			isbnList.add(isbn_5);
			isbnList.add(isbn_6);

			listBooks = dao.getBooks(pageIndex);
			button_1 = new JButton("");

			button_2 = new JButton("");
			button_3 = new JButton("");
			button_4 = new JButton("");
			button_5 = new JButton("");
			button_6 = new JButton("");
			MouseHandler.mouseListener(button_1, "list_1", listBooks);
			MouseHandler.mouseListener(button_2, "list_2", listBooks);
			MouseHandler.mouseListener(button_3, "list_3", listBooks);
			MouseHandler.mouseListener(button_4, "list_4", listBooks);
			MouseHandler.mouseListener(button_5, "list_5", listBooks);
			MouseHandler.mouseListener(button_6, "list_6", listBooks);
			buttonList = new ArrayList<>();
			buttonList.add(button_1);
			buttonList.add(button_2);
			buttonList.add(button_3);
			buttonList.add(button_4);
			buttonList.add(button_5);
			buttonList.add(button_6);

			panel_0.add(button_1);
			panel_1.add(button_2);
			panel_2.add(button_3);
			panel_3.add(button_4);
			panel_4.add(button_5);
			panel_5.add(button_6);

			panel_0.add(title_1);
			panel_1.add(title_2);
			panel_2.add(title_3);
			panel_3.add(title_4);
			panel_4.add(title_5);
			panel_5.add(title_6);

			panel_0.add(author_1);
			panel_1.add(author_2);
			panel_2.add(author_3);
			panel_3.add(author_4);
			panel_4.add(author_5);
			panel_5.add(author_6);

			panel_0.add(publisher_1);
			panel_1.add(publisher_2);
			panel_2.add(publisher_3);
			panel_3.add(publisher_4);
			panel_4.add(publisher_5);
			panel_5.add(publisher_6);

			panel_0.add(year_1);
			panel_1.add(year_2);
			panel_2.add(year_3);
			panel_3.add(year_4);
			panel_4.add(year_5);
			panel_5.add(year_6);

			panel_0.add(isbn_1);
			panel_1.add(isbn_2);
			panel_2.add(isbn_3);
			panel_3.add(isbn_4);
			panel_4.add(isbn_5);
			panel_5.add(isbn_6);

			panel_0.setLayout(null);
			panel_1.setLayout(null);
			panel_2.setLayout(null);
			panel_3.setLayout(null);
			panel_4.setLayout(null);
			panel_5.setLayout(null);
			getData(pageIndex);
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE));
			gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE));

			JPanel panel = new JPanel();
			tabbedPane.addTab("Main Page", null, panel, null);

			JButton buttonBack = new JButton("Back");
			buttonBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (pageIndex > 0) {
						pageIndex--;
						try {
							listBooks = dao.getBooks(pageIndex);
							MouseHandler.mouseListener(button_1, "list_1", listBooks);
							MouseHandler.mouseListener(button_2, "list_2", listBooks);
							MouseHandler.mouseListener(button_3, "list_3", listBooks);
							MouseHandler.mouseListener(button_4, "list_4", listBooks);
							MouseHandler.mouseListener(button_5, "list_5", listBooks);
							MouseHandler.mouseListener(button_6, "list_6", listBooks);
						} catch (Exception e2) {
							// TODO: handle exception
						}
						getData(pageIndex);
						textFieldPageIndex.setText((pageIndex + 1) + "/" + pageCount);
					}

				}
			});

			textFieldPageIndex = new JTextField();
			textFieldPageIndex.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						pageIndex = Integer.parseInt(textFieldPageIndex.getText()) - 1;
						if (pageCount > pageIndex && pageIndex >= 0) {
							try {
								listBooks = dao.getBooks(pageIndex);
								MouseHandler.mouseListener(button_1, "list_1", listBooks);
								MouseHandler.mouseListener(button_2, "list_2", listBooks);
								MouseHandler.mouseListener(button_3, "list_3", listBooks);
								MouseHandler.mouseListener(button_4, "list_4", listBooks);
								MouseHandler.mouseListener(button_5, "list_5", listBooks);
								MouseHandler.mouseListener(button_6, "list_6", listBooks);
								getData(pageIndex);
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Lutfen gecerli bir sayfa numarasi giriniz");
								textFieldPageIndex.setText((pageIndex + 1) + "/" + pageCount);
							}
						}
					}
				}

			});
			textFieldPageIndex.setText("1");
			textFieldPageIndex.setColumns(10);
			textFieldPageIndex.setBackground(SystemColor.menu);

			JButton buttonNext = new JButton("Next");
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pageCount > pageIndex + 1) {

						pageIndex++;
						try {
							listBooks = dao.getBooks(pageIndex);
							MouseHandler.mouseListener(button_1, "list_1", listBooks);
							MouseHandler.mouseListener(button_2, "list_2", listBooks);
							MouseHandler.mouseListener(button_3, "list_3", listBooks);
							MouseHandler.mouseListener(button_4, "list_4", listBooks);
							MouseHandler.mouseListener(button_5, "list_5", listBooks);
							MouseHandler.mouseListener(button_6, "list_6", listBooks);
						} catch (Exception e2) {
							// TODO: handle exception
						}
						getData(pageIndex);
						textFieldPageIndex.setText((pageIndex + 1) + "/" + pageCount);

					}
				}
			});

			btnNewButton = new JButton("Exit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					obj.disposeListBook();
					obj.getLogin().setVisible(true);
				}
			});

			JButton btnNewButton_1 = new JButton("Recommendations");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(User.getUser().getUserId());
					obj.getBookRecomandation().setVisible(true);
				}
			});
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_0, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup().addGap(405)
									.addComponent(buttonBack, GroupLayout.PREFERRED_SIZE, 73,
											GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(textFieldPageIndex, GroupLayout.PREFERRED_SIZE, 87,
											GroupLayout.PREFERRED_SIZE)
									.addGap(12).addComponent(buttonNext, GroupLayout.PREFERRED_SIZE, 71,
											GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING,
									gl_panel.createSequentialGroup().addContainerGap().addComponent(btnNewButton_1)
											.addPreferredGap(ComponentPlacement.RELATED, 868, Short.MAX_VALUE)
											.addComponent(btnNewButton)))
							.addContainerGap()));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_0, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(
											textFieldPageIndex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE))
									.addComponent(buttonNext).addComponent(buttonBack))
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
							.addContainerGap()));
			panel_Son5 = new JPanel();
			tabbedPane.addTab("Last5", null, panel_Son5, null);
			panel_Son5.setLayout(null);

			JPanel panel_16 = new JPanel();
			panel_16.setLayout(null);
			panel_16.setBounds(12, 84, 500, 95);
			panel_Son5.add(panel_16);

			JButton lastFiveButton1 = new JButton("");

			lastFiveButton1.setBounds(12, 13, 54, 75);
			panel_16.add(lastFiveButton1);

			lastTitle1 = new JTextField();
			lastTitle1.setEditable(false);
			lastTitle1.setColumns(10);
			lastTitle1.setBounds(89, 13, 399, 22);
			panel_16.add(lastTitle1);

			lastAuthor1 = new JTextField();
			lastAuthor1.setEditable(false);
			lastAuthor1.setColumns(10);
			lastAuthor1.setBounds(89, 38, 399, 25);
			panel_16.add(lastAuthor1);

			lastRating1 = new JTextField();
			lastRating1.setEditable(false);
			lastRating1.setColumns(10);
			lastRating1.setBounds(89, 66, 399, 22);
			panel_16.add(lastRating1);

			JPanel panel_17 = new JPanel();
			panel_17.setLayout(null);
			panel_17.setBounds(12, 213, 500, 95);
			panel_Son5.add(panel_17);

			JButton lastFiveButton2 = new JButton("");
			lastFiveButton2.setBounds(12, 13, 54, 75);
			panel_17.add(lastFiveButton2);

			lastTitle2 = new JTextField();
			lastTitle2.setEditable(false);
			lastTitle2.setColumns(10);
			lastTitle2.setBounds(89, 13, 399, 22);
			panel_17.add(lastTitle2);

			lastAuthor2 = new JTextField();
			lastAuthor2.setEditable(false);
			lastAuthor2.setColumns(10);
			lastAuthor2.setBounds(89, 38, 399, 25);
			panel_17.add(lastAuthor2);

			lastRating2 = new JTextField();
			lastRating2.setEditable(false);
			lastRating2.setColumns(10);
			lastRating2.setBounds(89, 66, 399, 22);
			panel_17.add(lastRating2);

			JPanel panel_18 = new JPanel();
			panel_18.setLayout(null);
			panel_18.setBounds(550, 213, 500, 95);
			panel_Son5.add(panel_18);

			JButton lastFiveButton4 = new JButton("");
			lastFiveButton4.setBounds(12, 13, 54, 75);
			panel_18.add(lastFiveButton4);

			lastTitle4 = new JTextField();
			lastTitle4.setEditable(false);
			lastTitle4.setColumns(10);
			lastTitle4.setBounds(89, 13, 399, 22);
			panel_18.add(lastTitle4);

			lastAuthor4 = new JTextField();
			lastAuthor4.setEditable(false);
			lastAuthor4.setColumns(10);
			lastAuthor4.setBounds(89, 38, 399, 25);
			panel_18.add(lastAuthor4);

			lastRating4 = new JTextField();
			lastRating4.setEditable(false);
			lastRating4.setColumns(10);
			lastRating4.setBounds(89, 66, 399, 22);
			panel_18.add(lastRating4);

			JPanel panel_19 = new JPanel();
			panel_19.setLayout(null);
			panel_19.setBounds(292, 337, 500, 95);
			panel_Son5.add(panel_19);

			JButton lastFiveButton5 = new JButton("");
			lastFiveButton5.setBounds(12, 13, 54, 75);
			panel_19.add(lastFiveButton5);

			lastTitle5 = new JTextField();
			lastTitle5.setEditable(false);
			lastTitle5.setColumns(10);
			lastTitle5.setBounds(89, 13, 399, 22);
			panel_19.add(lastTitle5);

			lastAuthor5 = new JTextField();
			lastAuthor5.setEditable(false);
			lastAuthor5.setColumns(10);
			lastAuthor5.setBounds(89, 38, 399, 25);
			panel_19.add(lastAuthor5);

			lastRating5 = new JTextField();
			lastRating5.setEditable(false);
			lastRating5.setColumns(10);
			lastRating5.setBounds(89, 66, 399, 22);
			panel_19.add(lastRating5);

			JPanel panel_20 = new JPanel();
			panel_20.setLayout(null);
			panel_20.setBounds(550, 84, 500, 95);
			panel_Son5.add(panel_20);

			JButton lastFiveButton3 = new JButton("");
			lastFiveButton3.setBounds(12, 13, 54, 75);
			panel_20.add(lastFiveButton3);

			lastTitle3 = new JTextField();
			lastTitle3.setEditable(false);
			lastTitle3.setColumns(10);
			lastTitle3.setBounds(89, 13, 399, 22);
			panel_20.add(lastTitle3);

			lastAuthor3 = new JTextField();
			lastAuthor3.setEditable(false);
			lastAuthor3.setColumns(10);
			lastAuthor3.setBounds(89, 38, 399, 25);
			panel_20.add(lastAuthor3);

			lastRating3 = new JTextField();
			lastRating3.setEditable(false);
			lastRating3.setColumns(10);
			lastRating3.setBounds(89, 66, 399, 22);
			panel_20.add(lastRating3);
			panel.setLayout(gl_panel);

			List<Book> lastFiveBooks = dao.getLastFiveBooks();
			MouseHandler.mouseListener(lastFiveButton1, "last_1", lastFiveBooks);
			MouseHandler.mouseListener(lastFiveButton2, "last_2", lastFiveBooks);
			MouseHandler.mouseListener(lastFiveButton3, "last_3", lastFiveBooks);
			MouseHandler.mouseListener(lastFiveButton4, "last_4", lastFiveBooks);
			MouseHandler.mouseListener(lastFiveButton5, "last_5", lastFiveBooks);
			List<JTextField> lastFiveTitles = new ArrayList<>();
			lastFiveTitles.add(lastTitle1);
			lastFiveTitles.add(lastTitle2);
			lastFiveTitles.add(lastTitle3);
			lastFiveTitles.add(lastTitle4);
			lastFiveTitles.add(lastTitle5);

			List<JTextField> lastFiveAuthors = new ArrayList<>();
			lastFiveAuthors.add(lastAuthor1);
			lastFiveAuthors.add(lastAuthor2);
			lastFiveAuthors.add(lastAuthor3);
			lastFiveAuthors.add(lastAuthor4);
			lastFiveAuthors.add(lastAuthor5);

			List<JTextField> lastFiveRatings = new ArrayList<>();
			lastFiveRatings.add(lastRating1);
			lastFiveRatings.add(lastRating2);
			lastFiveRatings.add(lastRating3);
			lastFiveRatings.add(lastRating4);
			lastFiveRatings.add(lastRating5);

			List<JButton> lastFiveButtons = new ArrayList<>();
			lastFiveButtons.add(lastFiveButton1);
			lastFiveButtons.add(lastFiveButton2);
			lastFiveButtons.add(lastFiveButton3);
			lastFiveButtons.add(lastFiveButton4);
			lastFiveButtons.add(lastFiveButton5);

			for (int i = 0; i < lastFiveBooks.size(); i++) {
				lastFiveTitles.get(i).setText(lastFiveBooks.get(i).getTitle());
				lastFiveAuthors.get(i).setText(lastFiveBooks.get(i).getAuthor());
				lastFiveRatings.get(i).setText(lastFiveBooks.get(i).getPoint());
				lastFiveTitles.get(i).setText(lastFiveBooks.get(i).getTitle());
			}
			List<Book> topBooks = dao.listTop10();
			JPanel panel_Top10 = new JPanel();
			tabbedPane.addTab("Top 10", null, panel_Top10, null);
			panel_Top10.setLayout(null);

			JPanel panel_6 = new JPanel();
			panel_6.setBounds(12, 13, 500, 95);
			panel_Top10.add(panel_6);
			panel_6.setLayout(null);

			JButton topButton1 = new JButton("");
			MouseHandler.mouseListener(topButton1, "top_1", topBooks);
			topButton1.setBounds(12, 13, 54, 75);
			panel_6.add(topButton1);

			topTitle1 = new JTextField();
			topTitle1.setEditable(false);
			topTitle1.setBounds(89, 13, 399, 22);
			panel_6.add(topTitle1);
			topTitle1.setColumns(10);

			topAuthor1 = new JTextField();
			topAuthor1.setEditable(false);
			topAuthor1.setColumns(10);
			topAuthor1.setBounds(89, 38, 399, 25);
			panel_6.add(topAuthor1);

			topRating1 = new JTextField();
			topRating1.setEditable(false);
			topRating1.setColumns(10);
			topRating1.setBounds(89, 66, 399, 22);
			panel_6.add(topRating1);

			JPanel panel_11 = new JPanel();
			panel_11.setLayout(null);
			panel_11.setBounds(570, 13, 500, 95);
			panel_Top10.add(panel_11);

			JButton topButton6 = new JButton("");
			MouseHandler.mouseListener(topButton6, "top_6", topBooks);
			topButton6.setBounds(12, 13, 54, 75);
			panel_11.add(topButton6);

			topTitle6 = new JTextField();
			topTitle6.setEditable(false);
			topTitle6.setColumns(10);
			topTitle6.setBounds(89, 13, 399, 22);
			panel_11.add(topTitle6);

			topAuthor6 = new JTextField();
			topAuthor6.setEditable(false);
			topAuthor6.setColumns(10);
			topAuthor6.setBounds(89, 38, 399, 25);
			panel_11.add(topAuthor6);

			topRating6 = new JTextField();
			topRating6.setEditable(false);
			topRating6.setColumns(10);
			topRating6.setBounds(89, 66, 399, 22);
			panel_11.add(topRating6);

			JPanel panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.setBounds(12, 121, 500, 95);
			panel_Top10.add(panel_7);

			JButton topButton2 = new JButton("");
			MouseHandler.mouseListener(topButton2, "top_2", topBooks);
			topButton2.setBounds(12, 13, 54, 75);
			panel_7.add(topButton2);

			topTitle2 = new JTextField();
			topTitle2.setEditable(false);
			topTitle2.setColumns(10);
			topTitle2.setBounds(89, 13, 399, 22);
			panel_7.add(topTitle2);

			topAuthor2 = new JTextField();
			topAuthor2.setEditable(false);
			topAuthor2.setColumns(10);
			topAuthor2.setBounds(89, 38, 399, 25);
			panel_7.add(topAuthor2);

			topRating2 = new JTextField();
			topRating2.setEditable(false);
			topRating2.setColumns(10);
			topRating2.setBounds(89, 66, 399, 22);
			panel_7.add(topRating2);

			JPanel panel_8 = new JPanel();
			panel_8.setLayout(null);
			panel_8.setBounds(12, 229, 500, 95);
			panel_Top10.add(panel_8);

			JButton topButton3 = new JButton("");
			MouseHandler.mouseListener(topButton3, "top_3", topBooks);
			topButton3.setBounds(12, 13, 54, 75);
			panel_8.add(topButton3);

			topTitle3 = new JTextField();
			topTitle3.setEditable(false);
			topTitle3.setColumns(10);
			topTitle3.setBounds(89, 13, 399, 22);
			panel_8.add(topTitle3);

			topAuthor3 = new JTextField();
			topAuthor3.setEditable(false);
			topAuthor3.setColumns(10);
			topAuthor3.setBounds(89, 38, 399, 25);
			panel_8.add(topAuthor3);

			topRating3 = new JTextField();
			topRating3.setEditable(false);
			topRating3.setColumns(10);
			topRating3.setBounds(89, 66, 399, 22);
			panel_8.add(topRating3);

			JPanel panel_9 = new JPanel();
			panel_9.setLayout(null);
			panel_9.setBounds(12, 337, 500, 95);
			panel_Top10.add(panel_9);

			JButton topButton4 = new JButton("");
			MouseHandler.mouseListener(topButton4, "top_4", topBooks);
			topButton4.setBounds(12, 13, 54, 75);
			panel_9.add(topButton4);

			topTitle4 = new JTextField();
			topTitle4.setEditable(false);
			topTitle4.setColumns(10);
			topTitle4.setBounds(89, 13, 399, 22);
			panel_9.add(topTitle4);

			topAuthor4 = new JTextField();
			topAuthor4.setEditable(false);
			topAuthor4.setColumns(10);
			topAuthor4.setBounds(89, 38, 399, 25);
			panel_9.add(topAuthor4);

			topRating4 = new JTextField();
			topRating4.setEditable(false);
			topRating4.setColumns(10);
			topRating4.setBounds(89, 66, 399, 22);
			panel_9.add(topRating4);

			JPanel panel_10 = new JPanel();
			panel_10.setLayout(null);
			panel_10.setBounds(12, 446, 500, 95);
			panel_Top10.add(panel_10);

			JButton topButton5 = new JButton("");
			MouseHandler.mouseListener(topButton5, "top_5", topBooks);
			topButton5.setBounds(12, 13, 54, 75);
			panel_10.add(topButton5);

			topTitle5 = new JTextField();
			topTitle5.setEditable(false);
			topTitle5.setColumns(10);
			topTitle5.setBounds(89, 13, 399, 22);
			panel_10.add(topTitle5);

			topAuthor5 = new JTextField();
			topAuthor5.setEditable(false);
			topAuthor5.setColumns(10);
			topAuthor5.setBounds(89, 38, 399, 25);
			panel_10.add(topAuthor5);

			topRating5 = new JTextField();
			topRating5.setEditable(false);
			topRating5.setColumns(10);
			topRating5.setBounds(89, 66, 399, 22);
			panel_10.add(topRating5);

			JPanel panel_12 = new JPanel();
			panel_12.setLayout(null);
			panel_12.setBounds(570, 121, 500, 95);
			panel_Top10.add(panel_12);

			JButton topButton7 = new JButton("");
			MouseHandler.mouseListener(topButton7, "top_7", topBooks);
			topButton7.setBounds(12, 13, 54, 75);
			panel_12.add(topButton7);

			topTitle7 = new JTextField();
			topTitle7.setEditable(false);
			topTitle7.setColumns(10);
			topTitle7.setBounds(89, 13, 399, 22);
			panel_12.add(topTitle7);

			topAuthor7 = new JTextField();
			topAuthor7.setEditable(false);
			topAuthor7.setColumns(10);
			topAuthor7.setBounds(89, 38, 399, 25);
			panel_12.add(topAuthor7);

			topRating7 = new JTextField();
			topRating7.setEditable(false);
			topRating7.setColumns(10);
			topRating7.setBounds(89, 66, 399, 22);
			panel_12.add(topRating7);

			JPanel panel_13 = new JPanel();
			panel_13.setLayout(null);
			panel_13.setBounds(570, 229, 500, 95);
			panel_Top10.add(panel_13);

			JButton topButton8 = new JButton("");
			MouseHandler.mouseListener(topButton8, "top_8", topBooks);
			topButton8.setBounds(12, 13, 54, 75);
			panel_13.add(topButton8);

			topTitle8 = new JTextField();
			topTitle8.setEditable(false);
			topTitle8.setColumns(10);
			topTitle8.setBounds(89, 13, 399, 22);
			panel_13.add(topTitle8);

			topAuthor8 = new JTextField();
			topAuthor8.setEditable(false);
			topAuthor8.setColumns(10);
			topAuthor8.setBounds(89, 38, 399, 25);
			panel_13.add(topAuthor8);

			topRating8 = new JTextField();
			topRating8.setEditable(false);
			topRating8.setColumns(10);
			topRating8.setBounds(89, 66, 399, 22);
			panel_13.add(topRating8);

			JPanel panel_14 = new JPanel();
			panel_14.setLayout(null);
			panel_14.setBounds(570, 337, 500, 95);
			panel_Top10.add(panel_14);

			JButton topButton9 = new JButton("");
			MouseHandler.mouseListener(topButton9, "top_9", topBooks);
			topButton9.setBounds(12, 13, 54, 75);
			panel_14.add(topButton9);

			topTitle9 = new JTextField();
			topTitle9.setEditable(false);
			topTitle9.setColumns(10);
			topTitle9.setBounds(89, 13, 399, 22);
			panel_14.add(topTitle9);

			topAuthor9 = new JTextField();
			topAuthor9.setEditable(false);
			topAuthor9.setColumns(10);
			topAuthor9.setBounds(89, 38, 399, 25);
			panel_14.add(topAuthor9);

			topRating9 = new JTextField();
			topRating9.setEditable(false);
			topRating9.setColumns(10);
			topRating9.setBounds(89, 66, 399, 22);
			panel_14.add(topRating9);

			JPanel panel_15 = new JPanel();
			panel_15.setLayout(null);
			panel_15.setBounds(570, 446, 500, 95);
			panel_Top10.add(panel_15);

			JButton topButton10 = new JButton("");
			MouseHandler.mouseListener(topButton10, "top_10", topBooks);
			topButton10.setBounds(12, 13, 54, 75);
			panel_15.add(topButton10);

			topTitle10 = new JTextField();
			topTitle10.setEditable(false);
			topTitle10.setColumns(10);
			topTitle10.setBounds(89, 13, 399, 22);
			panel_15.add(topTitle10);

			topAuthor10 = new JTextField();
			topAuthor10.setEditable(false);
			topAuthor10.setColumns(10);
			topAuthor10.setBounds(89, 38, 399, 25);
			panel_15.add(topAuthor10);

			topRating10 = new JTextField();
			topRating10.setEditable(false);
			topRating10.setColumns(10);
			topRating10.setBounds(89, 66, 399, 22);
			panel_15.add(topRating10);
			List<JTextField> topTitles = new ArrayList<JTextField>();
			topTitles.add(topTitle1);
			topTitles.add(topTitle2);
			topTitles.add(topTitle3);
			topTitles.add(topTitle4);
			topTitles.add(topTitle5);
			topTitles.add(topTitle6);
			topTitles.add(topTitle7);
			topTitles.add(topTitle8);
			topTitles.add(topTitle9);
			topTitles.add(topTitle10);

			List<JTextField> topAuthors = new ArrayList<>();
			topAuthors.add(topAuthor1);
			topAuthors.add(topAuthor2);
			topAuthors.add(topAuthor3);
			topAuthors.add(topAuthor4);
			topAuthors.add(topAuthor5);
			topAuthors.add(topAuthor6);
			topAuthors.add(topAuthor7);
			topAuthors.add(topAuthor8);
			topAuthors.add(topAuthor9);
			topAuthors.add(topAuthor10);

			List<JTextField> topRatings = new ArrayList<>();
			topRatings.add(topRating1);
			topRatings.add(topRating2);
			topRatings.add(topRating3);
			topRatings.add(topRating4);
			topRatings.add(topRating5);
			topRatings.add(topRating6);
			topRatings.add(topRating7);
			topRatings.add(topRating8);
			topRatings.add(topRating9);
			topRatings.add(topRating10);

			List<JButton> topButtons = new ArrayList<>();
			topButtons.add(topButton1);
			topButtons.add(topButton2);
			topButtons.add(topButton3);
			topButtons.add(topButton4);
			topButtons.add(topButton5);
			topButtons.add(topButton6);
			topButtons.add(topButton7);
			topButtons.add(topButton8);
			topButtons.add(topButton9);
			topButtons.add(topButton10);

			txtrChamp = new JTextArea();
			txtrChamp.setBackground(SystemColor.control);
			txtrChamp.setMinimumSize(new Dimension(10, 16));
			txtrChamp.setText("#\r\nC\r\nH\r\nA\r\nM\r\nP\r\nI\r\nO\r\nN\r\n\r\nL\r\nE\r\nA\r\nG\r\nU\r\nE\r\n#");
			txtrChamp.setFont(txtrChamp.getFont().deriveFont(txtrChamp.getFont().getSize() + 7.0f));
			txtrChamp.setBounds(534, 13, 24, 502);
			panel_Top10.add(txtrChamp);

			JPanel panel_21 = new JPanel();
			tabbedPane.addTab("Pop 10", null, panel_21, null);

			panel_22 = new JPanel();
			panel_22.setLayout(null);

			popButton1 = new JButton("");
			popButton1.setBounds(12, 13, 54, 75);
			panel_22.add(popButton1);

			popTitle1 = new JTextField();
			popTitle1.setEditable(false);
			popTitle1.setColumns(10);
			popTitle1.setBounds(89, 13, 399, 22);
			panel_22.add(popTitle1);

			popAuthor1 = new JTextField();
			popAuthor1.setEditable(false);
			popAuthor1.setColumns(10);
			popAuthor1.setBounds(89, 38, 399, 25);
			panel_22.add(popAuthor1);

			popRating1 = new JTextField();
			popRating1.setEditable(false);
			popRating1.setColumns(10);
			popRating1.setBounds(89, 66, 399, 22);
			panel_22.add(popRating1);

			panel_23 = new JPanel();
			panel_23.setLayout(null);

			popButton2 = new JButton("");
			popButton2.setBounds(12, 13, 54, 75);
			panel_23.add(popButton2);

			popTitle2 = new JTextField();
			popTitle2.setEditable(false);
			popTitle2.setColumns(10);
			popTitle2.setBounds(89, 13, 399, 22);
			panel_23.add(popTitle2);

			popAuthor2 = new JTextField();
			popAuthor2.setEditable(false);
			popAuthor2.setColumns(10);
			popAuthor2.setBounds(89, 38, 399, 25);
			panel_23.add(popAuthor2);

			popRating2 = new JTextField();
			popRating2.setEditable(false);
			popRating2.setColumns(10);
			popRating2.setBounds(89, 66, 399, 22);
			panel_23.add(popRating2);

			panel_24 = new JPanel();
			panel_24.setLayout(null);

			popButton3 = new JButton("");
			popButton3.setBounds(12, 13, 54, 75);
			panel_24.add(popButton3);

			popTitle3 = new JTextField();
			popTitle3.setEditable(false);
			popTitle3.setColumns(10);
			popTitle3.setBounds(89, 13, 399, 22);
			panel_24.add(popTitle3);

			popAuthor3 = new JTextField();
			popAuthor3.setEditable(false);
			popAuthor3.setColumns(10);
			popAuthor3.setBounds(89, 38, 399, 25);
			panel_24.add(popAuthor3);

			popRating3 = new JTextField();
			popRating3.setEditable(false);
			popRating3.setColumns(10);
			popRating3.setBounds(89, 66, 399, 22);
			panel_24.add(popRating3);

			panel_25 = new JPanel();
			panel_25.setLayout(null);

			popButton4 = new JButton("");
			popButton4.setBounds(12, 13, 54, 75);
			panel_25.add(popButton4);

			popTitle4 = new JTextField();
			popTitle4.setEditable(false);
			popTitle4.setColumns(10);
			popTitle4.setBounds(89, 13, 399, 22);
			panel_25.add(popTitle4);

			popAuthor4 = new JTextField();
			popAuthor4.setEditable(false);
			popAuthor4.setColumns(10);
			popAuthor4.setBounds(89, 38, 399, 25);
			panel_25.add(popAuthor4);

			popRating4 = new JTextField();
			popRating4.setEditable(false);
			popRating4.setColumns(10);
			popRating4.setBounds(89, 66, 399, 22);
			panel_25.add(popRating4);

			panel_26 = new JPanel();
			panel_26.setLayout(null);

			popButton5 = new JButton("");
			popButton5.setBounds(12, 13, 54, 75);
			panel_26.add(popButton5);

			popTitle5 = new JTextField();
			popTitle5.setEditable(false);
			popTitle5.setColumns(10);
			popTitle5.setBounds(89, 13, 399, 22);
			panel_26.add(popTitle5);

			popAuthor5 = new JTextField();
			popAuthor5.setEditable(false);
			popAuthor5.setColumns(10);
			popAuthor5.setBounds(89, 38, 399, 25);
			panel_26.add(popAuthor5);

			popRating5 = new JTextField();
			popRating5.setEditable(false);
			popRating5.setColumns(10);
			popRating5.setBounds(89, 66, 399, 22);
			panel_26.add(popRating5);

			panel_27 = new JPanel();
			panel_27.setLayout(null);

			popButton6 = new JButton("");
			popButton6.setBounds(12, 13, 54, 75);
			panel_27.add(popButton6);

			popTitle6 = new JTextField();
			popTitle6.setEditable(false);
			popTitle6.setColumns(10);
			popTitle6.setBounds(89, 13, 399, 22);
			panel_27.add(popTitle6);

			popAuthor6 = new JTextField();
			popAuthor6.setEditable(false);
			popAuthor6.setColumns(10);
			popAuthor6.setBounds(89, 38, 399, 25);
			panel_27.add(popAuthor6);

			popRating6 = new JTextField();
			popRating6.setEditable(false);
			popRating6.setColumns(10);
			popRating6.setBounds(89, 66, 399, 22);
			panel_27.add(popRating6);

			panel_28 = new JPanel();
			panel_28.setLayout(null);

			popButton7 = new JButton("");
			popButton7.setBounds(12, 13, 54, 75);
			panel_28.add(popButton7);

			popTitle7 = new JTextField();
			popTitle7.setEditable(false);
			popTitle7.setColumns(10);
			popTitle7.setBounds(89, 13, 399, 22);
			panel_28.add(popTitle7);

			popAuthor7 = new JTextField();
			popAuthor7.setEditable(false);
			popAuthor7.setColumns(10);
			popAuthor7.setBounds(89, 38, 399, 25);
			panel_28.add(popAuthor7);

			popRating7 = new JTextField();
			popRating7.setEditable(false);
			popRating7.setColumns(10);
			popRating7.setBounds(89, 66, 399, 22);
			panel_28.add(popRating7);

			panel_29 = new JPanel();
			panel_29.setLayout(null);

			popButton8 = new JButton("");
			popButton8.setBounds(12, 13, 54, 75);
			panel_29.add(popButton8);

			popTitle8 = new JTextField();
			popTitle8.setEditable(false);
			popTitle8.setColumns(10);
			popTitle8.setBounds(89, 13, 399, 22);
			panel_29.add(popTitle8);

			popAuthor8 = new JTextField();
			popAuthor8.setEditable(false);
			popAuthor8.setColumns(10);
			popAuthor8.setBounds(89, 38, 399, 25);
			panel_29.add(popAuthor8);

			popRating8 = new JTextField();
			popRating8.setEditable(false);
			popRating8.setColumns(10);
			popRating8.setBounds(89, 66, 399, 22);
			panel_29.add(popRating8);

			panel_30 = new JPanel();
			panel_30.setLayout(null);

			popButton9 = new JButton("");
			popButton9.setBounds(12, 13, 54, 75);
			panel_30.add(popButton9);

			popTitle9 = new JTextField();
			popTitle9.setEditable(false);
			popTitle9.setColumns(10);
			popTitle9.setBounds(89, 13, 399, 22);
			panel_30.add(popTitle9);

			popAuthor9 = new JTextField();
			popAuthor9.setEditable(false);
			popAuthor9.setColumns(10);
			popAuthor9.setBounds(89, 38, 399, 25);
			panel_30.add(popAuthor9);

			popRating9 = new JTextField();
			popRating9.setEditable(false);
			popRating9.setColumns(10);
			popRating9.setBounds(89, 66, 399, 22);
			panel_30.add(popRating9);

			panel_31 = new JPanel();
			panel_31.setLayout(null);

			popButton10 = new JButton("");
			popButton10.setBounds(12, 13, 54, 75);
			panel_31.add(popButton10);

			popTitle10 = new JTextField();
			popTitle10.setEditable(false);
			popTitle10.setColumns(10);
			popTitle10.setBounds(89, 13, 399, 22);
			panel_31.add(popTitle10);

			popAuthor10 = new JTextField();
			popAuthor10.setEditable(false);
			popAuthor10.setColumns(10);
			popAuthor10.setBounds(89, 38, 399, 25);
			panel_31.add(popAuthor10);

			popRating10 = new JTextField();
			popRating10.setEditable(false);
			popRating10.setColumns(10);
			popRating10.setBounds(89, 66, 399, 22);
			panel_31.add(popRating10);

			List<JTextField> popTitles = new ArrayList<>();
			popTitles.add(popTitle1);
			popTitles.add(popTitle2);
			popTitles.add(popTitle3);
			popTitles.add(popTitle4);
			popTitles.add(popTitle5);
			popTitles.add(popTitle6);
			popTitles.add(popTitle7);
			popTitles.add(popTitle8);
			popTitles.add(popTitle9);
			popTitles.add(popTitle10);

			List<JTextField> popAuthors = new ArrayList<>();
			popAuthors.add(popAuthor1);
			popAuthors.add(popAuthor2);
			popAuthors.add(popAuthor3);
			popAuthors.add(popAuthor4);
			popAuthors.add(popAuthor5);
			popAuthors.add(popAuthor6);
			popAuthors.add(popAuthor7);
			popAuthors.add(popAuthor8);
			popAuthors.add(popAuthor9);
			popAuthors.add(popAuthor10);

			List<JTextField> popRatings = new ArrayList<>();
			popRatings.add(popRating1);
			popRatings.add(popRating2);
			popRatings.add(popRating3);
			popRatings.add(popRating4);
			popRatings.add(popRating5);
			popRatings.add(popRating6);
			popRatings.add(popRating7);
			popRatings.add(popRating8);
			popRatings.add(popRating9);
			popRatings.add(popRating10);

			List<JButton> popButtons = new ArrayList<>();
			popButtons.add(popButton1);
			popButtons.add(popButton2);
			popButtons.add(popButton3);
			popButtons.add(popButton4);
			popButtons.add(popButton5);
			popButtons.add(popButton6);
			popButtons.add(popButton7);
			popButtons.add(popButton8);
			popButtons.add(popButton9);
			popButtons.add(popButton10);

			List<Book> popBooks = new ArrayList<>();
			popBooks = dao.listPop10();
			for (int i = 0; i < 10; i++) {
				popRatings.get(i).setText(popBooks.get(i).getPoint());
				popAuthors.get(i).setText(popBooks.get(i).getAuthor());
				popTitles.get(i).setText(popBooks.get(i).getTitle());
				popButtons.get(i).setIcon(popBooks.get(i).getIcon());
			}

			MouseHandler.mouseListener(popButton1, "pop_1", popBooks);
			MouseHandler.mouseListener(popButton2, "pop_2", popBooks);
			MouseHandler.mouseListener(popButton3, "pop_3", popBooks);
			MouseHandler.mouseListener(popButton4, "pop_4", popBooks);
			MouseHandler.mouseListener(popButton5, "pop_5", popBooks);
			MouseHandler.mouseListener(popButton6, "pop_6", popBooks);
			MouseHandler.mouseListener(popButton7, "pop_7", popBooks);
			MouseHandler.mouseListener(popButton8, "pop_8", popBooks);
			MouseHandler.mouseListener(popButton9, "pop_9", popBooks);
			MouseHandler.mouseListener(popButton10, "pop_10", popBooks);

			GroupLayout gl_panel_21 = new GroupLayout(panel_21);
			gl_panel_21.setHorizontalGroup(gl_panel_21.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_21
					.createSequentialGroup().addContainerGap()
					.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_21.createSequentialGroup()
									.addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addGap(58).addComponent(panel_27, GroupLayout.PREFERRED_SIZE, 500,
											GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_21.createSequentialGroup()
									.addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addGap(58).addComponent(panel_28, GroupLayout.PREFERRED_SIZE, 500,
											GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_21.createSequentialGroup()
									.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addGap(58).addComponent(panel_29, GroupLayout.PREFERRED_SIZE, 500,
											GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_21.createSequentialGroup()
									.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addGap(58).addComponent(panel_30, GroupLayout.PREFERRED_SIZE, 500,
											GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_21.createSequentialGroup()
									.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addGap(58).addComponent(panel_31, GroupLayout.PREFERRED_SIZE, 500,
											GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			gl_panel_21.setVerticalGroup(gl_panel_21.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
					gl_panel_21.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE).addGap(1)
							.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_22, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_27, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_23, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_28, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_24, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_29, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_25, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_30, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panel_21.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_26, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_31, GroupLayout.PREFERRED_SIZE, 95,
											GroupLayout.PREFERRED_SIZE))));
			panel_21.setLayout(gl_panel_21);

			for (int i = 0; i < 10; i++) {
				topTitles.get(i).setText(topBooks.get(i).getTitle());
				topAuthors.get(i).setText(topBooks.get(i).getAuthor());
				topRatings.get(i).setText(topBooks.get(i).getPoint());
				topButtons.get(i).setIcon(topBooks.get(i).getIcon());
			}

			contentPane.setLayout(gl_contentPane);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
