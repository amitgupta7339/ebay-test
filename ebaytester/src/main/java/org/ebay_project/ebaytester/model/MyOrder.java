package org.ebay_project.ebaytester.model;

public class MyOrder {

	private int Txn_surr_id;
	private String txn_id;
	private int user_id;
	private int seller_id;
	private int amount;
	private int product_id;
	private int quantity;
	private int deal_id;
	private String status;
	private int free_check;
	private java.sql.Date order_date;
	private java.sql.Date ship_date;
	private java.sql.Date receive_date;
	private int product_discount;
	private String user_address;
	private String product_deal;
	private String product_img_url;
	private String item_id;
	private String product_name;
	private String user_fname;
	private String user_lname;
	private String user_email;

	public MyOrder() {

	}

	public int getTxn_surr_id() {
		return Txn_surr_id;
	}

	public void setTxn_surr_id(int txn_surr_id) {
		Txn_surr_id = txn_surr_id;
	}

	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(java.sql.Date order_date) {
		this.order_date = order_date;
	}

	public java.sql.Date getShip_date() {
		return ship_date;
	}

	public void setShip_date(java.sql.Date ship_date) {
		this.ship_date = ship_date;
	}

	public java.sql.Date getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(java.sql.Date receive_date) {
		this.receive_date = receive_date;
	}

	public int getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getProduct_deal() {
		return product_deal;
	}

	public void setProduct_deal(String product_deal) {
		this.product_deal = product_deal;
	}

	public String getProduct_img_url() {
		return product_img_url;
	}

	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public int getFree_check() {
		return free_check;
	}

	public void setFree_check(int free_check) {
		this.free_check = free_check;
	}

}
