package org.ebay_project.ebaytester.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Category;
import org.ebay_project.ebaytester.service.CategoryService;


@Path("/category")
public class CategoryResource {
	@GET
	@Path("/getAllCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Category> getAllCategories() {
		CategoryService categoryService=new CategoryService();
		ArrayList<Category> list = categoryService.getAllCategory();
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
