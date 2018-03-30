package org.ebay_project.ebaytester.model;

public class User {
	private int user_id;
	private String user_fname;
	private String user_lname;
	private String user_email;
	private String user_password;
	private String user_country;
	private String user_address;
	private String user_city;
	private String user_state;
	private int user_pincode;
	private String user_phone;

	public User() {
	}

	public User(int user_id, String user_fname, String user_lname, String user_email, String user_password,
			String user_country, String user_address, String user_city, String user_state, int user_pincode,
			String user_phone) {
		super();
		this.user_id = user_id;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_country = user_country;
		this.user_address = user_address;
		this.user_city = user_city;
		this.user_state = user_state;
		this.user_pincode = user_pincode;
		this.user_phone = user_phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_country() {
		return user_country;
	}

	public void setUser_country(String user_country) {
		this.user_country = user_country;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public int getUser_pincode() {
		return user_pincode;
	}

	public void setUser_pincode(int user_pincode) {
		this.user_pincode = user_pincode;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
}
