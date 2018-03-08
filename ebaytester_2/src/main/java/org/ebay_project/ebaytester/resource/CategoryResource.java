package org.ebay_project.ebaytester.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Category;
import org.ebay_project.ebaytester.model.Subcategory;
import org.ebay_project.ebaytester.service.CategoryService;


@Path("/category")
public class CategoryResource {
	@GET
	@Path("/getAllCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Category> getAllCategories() {
                System.out.println("getallCategory API excute start");
		CategoryService categoryService=new CategoryService();
		ArrayList<Category> list = categoryService.getAllCategory();
                System.out.println("getallCategory API excute successfully");
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
	@Path("/uploadCategory")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String categoryUpload(@FormParam("Category") String Category, @FormParam("NewCategory") String NewCategory, @FormParam("subCategory") String subCategory) {
                System.out.println("uploadCategory API excute start");
		CategoryService categoryService = new CategoryService();
		String result = categoryService.categoryAdded(Category,NewCategory,subCategory);
		System.out.println(result);
                System.out.println("uploadCategory API excute successfully");
			return result;

	}
	@GET
	@Path("/{category_name}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ArrayList<Subcategory> getAllSubCategory(@PathParam("category_name") String category_name) {
                System.out.println("subCategory API excute start"+category_name);
		CategoryService categoryService=new CategoryService();
		ArrayList<Subcategory> list = categoryService.getAllSubCategory(category_name);
                System.out.println("subCategory API excute successfully");
		if(list.isEmpty())
		{
		return null;
		}
		else
		{
		return list ;
	}
	}

}
