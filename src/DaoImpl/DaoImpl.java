package DaoImpl;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Book;
import model.User;

public class DaoImpl {

	Connection conn;
	mysql db = new mysql();
	Statement stmt;
	ResultSet rs;
	ResultSet rs1;
	PreparedStatement ps;
	Statement stmt1;
	ResultSet rs2;
	Statement stmt2;
	int UserId = 99999;
	int index = 0;
	int ratingCount;

	public DaoImpl() {
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(String pass, String userName, String loc, int age) throws Exception {
		conn = db.getConnection();
		ps = conn.prepareStatement(
				"INSERT INTO bxusers (Location,Age,Password,UserName,Role,Flag) VALUES (?,?,?,?,?,?)");
		ps.setObject(1, loc);
		ps.setObject(2, age);
		ps.setObject(3, pass);
		ps.setObject(4, userName);
		ps.setObject(5, 0);/// kayýt olan her yeni kullanýcý admin deðildir.
		ps.setObject(6, 0);// Yeni kayýt en az 10 kitap oylamalý
		ps.executeUpdate();
		ps.close();
		conn.close();
		System.out.println("signing up successful");
	}

	public Boolean isLogin(String userName, String pass) throws Exception {
		conn = db.getConnection();
		String query = "Select UserName,Password,UserId,Location,Age from bxusers where Username='" + userName
				+ "' and Password='" + pass + "'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			User user = User.getUser();
			user.setUserId(rs.getInt("UserId"));
			user.setUserName(rs.getString("Username"));
			user.setLoc(rs.getString("Location"));
			user.setAge(rs.getInt("Age"));
			rs.close();
			stmt.close();
			conn.close();
			return true;
		}
		rs.close();
		stmt.close();
		conn.close();
		return false;
	}

//	public void CreateTable() throws Exception {
//
//		//conn = db.getConnection();
//		PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS bxusers("
//				+ "UserId varchar(50) not null unique," + "Location varchar(50) not null," + "Age int not null,"
//				+ "Password varchar(50) not null," + "UserName varchar(50) not null unique," + "Role tinyint(1) not null,"
//				+ "Flag tinyint(1) not null," + "PRIMARY KEY(UserName,UserId))");
//		create.executeUpdate();
//		System.out.println("Tablolar oluþturuldu...");
//		
//		
//
//	}
//
//	public void DropTable() throws Exception {
//		//conn = db.getConnection();
//		PreparedStatement create = conn.prepareStatement("drop table if exists bxusers;");
//		create.executeUpdate();
//		System.out.println("Tablolar kaldýrýldý.");
//
//	}
	public Boolean isAdmin(String userName) throws Exception {
		conn = db.getConnection();
		String query = "Select Role from bxusers where UserName='" + userName + "'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			if (rs.getInt("Role") == 0) {
				rs.close();
				stmt.close();
				conn.close();
				return false;
			} else {
				rs.close();
				stmt.close();
				conn.close();
				return true;
			}

		} else {
			System.out.println("Böyle bir kullanýcý yok !!!!!!!999");
		}
		rs.close();
		stmt.close();
		conn.close();
		return false;
	}

	public Boolean isRated(String userName) throws Exception {
		conn = db.getConnection();
		String query = "Select Flag from bxusers where UserName='" + userName + "'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			if (rs.getInt("Flag") == 0) {
				rs.close();
				stmt.close();
				conn.close();
				return false;
			} else {
				rs.close();
				stmt.close();
				conn.close();
				return true;
			}

		} else {
			System.out.println("Böyle bir kullanýcý yok !!!!!!!999");
		}
		rs.close();
		stmt.close();
		conn.close();
		return false;
	}

	public void listBook(JTable table, int pageIndex) throws Exception {
		conn = db.getConnection();
		int a = 0;
		// String query="Select * from bxbooks group by bxbooks.Index limit 40 offset
		// "+pageIndex*40;
		String query = "Select * from bxbooks group by bxbooks.Index  having bxbooks.Index >" + pageIndex * 40
				+ " limit 40";
		stmt = conn.createStatement();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		if (table.getColumnModel().getColumnCount() > 8) /// Admin sayfasýndan listeleme ise url ler de dahil edildi.
		{
			String sql = "Select * from bxbooks where YearOfPublication=-1"; // database eklenmiþ boþ kitap var mý??
			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery(sql);
			while (rs1.next()) {
				a++;
				index = rs1.getInt("Index");
			}
			if (a == 0) { /// eklenmiþ boþ kitap yoksa ekle
				ps = conn.prepareStatement(
						"INSERT INTO bxbooks (ISBN,BookTitle,BookAuthor,YearOfPublication,Publisher,ImageURLS,ImageURLM,ImageURLL) VALUES (?,?,?,?,?,?,?,?)");
				ps.setString(1, null);
				ps.setString(2, null);
				ps.setString(3, null);
				ps.setInt(4, -1);
				ps.setString(5, null);
				ps.setString(6, null);
				ps.setString(7, null);
				ps.setString(8, null);
				ps.executeUpdate();
				sql = "Select * from bxbooks where YearOfPublication=-1";
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sql);
				while (rs2.next()) {
					model.addRow(new Object[] { null, null, null, null, null, null, null, null, rs2.getInt("Index") });
					// eklenilen boþ kitabýn bir kopyasýný düzenlemek için baþa getir.
				}

			} else {
				model.addRow(new Object[] { null, null, null, null, null, null, null, null, index });
				// eklenilen boþ kitabýn bir kopyasýný düzenlemek için baþa getir.
			}
			rs = stmt.executeQuery(query);
			while (rs.next()) {// diðer k
				if (rs.getInt("YearOfPublication") != -1) {
					model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
							rs.getString("BookAuthor"), rs.getInt("YearOfPublication"), rs.getString("Publisher"),
							rs.getString("ImageURLS"), rs.getString("ImageURLM"), rs.getString("ImageURLL"),
							rs.getInt("Index") });
				}

			}
		} else {
			rs = stmt.executeQuery(query);
			User user = User.getUser();
			while (rs.next()) {
				String query1 = "SELECT BookRating,count(*) FROM test1.bxbookratings where UserID=" + user.getUserId()
						+ " and ISBN='" + rs.getString("ISBN") + "'";
				stmt = conn.createStatement();
				rs1 = stmt.executeQuery(query1);
				while (rs1.next()) {
					if (rs1.getInt(1) > 0) {
						model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
								rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
								rs.getString("Publisher"), rs.getInt("Index"), rs1.getInt("BookRating") });
					} else {
						model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
								rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
								rs.getString("Publisher"), rs.getInt("Index"), 0 });
					}

				}

			}
		}
		rs.close();
		stmt.close();
	}

	public void searchBookTitle(JTable table, String search) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		int a = 0;
		String query;
		try {
			conn = db.getConnection();
			if (table.getColumnModel().getColumnCount() > 8) {// admin sayfasý
				query = "SELECT * FROM test1.bxbooks where BookTitle  like '%" + search + "%'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					a = 1;
					model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
							rs.getString("BookAuthor"), rs.getString("YearOfPublication"), rs.getString("Publisher"),
							rs.getString("ImageURLS"), rs.getString("ImageURLM"), rs.getString("ImageURLL"),
							rs.getInt("Index") });
				}
				if (a == 0) {
					JOptionPane.showMessageDialog(null, "Arama yaptýðýnýz isimde bir kitap bulunamadý!!");
				}
			} else {
				query = "SELECT * FROM test1.bxbooks  where BookTitle  like '%" + search + "%'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					a = 1;
					String query1 = "SELECT BookRating,count(*) FROM test1.bxbookratings where UserID="
							+ User.getUser().getUserId() + " and ISBN='" + rs.getString("ISBN") + "'";
					stmt = conn.createStatement();
					rs1 = stmt.executeQuery(query1);
					while (rs1.next()) {
						if (rs1.getInt(1) > 0) {
							model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
									rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
									rs.getString("Publisher"), rs.getInt("Index"), rs1.getInt("BookRating") });
						} else {
							model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
									rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
									rs.getString("Publisher"), rs.getInt("Index"), 0 });
						}

					}
				}
				if (a == 0) {
					JOptionPane.showMessageDialog(null, "Arama yaptýðýnýz isimde bir kitap bulunamadý!!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchBookAuthor(JTable table, String search) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		int a = 0;
		String query;
		try {
			conn = db.getConnection();
			if (table.getColumnModel().getColumnCount() > 8) {// admin sayfasý
				query = "SELECT * FROM test1.bxbooks where BookAuthor  like '%" + search + "%'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					a = 1;
					model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
							rs.getString("BookAuthor"), rs.getString("YearOfPublication"), rs.getString("Publisher"),
							rs.getString("ImageURLS"), rs.getString("ImageURLM"), rs.getString("ImageURLL"),
							rs.getInt("Index") });
				}
				if (a == 0) {
					JOptionPane.showMessageDialog(null, "Arama yaptýðýnýz isimde bir yazar bulunamadý!!");
				}
			} else {
				query = "SELECT * FROM test1.bxbooks  where BookAuthor  like '%" + search + "%'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					a = 1;
					String query1 = "SELECT BookRating,count(*) FROM test1.bxbookratings where UserID="
							+ User.getUser().getUserId() + " and ISBN='" + rs.getString("ISBN") + "'";
					stmt = conn.createStatement();
					rs1 = stmt.executeQuery(query1);
					while (rs1.next()) {
						if (rs1.getInt(1) > 0) {
							model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
									rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
									rs.getString("Publisher"), rs.getInt("Index"), rs1.getInt("BookRating") });
						} else {
							model.addRow(new Object[] { rs.getString("ISBN"), rs.getString("BookTitle"),
									rs.getString("BookAuthor"), rs.getString("YearOfPublication"),
									rs.getString("Publisher"), rs.getInt("Index"), 0 });
						}

					}
				}
				if (a == 0) {
					JOptionPane.showMessageDialog(null, "Arama yaptýðýnýz isimde bir yazar bulunamadý!!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchUser(JTable table, String search) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		int a = 0;
		try {
			conn = db.getConnection();
			String query = "SELECT * FROM test1.bxusers where UserName  like '%" + search + "%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				a = 1;
				model.addRow(new Object[] { rs.getString("UserID"), rs.getString("UserName"), rs.getString("Location"),
						rs.getString("Age"), rs.getInt("Role"), rs.getInt("Flag") });
			}
			if (a == 0) {
				JOptionPane.showMessageDialog(null, "Arama yaptýðýnýz isimde bir kullancý bulunamadý!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listUser(JTable table, int pageIndex) throws Exception {
		conn = db.getConnection();
		// String query="Select * from bxusers group by bxusers.UserId limit 50 offset
		// "+pageIndex*50;
		String query = "Select * from bxusers group by bxusers.UserId  having UserId >" + pageIndex * 50 + " limit 50";
		// String query = "Select * from bxusers";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		while (rs.next()) {
			model.addRow(new Object[] { rs.getString("UserID"), rs.getString("UserName"), rs.getString("Location"),
					rs.getString("Age"), rs.getInt("Role"), rs.getInt("Flag") });
		}
		rs.close();
		stmt.close();
	}

	public List<Book> getBooks(int pageIndex) throws Exception {
		conn = db.getConnection();
		String query = "SELECT * FROM test1.bxbooks limit 6 offset " + pageIndex * 6;
		// String query="SELECT * FROM test1.bxbooks where bxbooks.Index>"+pageIndex*6+"
		// limit 6";
		// String query = "Select * from bxbooks group by bxbooks.Index having
		// bxbooks.Index >" + pageIndex * 6
		// + " limit 6";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		List<Book> books = new ArrayList<Book>();

		while (rs.next()) {
			ImageIcon icon;
			try {
				icon = new ImageIcon(new URL(rs.getString("ImageURLM")));
			} catch (Exception e) {
				icon = new ImageIcon("null.png");
			}
			try {
				Book book = new Book(icon, rs.getString("BookTitle"), rs.getString("BookAuthor"), rs.getString("ISBN"),
						rs.getString("Publisher"), rs.getInt("YearOfPublication"), "");
				book.setImageLURL(rs.getString("ImageURLL"));
				book.setPoint(getRating(rs.getString("ISBN")) + "");
				book.setRatingCount(getRatingCount(rs.getString("ISBN")));
//				book.setRatingCount(ratingCount);
				books.add(book);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		while (books.size() != 6) {
			Book book = new Book(new ImageIcon("null.png"), "", "", "", "", -1, "");
			books.add(book);
		}
		return books;
	}

	public void updateBook(String ISBN, String title, String author, int year, String publisher, String imageS,
			String imageM, String imageL, int index, int chose) {
		try {
			conn = db.getConnection();
			if (this.index != index) {
				chose = -1;
			}
			if (chose == 0) {
				ps = conn.prepareStatement(
						"INSERT INTO test1.bxlastfive (ISBN,BookTitle,BookAuthor,YearOfPublication,Publisher,ImageURLS,ImageURLM,ImageURLL,bxlastfive.Index) "
								+ "VALUES (?,?,?,?,?,?,?,?,?)");
				ps.setString(1, ISBN);
				ps.setString(2, title);
				ps.setString(3, author);
				ps.setInt(4, year);
				ps.setString(5, publisher);
				ps.setString(6, imageS);
				ps.setString(7, imageM);
				ps.setString(8, imageL);
				ps.setInt(9, index);
				ps.executeUpdate();

				String query = "Select count(*) from bxlastfive";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				if (rs.next()) {
					int count = rs.getInt(1);
					if (count > 5) {// 5ten fazla eklenmiþ kitap varsa en küçük indexli kitabý sil
						query = "Select min(bxlastfive.Index) as minIndex from bxlastfive";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							deleteLastFiveBook(rs.getInt("minIndex"));
						}
					}
				}

			}
			ps = conn.prepareStatement("UPDATE test1.bxlastfive SET "
					+ "ISBN=?,BookTitle=?,BookAuthor=?,YearOfPublication=?,Publisher=?,ImageURLS=?,ImageURLM=?,ImageURLL=? WHERE bxlastfive.Index=?");
			ps.setString(1, ISBN);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setInt(4, year);
			ps.setString(5, publisher);
			ps.setString(6, imageS);
			ps.setString(7, imageM);
			ps.setString(8, imageL);
			ps.setInt(9, index);
			ps.executeUpdate();
			//////////////////////////////////////////////
			ps = conn.prepareStatement("UPDATE test1.bxbooks SET "
					+ "ISBN=?,BookTitle=?,BookAuthor=?,YearOfPublication=?,Publisher=?,ImageURLS=?,ImageURLM=?,ImageURLL=? WHERE bxbooks.Index=?");
			ps.setString(1, ISBN);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setInt(4, year);
			ps.setString(5, publisher);
			ps.setString(6, imageS);
			ps.setString(7, imageM);
			ps.setString(8, imageL);
			ps.setInt(9, index);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateUser(int UserID, String UserName, String Location, String Age, int Role, int Flag) {
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(
					"UPDATE test1.bxusers SET " + "UserName=?,Location=?,Age=?,Role=?,Flag=? where bxusers.UserId=?");
			ps.setString(1, UserName);
			ps.setString(2, Location);
			ps.setString(3, Age);
			ps.setInt(4, Role);
			ps.setInt(5, Flag);
			ps.setInt(6, UserID);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUser(int UserID) {
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement("Delete from test1.bxusers where UserId=" + UserID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(int index) {
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement("DELETE FROM bxbooks WHERE bxbooks.Index=?");
			ps.setInt(1, index);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteLastFiveBook(int index) {
		try {
//			conn = db.getConnection();
			ps = conn.prepareStatement("DELETE FROM bxlastfive WHERE bxlastfive.Index=?");
			ps.setInt(1, index);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String ISBN) {
		try {
//			conn = db.getConnection();
			ps = conn.prepareStatement("DELETE FROM bxratings WHERE ISBN=?");
			ps.setString(1, ISBN);
			ps.executeUpdate();
			ps = conn.prepareStatement("DELETE FROM bxbookratings WHERE ISBN=?");
			ps.setString(1, ISBN);
			ps.executeUpdate();
			System.out.println("Kitap yoktu isbn silindi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCount(String model, int pageElementCount) {
		int count = 0;
		String query;
		try {
//			conn = db.getConnection();
			if (model.equals("Book")) {
				query = "Select count(*) from bxbooks";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count / pageElementCount;
			} else if (model.equals("User")) {
				query = "Select count(*) from bxusers";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count / pageElementCount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Book getBooks(String ISBN) throws Exception {
		int flag = 0;
		String query = "SELECT * FROM test1.bxbooks where ISBN like '" + ISBN + "'";
//		conn = db.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		Book book = null;
		while (rs.next()) {
			flag = 1;
			try {

				book = new Book(new ImageIcon(new URL(rs.getString("ImageURLS"))), rs.getString("BookTitle"),
						rs.getString("BookAuthor"), rs.getString("ISBN"), rs.getString("Publisher"),
						rs.getInt("YearOfPublication"), getRating(rs.getString("ISBN")) + "");
				book.setRatingCount(getRatingCount(rs.getString("ISBN")));
				book.setImageLURL(rs.getString("ImageURLL"));
			} catch (Exception e) {
				book = new Book(new ImageIcon("null.png"), rs.getString("BookTitle"), rs.getString("BookAuthor"),
						rs.getString("ISBN"), rs.getString("Publisher"), rs.getInt("YearOfPublication"), "");
			}
		}
		if (flag == 1)
			return book;
		else {
			delete(ISBN);
			book = new Book(new ImageIcon("null.png"), "Böyle Bir Kitap Yok", "", "", "", -1, "");
			return book;
		}
	}

	public List<Book> listPop10() {
		Book book = null;
		List<Book> books = new ArrayList<>();
		try {
			String query = "SELECT * FROM test1.bxratings order by bxratings.Count desc limit 10;";
			Connection conn1 = db.getConnection();
			Statement stmt1 = conn1.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query);
			while (rs1.next()) {
				book = getBooks(rs1.getString("ISBN"));
				book.setPoint(rs1.getString("SumPoint"));
				book.setRatingCount(rs1.getInt("Count"));
				books.add(book);
			}

			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			books.add(new Book(new ImageIcon("null.png"), "", "", "", "", -1, ""));
		}

		return books;
	}

	public List<Book> listTop10() {
		Book book = null;
		String point;
		List<Book> books = new ArrayList<>();
		try {
			String query = "SELECT * FROM test1.bxratings order by SumPoint desc limit 10;";
			Connection conn1 = db.getConnection();
			Statement stmt1 = conn1.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query);
			while (rs1.next()) {
				point = rs1.getString("SumPoint");
				book = getBooks(rs1.getString("ISBN"));
				book.setPoint(rs1.getString("SumPoint"));
				book.setRatingCount(rs1.getInt("Count"));
				books.add(book);
			}

			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			books.add(new Book(new ImageIcon("null.png"), "", "", "", "", -1, ""));
		}

		return books;
	}

	public List<Book> getPredictedBooks(String books) throws Exception {
		String query = "Select * from bxbooks where ISBN in ( " + books + ") order by field(ISBN," + books + ");";
		List<Book> predictedBooks = new ArrayList<>();
		Book book = null;
		try {
			rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				// System.out.println(new ImageIcon(new URL(rs.getString("ImageURLL"))));
				book = new Book(new ImageIcon(new URL(rs.getString("ImageURLL"))), rs.getString("BookTitle"),
						rs.getString("BookAuthor"), rs.getString("ISBN"), rs.getString("Publisher"),
						rs.getInt("YearOfPublication"), getRating(rs.getString("ISBN")) + "");
				book.setRatingCount(getRatingCount(rs.getString("ISBN")));
				predictedBooks.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			book = new Book(new ImageIcon("null.png"), rs.getString("BookTitle"), rs.getString("BookAuthor"),
					rs.getString("ISBN"), rs.getString("Publisher"), rs.getInt("YearOfPublication"),
					getRating(rs.getString("ISBN")) + "");
			book.setRatingCount(getRatingCount(rs.getString("ISBN")));
			predictedBooks.add(book);
		}

		return predictedBooks;
	}

	public void getMyBookandOtherUsers() {
		if (User.getUser().getUserId() != 0) {
			try {
				String myBooks = "(";
				User user = User.getUser();
				String query = "Select ISBN,BookRating from bxbookratings where UserId=" + user.getUserId();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				HashMap<String, Double> mapBook = new HashMap<>();

				while (rs.next()) {
					myBooks += "'" + rs.getString("ISBN") + "',";
					mapBook.put(rs.getString("ISBN"), rs.getDouble("BookRating"));
				}
				user.setBookMap(mapBook);
				myBooks = myBooks.substring(0, myBooks.length() - 1);
				myBooks += ")";
				HashMap<Integer, HashMap<String, Double>> mapUsers = new HashMap<>();
				query = "Select * from test1.bxbookratings  where UserID  in (Select UserID from bxbookratings where ISBN in "
						+ myBooks + " and UserID != " + user.getUserId() + " group by UserID)";
				// System.out.println(query);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					if (mapUsers.containsKey(rs.getInt("UserID"))) {// mapte kullanýcý varsa
						mapUsers.get(rs.getInt("UserID")).put(rs.getString("ISBN"), rs.getDouble("BookRating"));
					} else {
						mapUsers.put(rs.getInt("UserID"), new HashMap<String, Double>());
						mapUsers.get(rs.getInt("UserID")).put(rs.getString("ISBN"), rs.getDouble("BookRating"));
					}
				}
				user.setUserMap(mapUsers);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setRating(int UserId, String ISBN, int point) {
		int flag = 0;
		int rating = -1;
		try {
			String query = "Select UserID,BookRating from bxbookratings where UserID=" + UserId + " and ISBN='" + ISBN
					+ "'";// bu user ýd bu ýsbn ye daha önce puan verdiyse
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				flag = 1;
				rating = rs.getInt("BookRating");
				if (rating != point) {
					ps = conn.prepareStatement("UPDATE bxbookratings SET BookRating=? WHERE UserID=? and ISBN=?");
					ps.setInt(1, point);
					ps.setInt(2, UserId);
					ps.setString(3, ISBN);
					ps.executeUpdate();
				}
			} else {
				ps = conn.prepareStatement("INSERT INTO test1.bxbookratings (UserID,ISBN,BookRating) VALUES (?,?,?)");
				ps.setInt(1, UserId);
				ps.setString(2, ISBN);
				ps.setInt(3, point);
				ps.executeUpdate();
			}
			if (rating != point) {
				query = "Select * from bxratings where ISBN='" + ISBN + "'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				if (rs.next()) {
					double p = rs.getDouble("SumPoint");
					int count = rs.getInt("Count");
					ps = conn.prepareStatement("UPDATE bxratings SET Sumpoint=?,Count=? WHERE ISBN=?");
					if (flag == 1) {// daha önce oy verilmiþte countu arttýrma yalnýzca oraný düzenle
						ps.setDouble(1, ((p * count) + point - rating) / (count));
						ps.setInt(2, count);
						ps.setString(3, ISBN);
						ps.executeUpdate();
					} else {
						ps.setDouble(1, ((p * count) + point - rating) / (count + 1));
						ps.setInt(2, count + 1);
						ps.setString(3, ISBN);
						ps.executeUpdate();
					}
				} else {
					ps = conn.prepareStatement("INSERT INTO bxratings (ISBN,SumPoint,Count) VALUES (?,?,?)");
					ps.setString(1, ISBN);
					ps.setInt(2, point);
					ps.setInt(3, 1);
					ps.executeUpdate();
				}
			}
			System.out.println("Rating successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getRating(String ISBN) {

		String query = "select SumPoint from test1.bxratings where ISBN ='" + ISBN + "'";

		try {
//			conn = db.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(query);
			if (rs2.next()) {
				return rs2.getDouble("SumPoint");
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	public int getRating(int UserID, String ISBN) {
		String query = "Select BookRating from test1.bxbookratings where UserID=" + UserID + " and ISBN='" + ISBN + "'";

		try {
			// conn = db.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(query);
			if (rs2.next()) {
				return rs2.getInt("BookRating");
			} else
				return -1;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return -1;
	}

	public int getRatingCount(String ISBN) {

		String query = "select bxratings.Count from test1.bxratings where ISBN ='" + ISBN + "'";

		try {
//			conn = db.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(query);
			if (rs2.next()) {
				return rs2.getInt("Count");
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	public int getRatingCount(int UserId) {
		try {
			String query = "Select count(UserID) from bxbookratings where UserID=" + UserId;
//			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Ltfen en az 10 farklý kitabý oylayýnýz!!");
		return -1;
	}

	public void ratingComplete(int UserId) {
		try {
//			conn = db.getConnection();
			ps = conn.prepareStatement("UPDATE bxusers SET Flag=1 WHERE UserId=?");
			ps.setInt(1, UserId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getLastFiveBooks() {
		List<Book> books = new ArrayList<>();
		Connection conn1;
		Statement stmt1;
		ResultSet rs1;
		try {
			conn1 = db.getConnection();
			String query = "Select * from bxlastfive";
			stmt1 = conn1.createStatement();
			rs1 = stmt1.executeQuery(query);

			while (rs1.next()) {
				Book book = new Book(new ImageIcon(rs1.getString("imageURLM")), rs1.getString("BookTitle"),
						rs1.getString("BookAuthor"), rs1.getString("ISBN"), rs1.getString("Publisher"),
						rs1.getInt("YearOfPublication"), getRating(rs1.getString("ISBN")) + "");

				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
}
