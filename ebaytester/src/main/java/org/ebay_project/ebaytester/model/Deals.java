package org.ebay_project.ebaytester.model;

public class Deals {
	private int deal_id;
	private String deal_name;
	private int free_products;
	private int paid_products;
	private int overall_discount;
	public Deals() {
		//super();
	}

	public Deals(int deal_id,String deal_Name, int free_products, int paid_products, int overall_discount) {
		super();
		deal_name = deal_Name;
		this.free_products = free_products;
		this.paid_products = paid_products;
		this.overall_discount = overall_discount;
	}
	
	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public String getDeal_name() {
		return deal_name;
	}

	public void setDeal_name(String deal_name) {
		this.deal_name = deal_name;
	}

	public int getFree_products() {
		return free_products;
	}
	public void setFree_products(int free_products) {
		this.free_products = free_products;
	}
	public int getPaid_products() {
		return paid_products;
	}
	public void setPaid_products(int paid_products) {
		this.paid_products = paid_products;
	}
	public int getOverall_discount() {
		return overall_discount;
	}
	public void setOverall_discount(int overall_discount) {
		this.overall_discount = overall_discount;
	}
	
}
