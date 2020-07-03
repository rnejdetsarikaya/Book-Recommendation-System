package model;

import javax.swing.ImageIcon;

public class Book {

	ImageIcon icon;
	String title;
	String author;
	String ISBN;
	String publisher;
	int year;
	String point;
	int ratingCount;
	String ImageLURL;

	/**
	 * @return the imageLURL
	 */
	public String getImageLURL() {
		return ImageLURL;
	}

	/**
	 * @param imageLURL the imageLURL to set
	 */
	public void setImageLURL(String imageLURL) {
		ImageLURL = imageLURL;
	}

	/**
	 * @return the ratingCount
	 */
	public int getRatingCount() {
		return ratingCount;
	}

	/**
	 * @param ratingCount the ratingCount to set
	 */
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Book(ImageIcon icon, String title, String author, String iSBN, String publisher, int year, String point) {
		this.icon = icon;
		this.title = title;
		this.author = author;
		ISBN = iSBN;
		this.publisher = publisher;
		this.year = year;
		this.point = point;
	}

	/**
	 * @return the point
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

}
