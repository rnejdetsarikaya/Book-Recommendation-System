package com.bookProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DaoImpl.DaoImpl;
import model.Book;
import model.MouseHandler;
import model.User;
import javax.swing.JLabel;

public class ReadBook extends JFrame {

	static SingleObject obj;
	private JPanel contentPane;
	private JTextField RatingCount;
	private JTextField Rating;
	private JTextField ISBN;
	private JTextField YearOfPublication;
	private JTextField Publisher;
	private JTextField Author;
	private JTextField Title;
	private JTextField sliderValue;
	public String name = "";
	public List<Book> books;
	int i;
	static DaoImpl dao = new DaoImpl();
	private JTextField yourRating;
	JSlider slider;

	public ReadBook() {
		JButton buttonImage = new JButton("");
		MouseHandler.mouseListener(buttonImage);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				i = Integer.parseInt(name.substring(name.indexOf('_') + 1)) - 1;// kaçýncý butondan basýldýðý kontrolü
				Title.setText(books.get(i).getTitle());
				Author.setText(books.get(i).getAuthor());
				Publisher.setText(books.get(i).getPublisher());
				YearOfPublication.setText(books.get(i).getYear() + "");
				ISBN.setText(books.get(i).getISBN());
				Rating.setText(books.get(i).getPoint());
				RatingCount.setText(books.get(i).getRatingCount() + "");
				int rating = dao.getRating(User.getUser().getUserId(), books.get(i).getISBN());
				if (rating != -1)
					yourRating.setText(rating + "");
				else
					yourRating.setText("You have not rated this book");
				try {
					buttonImage.setIcon(new ImageIcon(new URL(books.get(i).getImageLURL())));
				} catch (Exception e2) {
					buttonImage.setIcon(new ImageIcon("C:\\Users\\NECOO\\Desktop\\BookProject\\null.png"));

				}

				/*
				 * if(name.substring(0,name.indexOf('_')).equals("top"))//hangi listelerdeki
				 * butonlarýn basýldýðý kontrolü { i=
				 * Integer.parseInt(name.substring(name.indexOf('_')+1))-1;//kaçýncý butondan
				 * basýldýðý kontrolü Title.setText(books.get(i).getTitle());
				 * Author.setText(books.get(i).getAuthor());
				 * Publisher.setText(books.get(i).getPublisher());
				 * YearOfPublication.setText(books.get(i).getYear()+"");
				 * ISBN.setText(books.get(i).getISBN());
				 * Rating.setText(books.get(i).getPoint());
				 * RatingCount.setText(books.get(i).getRatingCount()+""); try {
				 * buttonImage.setIcon(new ImageIcon(new URL(books.get(i).getImageLURL()))); }
				 * catch (Exception e2) { e2.printStackTrace(); } //getData(buttonImage);
				 * 
				 * } else if(name.substring(0,name.indexOf('_')).equals("list")) {
				 * getData(buttonImage); } else
				 * if(name.substring(0,name.indexOf('_')).equals("pop")) {
				 * 
				 * } else {
				 * 
				 * }
				 */
			}
			@Override
			public void windowClosing(WindowEvent e) {
			slider.setValue(5);
			sliderValue.setText(5+"");
			}
		});
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 580);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonImage.setBounds(12, 13, 334, 500);
		contentPane.add(buttonImage);

		RatingCount = new JTextField();
		RatingCount.setEditable(false);
		RatingCount.setBorder(null);
		RatingCount.setColumns(10);
		RatingCount.setBounds(442, 255, 220, 22);
		contentPane.add(RatingCount);

		Rating = new JTextField();
		Rating.setEditable(false);
		Rating.setBorder(null);
		Rating.setColumns(10);
		Rating.setBounds(442, 220, 220, 22);
		contentPane.add(Rating);

		ISBN = new JTextField();
		ISBN.setEditable(false);
		ISBN.setBorder(null);
		ISBN.setColumns(10);
		ISBN.setBounds(442, 185, 220, 22);
		contentPane.add(ISBN);

		YearOfPublication = new JTextField();
		YearOfPublication.setEditable(false);
		YearOfPublication.setBorder(null);
		YearOfPublication.setColumns(10);
		YearOfPublication.setBounds(442, 150, 220, 22);
		contentPane.add(YearOfPublication);

		Publisher = new JTextField();
		Publisher.setEditable(false);
		Publisher.setBorder(null);
		Publisher.setColumns(10);
		Publisher.setBounds(442, 115, 220, 22);
		contentPane.add(Publisher);

		Author = new JTextField();
		Author.setEditable(false);
		Author.setBorder(null);
		Author.setColumns(10);
		Author.setBounds(442, 80, 220, 22);
		contentPane.add(Author);

		Title = new JTextField();
		Title.setEditable(false);
		Title.setBorder(null);
		Title.setColumns(10);
		Title.setBounds(442, 45, 220, 22);
		contentPane.add(Title);

		yourRating = new JTextField();
		yourRating.setEditable(false);
		yourRating.setColumns(10);
		yourRating.setBorder(null);
		yourRating.setBounds(442, 290, 220, 22);
		contentPane.add(yourRating);

		slider = new JSlider();
		JButton button = new JButton("Rate Book");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.setRating(User.getUser().getUserId(), books.get(i).getISBN(), slider.getValue());
				yourRating.setText(dao.getRating(User.getUser().getUserId(), books.get(i).getISBN()) + "");
				String ISBN = books.get(i).getISBN();
				try {
					books.set(i,dao.getBooks(ISBN));//oy verme ile güncellenen kitap çaðýrýlýp listekiyle deðiþtirildi.
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Rating.setText(books.get(i).getPoint());
				RatingCount.setText(books.get(i).getRatingCount() + "");
			}
		});
		button.setBounds(396, 488, 220, 25);
		contentPane.add(button);

		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				sliderValue.setText(slider.getValue() + "");
			}
		});
		slider.setValue(5);
		slider.setMaximum(10);
		slider.setBounds(396, 449, 176, 26);
		contentPane.add(slider);

		sliderValue = new JTextField();
		sliderValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		sliderValue.setBorder(null);
		sliderValue.setText("5");
		sliderValue.setEditable(false);
		sliderValue.setColumns(10);
		sliderValue.setBounds(584, 442, 29, 33);
		contentPane.add(sliderValue);

		JLabel lblNewLabel = new JLabel("Title ");
		lblNewLabel.setBounds(349, 48, 81, 16);
		contentPane.add(lblNewLabel);

		JLabel lblAuthor = new JLabel("Author ");
		lblAuthor.setBounds(349, 83, 81, 16);
		contentPane.add(lblAuthor);

		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(349, 118, 81, 16);
		contentPane.add(lblPublisher);

		JLabel lblYearOfPublication = new JLabel("Year");
		lblYearOfPublication.setBounds(349, 153, 81, 16);
		contentPane.add(lblYearOfPublication);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(349, 188, 81, 16);
		contentPane.add(lblIsbn);

		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(349, 223, 81, 16);
		contentPane.add(lblRating);

		JLabel lblRatingCount = new JLabel("Rating Count");
		lblRatingCount.setBounds(349, 258, 81, 16);
		contentPane.add(lblRatingCount);

		JLabel lblYourRating = new JLabel("Your Rating");
		lblYourRating.setBounds(349, 293, 81, 16);
		contentPane.add(lblYourRating);

	}
}
