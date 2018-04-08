package org.ebay_project.ebaytester.model;

public class Product_desc {

	private int product_id;
	private int sub_category_id;
	private int category_id;
	private int seller_id;
	private String seller_name;
	private String seller_country;
	private String seller_state;
	private String seller_city;
	private String seller_address;
	private String seller_email;
	private String seller_contact;
	private String product_name;
	private int product_price;
	private int product_discount;
	private String product_condition;
	private String product_shipping;
	private int product_sold_quantity;
	private String product_img_url;
	private int product_available_quantity;
	private String product_description;
	private int Rating;
	private String Deal;
	private String Brand;
	private String Color;
	private String Screen_Size;
	private String Processor;
	private String Storage;
	private String Warranty;
	private String OS;
	private int Year;
	private String Gender;
	private String Applicable;
	private String Material;
	private String Size;
	private String Style;
	private String Warranty_Type;
	private String Card_Class;
	
	public Product_desc() {};
	
	public Product_desc(int product_id, int sub_category_id, int category_id, int seller_id, String seller_name,
			String seller_country, String seller_state, String seller_city,String seller_address,String seller_email, String seller_contact, String product_name,
			int product_price, int product_discount, String product_condition, String product_shipping,
			int product_sold_quantity, String product_img_url, int product_available_quantity,
			String product_description, int rating, String deal, String brand, String color, String screen_Size,
			String processor, String storage, String warranty, String oS, int year, String gender, String applicable,
			String material, String size, String style, String warranty_Type, String card_Class) {
		super();
		this.product_id = product_id;
		this.sub_category_id = sub_category_id;
		this.category_id = category_id;
		this.seller_id = seller_id;
		this.seller_email = seller_email;
		this.seller_name = seller_name;
		this.seller_country = seller_country;
		this.seller_state = seller_state;
		this.seller_city = seller_city;
		this.seller_address = seller_address;
		this.seller_contact = seller_contact;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.product_condition = product_condition;
		this.product_shipping = product_shipping;
		this.product_sold_quantity = product_sold_quantity;
		this.product_img_url = product_img_url;
		this.product_available_quantity = product_available_quantity;
		this.product_description = product_description;
		Rating = rating;
		Deal = deal;
		Brand = brand;
		Color = color;
		Screen_Size = screen_Size;
		Processor = processor;
		Storage = storage;
		Warranty = warranty;
		OS = oS;
		Year = year;
		Gender = gender;
		Applicable = applicable;
		Material = material;
		Size = size;
		Style = style;
		Warranty_Type = warranty_Type;
		Card_Class = card_Class;
	}
	
	public String getSeller_city() {
		return seller_city;
	}
	public void setSeller_city(String seller_city)
	{
		this.seller_city = seller_city;
	}
	public String getSeller_email() {
		
		return this.seller_email;
	}
	public void setSeller_email(String seller_email)
	{
		this.seller_email = seller_email;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getSeller_country() {
		return seller_country;
	}
	public void setSeller_country(String seller_country) {
		this.seller_country = seller_country;
	}
	public String getSeller_state() {
		return seller_state;
	}
	public void setSeller_state(String seller_state) {
		this.seller_state = seller_state;
	}
	public String getSeller_address() {
		return seller_address;
	}
	public void setSeller_address(String seller_address) {
		this.seller_address = seller_address;
	}
	public String getSeller_contact() {
		return seller_contact;
	}
	public void setSeller_contact(String seller_contact) {
		this.seller_contact = seller_contact;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}
	public String getProduct_condition() {
		return product_condition;
	}
	public void setProduct_condition(String product_condition) {
		this.product_condition = product_condition;
	}
	public String getProduct_shipping() {
		return product_shipping;
	}
	public void setProduct_shipping(String product_shipping) {
		this.product_shipping = product_shipping;
	}
	public int getProduct_sold_quantity() {
		return product_sold_quantity;
	}
	public void setProduct_sold_quantity(int product_sold_quantity) {
		this.product_sold_quantity = product_sold_quantity;
	}
	public String getProduct_img_url() {
		return product_img_url;
	}
	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}
	public int getProduct_available_quantity() {
		return product_available_quantity;
	}
	public void setProduct_available_quantity(int product_available_quantity) {
		this.product_available_quantity = product_available_quantity;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public String getDeal() {
		return Deal;
	}
	public void setDeal(String deal) {
		Deal = deal;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getScreen_Size() {
		return Screen_Size;
	}
	public void setScreen_Size(String screen_Size) {
		Screen_Size = screen_Size;
	}
	public String getProcessor() {
		return Processor;
	}
	public void setProcessor(String processor) {
		Processor = processor;
	}
	public String getStorage() {
		return Storage;
	}
	public void setStorage(String storage) {
		Storage = storage;
	}
	public String getWarranty() {
		return Warranty;
	}
	public void setWarranty(String warranty) {
		Warranty = warranty;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getApplicable() {
		return Applicable;
	}
	public void setApplicable(String applicable) {
		Applicable = applicable;
	}
	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getStyle() {
		return Style;
	}
	public void setStyle(String style) {
		Style = style;
	}
	public String getWarranty_Type() {
		return Warranty_Type;
	}
	public void setWarranty_Type(String warranty_Type) {
		Warranty_Type = warranty_Type;
	}
	public String getCard_Class() {
		return Card_Class;
	}
	public void setCard_Class(String card_Class) {
		Card_Class = card_Class;
	}
}
