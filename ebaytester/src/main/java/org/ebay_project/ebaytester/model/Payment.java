package org.ebay_project.ebaytester.model;

public class Payment {
	
	private String card_number,cvv,ex_date;
	private int balance;
	public Payment() {}
	
	public Payment( String card_number, String cvv, String ex_date, int balance) {
		
		
		setCard_number(card_number);
		setCvv(cvv);
		setEx_date(ex_date);
		setBalance(balance);
	}
	

	public String getCard_number() {
		return card_number;
	}
	public String getCvv() {
		return cvv;
	}
	public String getEx_date() {
		return ex_date;
	}
	public int getBalance() {
		return balance;
	}
	
	
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	

}
