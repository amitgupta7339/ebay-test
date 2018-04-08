package org.ebay_project.ebaytester.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Subcategory;
import org.ebay_project.ebaytester.service.SubcategoryService;

@Path("/subcategory")
public class SubcategoryResource {
// =====================================GET SUB CATEGORY NAME BASES ON SUB CATEGORY ID============================//	
	@GET//(written by Prakhar)
	@Path("/getsubcategoryname/{sub_category_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subcategory getSubcategoryName(@PathParam("sub_category_id") int sub_category_id) {
		SubcategoryService subcategoryService = new SubcategoryService();
		Subcategory cc = subcategoryService.getSubcategoryName(sub_category_id);
		return cc;
	}
// =======================================GET ALL SUB CATEGORIES NAMES============================================//
	@GET //(written by Amit)
	@Path("/getAllsubcategoryname")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Subcategory> getAllSubcategoryName() {
		SubcategoryService subcategoryService = new SubcategoryService();
		return subcategoryService.getAllSubcategoryName();

	}
}
// =============================================END OF CODE=======================================================//