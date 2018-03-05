package org.ebay_project.ebaytester.resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ebay_project.ebaytester.model.Product;
import org.ebay_project.ebaytester.service.CategoryService;
import org.ebay_project.ebaytester.service.ProductService;
import org.ebay_project.ebaytester.service.SubcategoryService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/products")
public class ProductResource {
	

	
	
	@GET
	@Path("/getAllProducts")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ArrayList<Product> getAllProducts() {
		ProductService productService=new ProductService();
		ArrayList<Product> list = productService.getAllProducts();
		if(list.isEmpty())
		{
		return null;
		}
		else
		{
		return list ;
	        }
	}
	
	@GET
	@Path("/category/{category_name}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ArrayList<Product> getProductsCategoryWise(@PathParam("category_name") String category_name) {
		CategoryService categoryService=new CategoryService();
		ArrayList<Product> list = categoryService.getProducts(category_name);
		if(list.isEmpty())
		{
		return null;
		}
		else
		{
		return list ;
	}
	}
	@GET
	@Path("/sub_category/{sub_category_name}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ArrayList<Product> getProductsSubcategoryWise(@PathParam("sub_category_name") String sub_category_name) {
		SubcategoryService subcategoryService=new SubcategoryService();
		ArrayList<Product> list = subcategoryService.getProducts(sub_category_name);
		if(list.isEmpty())
		{
		return null;
		}
		else
		{
		return list ;
	}
	}
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/uploadProduct")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_FORM_URLENCODED })
	public Product productUpload(@FormParam("Product_Name") String Product_Name, @FormParam("Category") String Category, @FormParam("Subcategory") String Subcategory, @FormParam("Price") int Price, @FormParam("Quantity") int Quantity, 
			@FormParam("Condition") String Condition, @FormParam("Shipping") String Shipping, @FormParam("Description") String Description, @FormParam("Discount") int Discount) {

		ProductService productService = new ProductService();
		Product product = productService.setProductInfo(Product_Name,Category,Subcategory,Price,Quantity,Condition,Shipping,Description,Discount);

		if (product == null) {
			return null;  // resource conflict
		} else {

			return product;
		}

	}
	
	@POST
	@Path("/uploadProductPic/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String ProductPicUpload(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileFormDataContentDisposition,
			@PathParam("product_id") String product_id) {
		
		System.out.println("calling service check");

		// local variables
		String fileName = null;
		String uploadFilePath = null;

		fileName = fileFormDataContentDisposition.getFileName();
		uploadFilePath = new ProductService().uploadProductPic(
				fileInputStream, fileName, product_id);
		if (uploadFilePath == null)
			return null;

		return uploadFilePath;

	}


}
