package com.dealdata;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int id;
	private String user_namber;
	private String user_passworld;
	private String user_head;
	private String user_name;
	private List<User> list = new ArrayList<User>();
	
	public List<User> getList() {
		return list;
	}



	public void setList(List<User> list) {
		this.list = list;
	}



	public User(int id, String user_namber, String user_passworld,
			String user_head, String user_name) {
		super();
		this.id = id;
		this.user_namber = user_namber;
		this.user_passworld = user_passworld;
		this.user_head = user_head;
		this.user_name = user_name;
	}
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}



	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_namber() {
		return user_namber;
	}
	public void setUser_namber(String user_namber) {
		this.user_namber = user_namber;
	}
	public String getUser_passworld() {
		return user_passworld;
	}
	public void setUser_passworld(String user_passworld) {
		this.user_passworld = user_passworld;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	
}
