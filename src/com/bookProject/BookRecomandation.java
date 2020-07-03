package com.bookProject;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DaoImpl.DaoImpl;
import model.Book;
import model.User;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookRecomandation extends JFrame {

	private JPanel contentPane;
	static DaoImpl dao = new DaoImpl();
	User user = User.getUser();
	double a = 0;
	double sumNeighbourhood = 0;
	double sumOfISBNs;
	String isbn;
	double temp;
	double result = 0;
	int index = 0;
	List<Book> predictedBooks10;
	private JTextField RatingCount;
	private JTextField Rating;
	private JTextField ISBN;
	private JTextField Year;
	private JTextField Publisher;
	private JTextField Author;
	private JTextField Title;
	private JTextField YourRating;
	private JTextField sliderValue;
	private JTextField indexField;
	JSlider slider;
	JButton image;
	ArrayList<String> ISBNs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRecomandation frame = new BookRecomandation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public double findSubtrahend(int userId, HashMap<Integer, Double> factorMap) {

		//
		for (Map.Entry<Integer, Double> factorEntry : factorMap.entrySet()) {
			if (factorEntry.getKey() == userId) {
				return factorEntry.getValue();
			}
		}
		return 999;
	}

	public void calculateSimilarity(HashMap<Integer, Double> factorMap,
			HashMap<Integer, HashMap<String, Double>> userMap) {

		/*
		 * Her kullanýcnýn kendi pearson korelasyon katsayýsýný kendi puanlarýndan
		 * cikardik. Giris yapan kullanýcý dahil
		 *
		 */
		for (Map.Entry<Integer, HashMap<String, Double>> userEntry1 : userMap.entrySet()) {
			for (Map.Entry<String, Double> bookEntry : userEntry1.getValue().entrySet()) {
				bookEntry.setValue(bookEntry.getValue() - findSubtrahend(userEntry1.getKey(), factorMap));
			}
		}

		// cosinus benzerligininde kullancagimiz kendi puanlarýmýzý toplamýný hesapladýk
		user.getBookMap().forEach((myISBN, myRating) -> {
			a += myRating * myRating;
		});

		userMap.forEach((UserID, bookMap) -> {

			bookMap.forEach((isbn, rating) -> {

				// if (UserID == user.getUserId())
				// System.out.println("key = " + UserID + " isbn = " + isbn + " rating = " +
				// rating);

			});

		});

		double sim = 0;
		double b = 0;
		double v = 0;

		// b yukarýsý --> ortak oylanan kitaplarin puanlari carpimi toplami
		// a alt sol v alt sað
		// a giris yapan kullanicinin puanlarý toplamýnýn karekökü
		// v benzerliðine baktigimiz kullanicin puanlarý toplamýnýn karekökü
		// sim = b/(kök a + kök v)
		HashMap<Integer, Double> similarity = new HashMap<>();

		for (Map.Entry<Integer, HashMap<String, Double>> userEntry : userMap.entrySet()) {
			HashMap<String, Double> books = userEntry.getValue(); // userMapin icindeki her bir userin oyladigi kitaplar

			if (userEntry.getKey() != user.getUserId()) {
				for (Map.Entry<String, Double> book : books.entrySet()) {
					if (user.getBookMap().containsKey(book.getKey())) {
						b += user.getBookMap().get(book.getKey()) * book.getValue();
					}
					v += book.getValue() * book.getValue();
				}
				sim = b / (Math.sqrt(a) * Math.sqrt(v));
				similarity.put(userEntry.getKey(), sim);
			}
			b = 0;
			v = 0;
			sim = 0;
		}

		user.setSimilarty(similarity);

	}

	public ArrayList<String> ratingPredicate(HashMap<Integer, Double> sim) {

		HashMap<String, Double> sums = new HashMap<>();
		HashMap<Integer, Double> neighbours = new HashMap<>();
		Map<Double, String> predicatedValue = new TreeMap<>();
		ArrayList<String> PredictedISBNs = new ArrayList<>();
		// Komsularý bulup treesete attýk bu sayede benzerlige gore sirali gelecek
		/*
		 * benzerlikleri key olarak aldigimiz icin keyler tekrar etmesin diye ayný keye
		 * denk gelirse ufak bir miktar arttýrdýk. miktar cok kucuk oldugu icin hata
		 * oraný oldukca az arttý.
		 */

		sim.forEach((ID, Rating) -> {
			if (Rating > 0) {//esik deger
				neighbours.put(ID, Rating);

			}
		});

		// giris yapan kullanicinin ortalamasi
		a = 0;
		user.getBookMap().forEach((ISBN, Rating) -> {
			a += Rating;
		});

		a /= user.getBookMap().size();

		HashMap<String, Double> sumISBN = new HashMap<>();

		/*
		 * komþulukta bulunan kullanýcýlar için ilgili isbnlerin puanýný topladik -->
		 * onceden her kullanýcý icin kendi ortalamasýný verdigi puandan cikarmistik.
		 */
		temp = 0;
		user.getUserMap().forEach((ID, Books) -> {
			if (neighbours.containsKey(ID)) {
				Books.forEach((ISBN, Rating) -> {
					if (sumISBN.containsKey(ISBN)) {
						temp = sumISBN.get(ISBN) * neighbours.get(ID);
						sumISBN.put(ISBN, temp + Rating);
					} else {
						sumISBN.put(ISBN, Rating * neighbours.get(ID));
					}
				});
			}
		});

		sumNeighbourhood = 0;

		neighbours.forEach((Rating, ID) -> {
			sumNeighbourhood += Rating;
		});

		sumOfISBNs = 0;

		user.setPredicate(new TreeMap<>());
		sumISBN.forEach((ISBN, Rating) -> {
			result = (Rating / sumNeighbourhood) + a;
			if (user.getPredicate().containsKey(result)) {
				result += 0.0000000000000001;
				user.getPredicate().put(result, ISBN);
			} else {
				user.getPredicate().put(result, ISBN);
			}

		});

		user.getPredicate().forEach((Rating, ISBN) -> {
			PredictedISBNs.add(ISBN);
		});

		return PredictedISBNs;

	}

	/**
	 * Create the frame.
	 */

	// Pearson katsayisini hesapla
	public HashMap<Integer, Double> calculateCoefficient() {
		HashMap<Integer, Double> userFactor = new HashMap<>(); // her bir kullanicinin cikarma isleminde kullanilacak
																// katsayýsý
		double factor = 0;

		for (Map.Entry<String, Double> entry : user.getBookMap().entrySet()) {
			factor += entry.getValue();
		}

		factor /= user.getBookMap().size();

		HashMap<Integer, HashMap<String, Double>> userMap = user.getUserMap();

		userFactor.put(user.getUserId(), factor);
		userMap.put(user.getUserId(), user.getBookMap());

		factor = 0;

		int size = 0;
		int key = 0;

		for (Map.Entry<Integer, HashMap<String, Double>> entry1 : userMap.entrySet()) {
			HashMap<String, Double> m1 = entry1.getValue();

			size = m1.size();
			for (Map.Entry<String, Double> entry : m1.entrySet()) {
				// System.out.println("UserId: " + entry1.getKey() + " ISBN: " + entry.getKey()
				// + " " + " Rating: "+ entry.getValue());
				factor += entry.getValue();
				key = entry1.getKey();
			}
			factor /= size;
			userFactor.put(key, factor);
		}

		return userFactor;
	}

	public void getData(ArrayList<String> predictedBooks, int index) {
		System.out.println(predictedBooks.size());
		String books = "";
		System.out.println(predictedBooks);
		for (int i = index; i < index + 10; i++) {
			books += "'" + predictedBooks.get(predictedBooks.size() - i - 1) + "',";
		}
		System.out.println(books);
		books = books.substring(0, books.length() - 1);
		try {
			predictedBooks10 = dao.getPredictedBooks(books);
			System.out.println("new books are loaded" + books);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listBook(int index) {
		
		try {
			image.setIcon(predictedBooks10.get(index).getIcon());
			Title.setText(predictedBooks10.get(index).getTitle());
			Author.setText(predictedBooks10.get(index).getAuthor());
			Publisher.setText(predictedBooks10.get(index).getPublisher());
			Year.setText(predictedBooks10.get(index).getYear() + "");
			ISBN.setText(predictedBooks10.get(index).getISBN());
			Rating.setText(predictedBooks10.get(index).getPoint());
			RatingCount.setText(predictedBooks10.get(index).getRatingCount() + "");			
			int rating = dao.getRating(User.getUser().getUserId(), predictedBooks10.get(index).getISBN());
			if (rating == -1) {
				YourRating.setText("You have not rated this book");
			} else
				YourRating.setText(dao.getRating(User.getUser().getUserId(), predictedBooks10.get(index).getISBN()) + "");
		} catch (Exception e) {
			getData(ISBNs, 10-index%10+index);
		}
	}

	public BookRecomandation() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				slider.setValue(5);
				sliderValue.setText(5+"");
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		image = new JButton("");
		image.setBounds(12, 13, 334, 500);
		contentPane.add(image);

		RatingCount = new JTextField();
		RatingCount.setEditable(false);
		RatingCount.setColumns(10);
		RatingCount.setBorder(null);
		RatingCount.setBounds(442, 255, 220, 22);
		contentPane.add(RatingCount);

		Rating = new JTextField();
		Rating.setEditable(false);
		Rating.setColumns(10);
		Rating.setBorder(null);
		Rating.setBounds(442, 220, 220, 22);
		contentPane.add(Rating);

		ISBN = new JTextField();
		ISBN.setEditable(false);
		ISBN.setColumns(10);
		ISBN.setBorder(null);
		ISBN.setBounds(442, 185, 220, 22);
		contentPane.add(ISBN);

		Year = new JTextField();
		Year.setEditable(false);
		Year.setColumns(10);
		Year.setBorder(null);
		Year.setBounds(442, 150, 220, 22);
		contentPane.add(Year);

		Publisher = new JTextField();
		Publisher.setEditable(false);
		Publisher.setColumns(10);
		Publisher.setBorder(null);
		Publisher.setBounds(442, 115, 220, 22);
		contentPane.add(Publisher);

		Author = new JTextField();
		Author.setEditable(false);
		Author.setColumns(10);
		Author.setBorder(null);
		Author.setBounds(442, 80, 220, 22);
		contentPane.add(Author);

		Title = new JTextField();
		Title.setEditable(false);
		Title.setColumns(10);
		Title.setBorder(null);
		Title.setBounds(442, 45, 220, 22);
		contentPane.add(Title);

		YourRating = new JTextField();
		YourRating.setEditable(false);
		YourRating.setColumns(10);
		YourRating.setBorder(null);
		YourRating.setBounds(442, 290, 220, 22);
		contentPane.add(YourRating);

		slider = new JSlider();
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				sliderValue.setText(slider.getValue()+"");
			}
		});
		slider.setValue(5);
		slider.setMaximum(10);
		slider.setBounds(396, 449, 176, 26);
		contentPane.add(slider);
		JButton button_1 = new JButton("Rate Book");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.setRating(User.getUser().getUserId(), predictedBooks10.get(index).getISBN(), slider.getValue());
				String ISBN = predictedBooks10.get(index).getISBN();
				try {
					predictedBooks10.set(index,dao.getBooks(ISBN));//oy verme ile güncellenen kitap çaðýrýlýp listekiyle deðiþtirildi.
					listBook(index%10);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(396, 488, 220, 25);
		contentPane.add(button_1);


		sliderValue = new JTextField();
		sliderValue.setText("5");
		sliderValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		sliderValue.setEditable(false);
		sliderValue.setColumns(10);
		sliderValue.setBorder(null);
		sliderValue.setBounds(584, 442, 29, 33);
		contentPane.add(sliderValue);

		JLabel label = new JLabel("Title ");
		label.setBounds(349, 48, 81, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Author ");
		label_1.setBounds(349, 83, 81, 16);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Publisher");
		label_2.setBounds(349, 118, 81, 16);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Year");
		label_3.setBounds(349, 153, 81, 16);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("ISBN");
		label_4.setBounds(349, 188, 81, 16);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("Rating");
		label_5.setBounds(349, 223, 81, 16);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("Rating Count");
		label_6.setBounds(349, 258, 81, 16);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("Your Rating");
		label_7.setBounds(349, 293, 81, 16);
		contentPane.add(label_7);

		indexField = new JTextField();
		indexField.setEditable(false);
		indexField.setText("1");
		indexField.setColumns(10);
		indexField.setBackground(SystemColor.menu);
		indexField.setBounds(493, 388, 29, 22);
		contentPane.add(indexField);

		dao.getMyBookandOtherUsers();

		HashMap<Integer, Double> userFactor = calculateCoefficient();
		calculateSimilarity(userFactor, user.getUserMap());
		ISBNs = ratingPredicate(user.getSimilarity());

		JButton button_3 = new JButton("Back");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					if (index % 10 == 9) {
						getData(ISBNs, index - 9);
					}
					listBook(index % 10);
					indexField.setText(index + 1 + "");
				}
			}
		});
		button_3.setBounds(396, 387, 73, 25);
		contentPane.add(button_3);

		JButton button_2 = new JButton("Next");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ISBNs.size() > index) {
					index++;
					if (index % 10 == 0) {
						getData(ISBNs, index);
					}
					listBook(index % 10);
					indexField.setText(index + 1 + "");
				}
			}
		});
		button_2.setBounds(545, 387, 71, 25);
		contentPane.add(button_2);
		getData(ISBNs, index);
		listBook(index);
		indexField.setText(index + 1 + "");
		System.out.println("buradayim");
	}
}
