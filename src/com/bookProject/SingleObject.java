package com.bookProject;

import java.util.stream.Collector;

import javax.crypto.spec.GCMParameterSpec;

import model.LoadingScreen;

public class SingleObject {

	private static SingleObject instance = new SingleObject();
	private LoginFrame frame = new LoginFrame();
	private Register reg = new Register();
	private BookRating rating;// = new BookRating();
	private AdminPanel adminPanel;// = new AdminPanel();
	private ListBook listBook;// = new ListBook();
	private ReadBook readBook = new ReadBook();
	private BookRecomandation bookRec;
	private LoadingScreen loading= new LoadingScreen();

	

	private boolean bookRecFlag = false;
	private boolean listBookFlag = false;
	private boolean bookRatingFlag = false;
	private boolean adminPanelFlag = false;
	private boolean loadingFlag=false;

	private SingleObject() {

	}

	public LoadingScreen getLoading() {
		if (!loadingFlag) {
			loadingFlag = true;
			loading = new LoadingScreen();
		}
		return loading;
	}
	
	public static SingleObject getSingleObject() {
		return instance;
	}

	public LoginFrame getLogin() {
		return frame;
	}

	public Register getRegister() {
		return reg;
	}

	public BookRating getRating() {
		if (!bookRatingFlag) {
			bookRatingFlag = true;
			rating = new BookRating();
		}
		return rating;
	}

	public AdminPanel getAdminPanel() {
		if(!adminPanelFlag) {
			adminPanel = new AdminPanel();
			adminPanelFlag = false;
		}
		return adminPanel;
	}

	public ListBook getListBook() {
		if (!listBookFlag) {
			listBookFlag = true;
			listBook = new ListBook();
		}
		return listBook;
	}

	public ReadBook getReadBook() {
		return readBook;
	}

	public BookRecomandation getBookRecomandation() {
		if (!bookRecFlag) {
			bookRecFlag = true;
			bookRec = new BookRecomandation();
		}
		return bookRec;
	}

	public void disposeBookRating() {
		if (bookRatingFlag) {
			rating.dispose();
			bookRatingFlag = false;
		}
	}
	
	public void disposeAdminPanel() {
		if(adminPanelFlag) {
			adminPanel.dispose();
			adminPanelFlag = false;
		}
	}
	
	public void disposeBookRecomandation() {
		if(bookRecFlag) {
			bookRec.dispose();
			bookRecFlag = false;
		}
	}
	
	public void disposeListBook() {
		if(listBookFlag) {
			listBook.dispose();
			listBookFlag = false;
		}
	}
	public void disposeLoadingScreen() {
		if(loadingFlag) {
			loading.dispose();
			loadingFlag = false;
		}
	}

}
