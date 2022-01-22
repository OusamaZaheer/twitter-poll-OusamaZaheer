package com.twitter.poll.entity;

import javax.validation.constraints.NotNull;

public class User {
	public static String FIND_BY_USER_NAME = "select * from user where user_name = ? LIMIT 1";
	public static String FIND_BY_USER_NAME_AND_PASSWORD = "select * from user where user_name = ? and password = ? LIMIT 1";
	public static String CREATE_NEW_USER = "insert into user (user_name, password) values (?,?)";
	public static String FIND_ALL = "select * from user";
	public static String DELETE = "delete from user where user_id = ?";

	private int id;

	@NotNull
	private String userName;

	@NotNull
	private String password;

	public User(@NotNull String userName, @NotNull String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public User(int id, @NotNull String userName, @NotNull String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
