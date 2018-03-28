package org.ebay_project.ebaytester.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Product;
import org.ebay_project.ebaytester.model.Subcategory;
import org.ebay_project.ebaytester.service.SubcategoryService;

@Path("/subcategory")
public class SubcategoryResource {
	@GET
	@Path("/getsubcategoryname/{sub_category_id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Subcategory getSubcategoryName(@PathParam("sub_category_id") int sub_category_id) {
                System.out.println("Subcategory API excute start"+sub_category_id);
		SubcategoryService subcategoryService=new SubcategoryService();
		Subcategory cc = subcategoryService.getSubcategoryName(sub_category_id);
                System.out.println("subCategory API excute successfully");
		
		return cc ;
	
	}
    @GET
	@Path("/getAllsubcategoryname")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Subcategory> getAllSubcategoryName() {
                //System.out.println("Subcategory API excute start"+sub_category_id);
		SubcategoryService subcategoryService=new SubcategoryService();
		return subcategoryService.getAllSubcategoryName();
	
	}
}
