package org.ebay_project.ebaytester.model;

public class Product_list {
	private int product_id;
	private int sub_category_id;
	private int category_id;
	private String product_name;
	private int product_price;
	private int product_discount;
	private String product_condition;
	private String product_shipping;
	private int product_sold_quantity;
	private String product_img_url;
	private int product_available_quantity;
	private String product_description;
	private int product_rating;
	private String product_brand;
	private String product_color;
	private String product_storage;
	private String product_warranty;
	private String product_os;
	private int product_year;
	private String product_gender;
	private String product_applicable;
	private String product_material;
	private String product_clothing_size;
	private String product_warranty_type;
	private int product_seller_id;
	private String product_seller_name;
	private String product_deal;

	public Product_list() {
	};

	public Product_list(String product_seller_name, int product_seller_id, int product_id, int sub_category_id,
			int category_id, String product_name, int product_price, int product_discount, String product_condition,
			String product_shipping, int product_sold_quantity, String product_img_url, int product_available_quantity,
			String product_description, int product_rating, String product_brand, String product_color,
			String product_storage, String product_warranty, String product_os, int product_year, String product_gender,
			String product_applicable, String product_material, String product_clothing_size,
			String product_warranty_type) {
		this.product_seller_id = product_seller_id;
		this.product_seller_name = product_seller_name;
		this.product_id = product_id;
		this.sub_category_id = sub_category_id;
		this.category_id = category_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.product_condition = product_condition;
		this.product_shipping = product_shipping;
		this.product_sold_quantity = product_sold_quantity;
		this.product_img_url = product_img_url;
		this.product_available_quantity = product_available_quantity;
		this.product_description = product_description;
		this.product_brand = product_brand;
		this.product_color = product_color;
		this.product_storage = product_storage;
		this.product_warranty = product_warranty;
		this.product_rating = product_rating;
		this.product_os = product_os;
		this.product_year = product_year;
		this.product_gender = product_gender;
		this.product_applicable = product_applicable;
		this.product_material = product_material;
		this.product_clothing_size = product_clothing_size;
		this.product_warranty_type = product_warranty_type;

	};

	public String getProduct_deal() {
		return product_deal;
	}

	public void setProduct_deal(String product_deal) {
		this.product_deal = product_deal;
	}

	public int getSellerId() {
		return product_seller_id;
	};

	public void setSellerId(int id) {
		this.product_seller_id = id;
	};

	public String getSellerName() {
		return product_seller_name;
	};

	public void setSellerName(String name) {
		this.product_seller_name = name;
	};

	public int getProductId() {
		return product_id;
	};

	public void setProductId(int id) {
		this.product_id = id;
	};

	public int getSubCatId() {
		return sub_category_id;
	};

	public void setSubCatId(int id) {
		this.sub_category_id = id;
	};

	public int getCatId() {
		return this.category_id;
	};

	public void setCatId(int id) {
		this.category_id = id;
	};

	public String getProductName() {
		return product_name;
	};

	public void setProductName(String name) {
		this.product_name = name;
	};

	public int getProductPrice() {
		return product_price;
	};

	public void setProductPrice(int price) {
		this.product_price = price;
	};

	public int getProductRating() {
		return product_rating;
	};

	public void setProductRating(int rating) {
		this.product_rating = rating;
	}

	public int getProductDiscount() {
		return product_discount;
	};

	public void setProductDiscount(int discount) {
		this.product_discount = discount;
	};

	public String getProductCondition() {
		return product_condition;
	};

	public void setProductCondition(String cond) {
		this.product_condition = cond;
	};

	public String getProductShipping() {
		return product_shipping;
	};

	public void setProductShipping(String ship) {
		this.product_shipping = ship;
	};

	public int getProductSoldQuantity() {
		return product_sold_quantity;
	};

	public void setProductSoldQuantity(int new_q) {
		this.product_sold_quantity = new_q;
	};

	public String getProductImageUrl() {
		return product_img_url;
	};

	public void setProductImageUrl(String url) {
		this.product_img_url = url;
	};

	public int getProductAvailableQuantity() {
		return product_available_quantity;
	};

	public void setProductAvailableQuantity(int q) {
		this.product_available_quantity = q;
	};

	public String getProductDescription() {
		return this.product_description;
	};

	public void setProductDescription(String desc) {
		this.product_description = desc;
	};

	public String getProductBrand() {
		return product_brand;
	};

	public void setProductBrand(String brand) {
		this.product_brand = brand;
	};

	public String getProductColor() {
		return product_color;
	};

	public void setProductColor(String color) {
		this.product_color = color;
	};

	public String getProductStorage() {
		return product_storage;
	};

	public void setProductStorage(String store) {
		this.product_storage = store;
	};

	public String getProductWarranty() {
		return this.product_warranty;
	};

	public void setProductWarranty(String warranty) {
		this.product_warranty = warranty;
	};

	public String getProductOS() {
		return product_os;
	};

	public void setProductOS(String os) {
		this.product_os = os;
	};

	public int getProductYear() {
		return product_year;
	};

	public void setProductYear(int year) {
		this.product_year = year;
	};

	public String getProductGender() {
		return product_gender;
	};

	public void setProductGender(String gender) {
		this.product_gender = gender;
	};

	public String getProductApplicable() {
		return this.product_applicable;
	};

	public void setProductApplicable(String appl) {
		this.product_applicable = appl;
	};

	public String getProductMaterial() {
		return product_material;
	};

	public void setProductMaterial(String mat) {
		this.product_material = mat;
	};

	public String getProductClothingSize() {
		return product_clothing_size;
	};

	public void setProductClothingSize(String size) {
		this.product_clothing_size = size;
	};

	public String getProductWarrantyType() {
		return product_warranty_type;
	};

	public void setProductWarrantyType(String warranty) {
		this.product_warranty_type = warranty;
	};

}
