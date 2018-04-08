package org.ebay_project.ebaytester.model;

public class Product {

	private int product_id, sub_category_id, category_id, user_id, product_price, product_discount,
			product_sold_quantity, product_available_quantity, product_rating, product_year;
	private String product_name, product_condition, product_shipping, product_img_url, product_description,brand,
			color, screen_size, processor, storage, warranty, operating_system, gender, applicable, material,
			clothing_size, style, warranty_type, card_class,item_id;

	public Product() {
	}

	public Product(int product_id, int sub_category_id, int category_id, int user_id, int product_price,
			int product_discount, int product_sold_quantity, int product_available_quantity, int product_rating,
			int product_year, String product_name, String product_condition, String product_shipping,
			String product_img_url, String product_description,String brand, String color,
			String screen_size, String processor, String storage, String warranty, String operating_system,
			String gender, String applicable, String material, String clothing_size, String style, String warranty_type,
			String card_class) {
		super();
		this.product_id = product_id;
		this.sub_category_id = sub_category_id;
		this.category_id = category_id;
		this.user_id = user_id;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.product_sold_quantity = product_sold_quantity;
		this.product_available_quantity = product_available_quantity;
		this.product_rating = product_rating;
		this.product_year = product_year;
		this.product_name = product_name;
		this.product_condition = product_condition;
		this.product_shipping = product_shipping;
		this.product_img_url = product_img_url;
		this.product_description = product_description;
		this.brand = brand;
		this.color = color;
		this.screen_size = screen_size;
		this.processor = processor;
		this.storage = storage;
		this.warranty = warranty;
		this.operating_system = operating_system;
		this.gender = gender;
		this.applicable = applicable;
		this.material = material;
		this.clothing_size = clothing_size;
		this.style = style;
		this.warranty_type = warranty_type;
		this.card_class = card_class;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getSub_category_id() {
		return sub_category_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public int getProduct_price() {
		return product_price;
	}

	public int getProduct_discount() {
		return product_discount;
	}

	public int getProduct_sold_quantity() {
		return product_sold_quantity;
	}

	public int getProduct_available_quantity() {
		return product_available_quantity;
	}

	public int getProduct_rating() {
		return product_rating;
	}

	public int getProduct_year() {
		return product_year;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getProduct_condition() {
		return product_condition;
	}

	public String getProduct_shipping() {
		return product_shipping;
	}

	public String getProduct_img_url() {
		return product_img_url;
	}

	public String getProduct_description() {
		return product_description;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public String getScreen_size() {
		return screen_size;
	}

	public String getProcessor() {
		return processor;
	}

	public String getStorage() {
		return storage;
	}

	public String getWarranty() {
		return warranty;
	}

	public String getOperating_system() {
		return operating_system;
	}

	public String getGender() {
		return gender;
	}

	public String getApplicable() {
		return applicable;
	}

	public String getMaterial() {
		return material;
	}

	public String getClothing_size() {
		return clothing_size;
	}

	public String getStyle() {
		return style;
	}

	public String getWarranty_type() {
		return warranty_type;
	}

	public String getCard_class() {
		return card_class;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}

	public void setProduct_sold_quantity(int product_sold_quantity) {
		this.product_sold_quantity = product_sold_quantity;
	}

	public void setProduct_available_quantity(int product_available_quantity) {
		this.product_available_quantity = product_available_quantity;
	}

	public void setProduct_rating(int product_rating) {
		this.product_rating = product_rating;
	}

	public void setProduct_year(int product_year) {
		this.product_year = product_year;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setProduct_condition(String product_condition) {
		this.product_condition = product_condition;
	}

	public void setProduct_shipping(String product_shipping) {
		this.product_shipping = product_shipping;
	}

	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public void setClothing_size(String clothing_size) {
		this.clothing_size = clothing_size;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setWarranty_type(String warranty_type) {
		this.warranty_type = warranty_type;
	}

	public void setCard_class(String card_class) {
		this.card_class = card_class;
	}
}
