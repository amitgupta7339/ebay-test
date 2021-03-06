package org.ebay_project.ebaytester.resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ebay_project.ebaytester.model.Message;
import org.ebay_project.ebaytester.model.Product;
import org.ebay_project.ebaytester.model.Product_desc;
import org.ebay_project.ebaytester.model.Product_list;
import org.ebay_project.ebaytester.service.CategoryService;
import org.ebay_project.ebaytester.service.ProductService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/products")
public class ProductResource {
// =============================================GET ALL PRODUCTS=================================================//
	@GET // (written by Prakhar)
	@Path("/getAllproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		ProductService ps = new ProductService();
		return ps.getAllProducts();
	}
// =========================================GET SINGLE PRODUCT ON EACH DEAL======================================//
	@GET // (written by Amit)
	@Path("/getDealproductsImage")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> dealImagesList() {
		ProductService ps = new ProductService();
		return ps.dealImagesList();
	}
// =============================GET ALL PRODUCTS OF PARTICULAR SELLER BASES ON SELLER ID=========================//
	@GET // (written by Anamol)
	@Path("/list/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getSellerAllProducts(@PathParam("user_id") int user_id) {
		ProductService ps = new ProductService();
		return ps.getSellerAllProducts(user_id);
	}
// =================================GET PRODUCT BASES ON SELLER ID AND PRODUCT NAME==============================//
	@GET // (written by Anamol)
	@Path("/list/{user_id}/{product_name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getSellerProduct(@PathParam("user_id") int user_id, @PathParam("product_name") String product_name) {
		ProductService ps = new ProductService();
		return ps.getSellerProduct(user_id, product_name);
	}
// =========================================GET PRODUCT BASES ON PRODUCT ID======================================//
	@GET // (written by Amit in Exam)
	@Path("/getproduct/{product_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductDetail(@PathParam("product_id") int product_id) {
		ProductService ps = new ProductService();
		return ps.getProductDetail(product_id);
	}
// =====================GET DETAILS OF PRODUCT FOR PRODUCT DESCRIPTION PAGE BASES ON PRODUCT ID==================//
  	@GET //(Written By Pulkit)
	@Path("/{product_id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Product_desc getProductById(@PathParam("product_id") int prod_id){
		ProductService prod_serv = new ProductService();
		return prod_serv.getProductById(prod_id);
	}
// ========================================GET PRODUCTS BASES ON CATEGORY NAME===================================//
	@GET // (written by Pulkit)
	@Path("/category/{category_name}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ArrayList<Product_list> getProductsCategoryWise(@PathParam("category_name") String category_name) {
		CategoryService categoryService = new CategoryService();
		ArrayList<Product_list> list = categoryService.getProducts(category_name);
		if (list.isEmpty()) {
			return null;
		} 
		else {
			return list;
		}
	}
// =======================================UPLOAD PRODUCT DETAILS BASES ON SELLER ID==============================//
	@POST // (written by Prakhar,changes done by Saumya and Amit)
	@Path("/uploadProduct/{seller_id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
	public Product productUpload(@FormParam("Product_Name") String product_name, @FormParam("Category") String category,
			@FormParam("Subcategory") String subcategory, @PathParam("seller_id") int seller_id,
			@FormParam("Price") int product_price, @FormParam("Quantity") int quantity,
			@FormParam("Condition") String product_condition, @FormParam("Shipping") String product_shipping,
			@FormParam("Description") String product_description, @FormParam("Discount") int product_discount,
			@FormParam("Item_id") String item_id, @FormParam("Brand") String brand, @FormParam("Color") String color,
			@FormParam("Screen_size") String screen_size, @FormParam("Processor") String processor,
			@FormParam("Storage") String storage, @FormParam("Warranty") String warranty,
			@FormParam("Operating_system") String operating_system, @FormParam("Product_year") int product_year,
			@FormParam("Gender") String gender, @FormParam("Warranty_type") String warranty_type,
			@FormParam("Applicable") String applicable, @FormParam("Material") String material,
			@FormParam("Clothing_size") String clothing_size, @FormParam("Style") String style,
			@FormParam("Card_class") String card_class) {

		ProductService productService = new ProductService();
		Product product = productService.setProductInfo(product_name, category, subcategory, seller_id, product_price,
				quantity, product_condition, product_shipping, product_description, product_discount, item_id, brand,
				color, screen_size, processor, storage, warranty, operating_system, product_year, gender, warranty_type,
				applicable, material, clothing_size, style, card_class);
		if (product == null) {
			return null; // resource conflict
		} 
		else {
			return product;
		}

	}
// =====================================MODIFY PRODUCT BASES ON SELLER ID AND PRODUCT NAME=======================//
	@PUT // (written by Anamol)
	@Path("/update/{user_id}/{product_name}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateSellerProduct(@PathParam("user_id") int user_id,
			@PathParam("product_name") String original_product_name, @FormParam("Product_Name") String product_name,
			@FormParam("Price") int product_price, @FormParam("Quantity") int quantity,
			@FormParam("Condition") String product_condition, @FormParam("Shipping") String product_shipping,
			@FormParam("Description") String product_description, @FormParam("Discount") int product_discount,
	        @FormParam("Brand") String brand, @FormParam("Color") String color,
			@FormParam("Screen_size") String screen_size, @FormParam("Processor") String processor,
			@FormParam("Storage") String storage, @FormParam("Warranty") String warranty,
			@FormParam("Operating_system") String operating_system, @FormParam("Product_year") int product_year,
			@FormParam("Gender") String gender, @FormParam("Warranty_type") String warranty_type,
			@FormParam("Applicable") String applicable, @FormParam("Material") String material,
			@FormParam("Clothing_size") String clothing_size, @FormParam("Style") String style,
			@FormParam("Card_class") String card_class) {
		System.out.println("helloupdateSellerProduct");
		ProductService ps = new ProductService();
		return ps.updateSellerProduct(original_product_name, product_name, user_id, product_price, quantity,
				product_condition, product_shipping, product_description, product_discount, brand, color,
				screen_size, processor, storage, warranty, operating_system, product_year, gender, warranty_type,
				applicable, material, clothing_size, style, card_class);
	}
// =====================================DELETE PRODUCT BY SELLER BASES ON SELLER ID==============================//
	@POST // (written by Amit)
	@Path("/deleteProduct/{seller_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Message DeleteProduct(@FormParam("Products_name") String product_name,
			@PathParam("seller_id") int seller_id) {

		ProductService productService = new ProductService();
		return productService.DeleteProduct(product_name, seller_id);
	}
// ==================================================Upload product image========================================//
	@POST // (written by Prakhar)
	@Path("/uploadProductPic")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String ProductPicUpload(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileFormDataContentDisposition,
			@FormDataParam("product_id") int product_id) {

		String fileName = null;
		String uploadFilePath = null;
		fileName = fileFormDataContentDisposition.getFileName();
		uploadFilePath = new ProductService().uploadProductPic(fileInputStream, fileName, product_id);
		if (uploadFilePath == null)
			return null;

		return uploadFilePath;
	}
}
// ==================================================END OF CODE=================================================//
