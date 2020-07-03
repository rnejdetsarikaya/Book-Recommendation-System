package model;

import java.util.HashMap;
import java.util.Map;

public class User {

	private int UserId;
	private String UserName;
	private String loc;
	private int age;
	private HashMap<Integer, HashMap<String, Double>> mapUsers; // UserID , books ---> USER'S BOOKS
	private HashMap<String, Double> mapBook; // ISBN , Rating --> MY BOOK
	private HashMap<Integer, Double> similarity; // UserID , Factor her bir kullanicinin cikarma isleminde kullanilacak
	private Map<Double, String> predicate;


	private User() {

	}

	public Map<Double, String> getPredicate() {
		return predicate;
	}
	
	public void setPredicate(Map<Double, String> predicate) {
		this.predicate = predicate;
	}
	public HashMap<Integer, Double> getSimilarity() {
		return similarity;
	}

	public void setSimilarty(HashMap<Integer, Double> userFactor) {
		this.similarity = userFactor;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	private static User user = new User();

	public static User getUser() {
		return user;
	}

	public int getUserId() {
		return UserId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		UserId = userId;
	}

	public HashMap<Integer, HashMap<String, Double>> getUserMap() {
		return mapUsers;
	}

	public void setUserMap(HashMap<Integer, HashMap<String, Double>> m) {
		mapUsers = m;
	}

	public HashMap<String, Double> getBookMap() {
		return mapBook;
	}

	public void setBookMap(HashMap<String, Double> m) {
		mapBook = m;
	}

}
