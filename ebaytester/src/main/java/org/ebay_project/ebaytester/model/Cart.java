package org.ebay_project.ebaytester.model;

public class Cart {
	private int product_id;
	private float product_price;
	private int product_available_quantity;
	private String user_fname;
	private String user_lname;
	private String product_name;
	private String product_img_url;
	private String product_shipping;
	private int product_user_quantity;
	private String item_id;

	public Cart() {
	};



	public Cart(int product_id, float product_price, int product_available_quantity, String user_fname,
			String user_lname, String product_name, String product_img_url, String product_shipping,
			int product_user_quantity, String item_id) {
		super();
		this.product_id = product_id;
		this.product_price = product_price;
		this.product_available_quantity = product_available_quantity;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.product_name = product_name;
		this.product_img_url = product_img_url;
		this.product_shipping = product_shipping;
		this.product_user_quantity = product_user_quantity;
		this.item_id = item_id;
	}



	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	public int getProduct_available_quantity() {
		return product_available_quantity;
	}

	public void setProduct_available_quantity(int product_available_quantity) {
		this.product_available_quantity = product_available_quantity;
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

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_img_url() {
		return product_img_url;
	}

	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}

	public String getProduct_shipping() {
		return product_shipping;
	}

	public void setProduct_shipping(String product_shipping) {
		this.product_shipping = product_shipping;
	}

	public int getProduct_user_quantity() {
		return product_user_quantity;
	}

	public void setProduct_user_quantity(int product_user_quantity) {
		this.product_user_quantity = product_user_quantity;
	}
}
