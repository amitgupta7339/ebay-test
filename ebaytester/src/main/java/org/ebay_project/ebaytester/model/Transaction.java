package org.ebay_project.ebaytester.model;

public class Transaction {
	
	int Txn_surr_id;
	int txn_id;
	int user_id;
	int seller_id;
	int amount;
	int product_id;
	int quantity;
	int deal_id;
	String product_status;
	java.sql.Date order_date;
	java.sql.Date ship_date;
	java.sql.Date receive_date;
	int product_discount;
	String product_deal;
	String user_address;
	String seller_address;
	float seller_rating;
	float product_rating;
	// identifing user vs seller only in modal
	int user_set;
	String string_txn_id;

	public String getString_txn_id() {
		return string_txn_id;
	}

	public void setString_txn_id(String string_txn_id) {
		this.string_txn_id = string_txn_id;
	}

	public int getUser_set() {
		return user_set;
	}

	public void setUser_set(int user_set) {
		this.user_set = user_set;
	}

	public Transaction() {
		
	}

	public int getTxn_surr_id() {
		return Txn_surr_id;
	}

	public void setTxn_surr_id(int txn_surr_id) {
		Txn_surr_id = txn_surr_id;
	}

	public int getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(int txn_id) {
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

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String product_status) {
		this.product_status = product_status;
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

	public String getProduct_deal() {
		return product_deal;
	}

	public void setProduct_deal(String product_deal) {
		this.product_deal = product_deal;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getSeller_address() {
		return seller_address;
	}

	public void setSeller_address(String seller_address) {
		this.seller_address = seller_address;
	}

	public float getSeller_rating() {
		return seller_rating;
	}

	public void setSeller_rating(float seller_rating) {
		this.seller_rating = seller_rating;
	}

	public float getProduct_rating() {
		return product_rating;
	}

	public void setProduct_rating(float product_rating) {
		this.product_rating = product_rating;
	}
	

}