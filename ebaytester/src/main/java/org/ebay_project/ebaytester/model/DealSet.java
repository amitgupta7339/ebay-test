package org.ebay_project.ebaytester.model;

public class DealSet {

	private String free_products;
	private String paid_products;
	private String start_date;
	private String end_date;
	private int overall_discount;
	private String deal_name;
	private int seller_id;
	public DealSet(String free_products, String paid_products, String start_date, String end_date, int overall_discount,
			String deal_name, int seller_id) {
		super();
		this.free_products = free_products;
		this.paid_products = paid_products;
		this.start_date = start_date;
		this.end_date = end_date;
		this.overall_discount = overall_discount;
		this.deal_name = deal_name;
		this.seller_id = seller_id;
	}
	public DealSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDeal_name() {
		return deal_name;
	}
	public void setDeal_name(String deal_name) {
		this.deal_name = deal_name;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getFree_products() {
		return free_products;
	}
	public void setFree_products(String free_products) {
		this.free_products = free_products;
	}
	public String getPaid_products() {
		return paid_products;
	}
	public void setPaid_products(String paid_products) {
		this.paid_products = paid_products;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getOverall_discount() {
		return overall_discount;
	}
	public void setOverall_discount(int overall_discount) {
		this.overall_discount = overall_discount;
	}
	
}